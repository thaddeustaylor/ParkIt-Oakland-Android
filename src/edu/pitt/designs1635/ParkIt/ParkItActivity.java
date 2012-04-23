package edu.pitt.designs1635.ParkIt;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.view.*;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.location.*;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockMapActivity;
import com.actionbarsherlock.view.ActionMode;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.Window;
import com.google.android.maps.*;
import com.parse.*;

import edu.pitt.designs1635.ParkIt.ParkingLocationItemizedOverlay.BalloonTouchListener;
import edu.pitt.designs1635.ParkIt.TouchLocationOverlay.TapListener;
import edu.pitt.designs1635.ParkIt.Directions.DrivingDirections;
import edu.pitt.designs1635.ParkIt.Directions.DrivingDirections.IDirectionsListener;
import edu.pitt.designs1635.ParkIt.Directions.DrivingDirections.Mode;
import edu.pitt.designs1635.ParkIt.Directions.DrivingDirectionsFactory;
import edu.pitt.designs1635.ParkIt.Directions.Route;
import edu.pitt.designs1635.ParkIt.Directions.RouteOverlay;

public class ParkItActivity extends SherlockMapActivity implements LocationListener
{
	private dbAdapter mDbHelper;
	private Cursor mCursor;
	private MapView mapView;
	private ParkingLocationItemizedOverlay gItemizedOverlay, lItemizedOverlay, mItemizedOverlay;
	private CurrentLocationOverlay cLocationOverlay;
	private Drawable drawable;
	private SharedPreferences prefs;
	private MapController mapCtrl;
	LocationManager mlocManager;
	Location lastKnownLocation;
	Double latitude, longitude;
	ActionMode mMode;
	private Criteria criteria;
	private GeoPoint p;
	private AddLocationOverlay addedLocation;
	private RouteOverlay m_route;
	private ActionBar ab;
	
	
	

	private Runnable refreshMap = new Runnable()
	{
		public void run()
		{
			mapView.postInvalidate();
		}
	};

	public static final int INFORMATION_ACTIVITY = 0;
	public static final int ADD_LOCATION_ACTIVITY = 1;
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.main);
		ab = getSupportActionBar();

		Log.i("PARKIT ACTIVITY", "-----------START---------");

		setSupportProgressBarIndeterminateVisibility(false);

		prefs = this.getSharedPreferences("parkItPrefs", Activity.MODE_PRIVATE);

		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		mapCtrl = mapView.getController();

		criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setAltitudeRequired(false);
		criteria.setBearingRequired(false);
		criteria.setCostAllowed(true);
		criteria.setPowerRequirement(Criteria.NO_REQUIREMENT);

		mlocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

		GeoPoint currentLocation = getCurrentLocation();
		if(currentLocation != null)
			updateLocation(currentLocation);

		if(!prefs.getBoolean("tutorial", false))
		{
			promptTutorial();
		}

		mDbHelper = new dbAdapter(this);
		mDbHelper.open();

		mCursor = mDbHelper.fetchAllRows();
		startManagingCursor(mCursor);
		mCursor.moveToFirst();

		getRemotePoints();

		mapCtrl.setZoom(17);
	}

	public void promptTutorial()
	{
		AlertDialog.Builder ed = new AlertDialog.Builder(this);
		ed.setIcon(R.drawable.ic_launcher);
		ed.setTitle("First Time User");
		ed.setView(LayoutInflater.from(this).inflate(R.layout.tut_dialog,null));

		SharedPreferences.Editor editor = prefs.edit();
			editor.putBoolean("tutorial", true);
			editor.commit();

		ed.setPositiveButton("Yes",
		new android.content.DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int arg1) {
			startActivity(new Intent(getApplicationContext(), Tutorial.class));
			}
		});

		ed.setNegativeButton("No",
		new android.content.DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int arg1) {
			}
		});
		ed.show();
	}

	@Override
	protected void onStop()
	{
		super.onStop();
		mlocManager.removeUpdates(this);
		if(mDbHelper != null)
		mDbHelper.close();
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		mDbHelper.open();
		//refreshAllPoints();
		
		// Check is GPS is on.  Show alert if off.
		if (! isGPSAvailable()) {
			showAlertMessageNoGPS();
		}
		
		// Check for Internet connection on startup.
		if (! isNetworkAvailable()) {
			showAlertMessageNoInternets();
		} 
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		mlocManager.removeUpdates(this);
		if(mDbHelper != null)
			mDbHelper.close();
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.main_menu, menu);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.menu_add:
				hideAllBalloons();
				mMode = startActionMode(new AddPointActionMode());
				
				return true;
			case R.id.menu_refresh:
				getRemotePoints();
				updateLocation(getCurrentLocation());
				return true;
			case R.id.menu_alarm:
				startActivity(new Intent(this, Timer.class));
				return true;
			case R.id.menu_settings:
				//mDbHelper.abandonShip();
				//refreshAllPoints();
				return true;
		}
		return super.onMenuItemSelected(featureId, item);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			ActionBar ab = getSupportActionBar();
			ab.setTitle("ParkIt");
			List<Overlay> points = mapView.getOverlays();
			
			if(m_route != null)
				points.remove(m_route);
			mapView.postInvalidate();
			return true;
		default:
			return super.onOptionsItemSelected(item);	
		}
	}

	public void getRemotePoints()
	{
		ab.setTitle("Refreshing Points...");
		setSupportProgressBarIndeterminateVisibility(true);

		Parse.initialize(this, "pAtl7R7WUbPl3RIVMD9Ov8UDVODGYSJ9tImxKTPQ", "cgjq64nO8l5RVbmrqYH3Nv2VC1zPyX4904htpXPy");
		ParseQuery query = new ParseQuery("Points");
		query.findInBackground(new FindCallback() {
			public void done(List<ParseObject> objects, ParseException e) {
				if (e == null) {
					pointsProcessing(objects);

					ab.setTitle("ParkIt");
					setSupportProgressBarIndeterminateVisibility(false);
				}
			}
		});

	}

	public void pointsProcessing(List<ParseObject> objs)
	{
		for(int i=0; i < objs.size(); i++)
		{
			try{
				mDbHelper.addPoint(objs.get(i));
			}catch(Exception e){}
		}
		refreshAllPoints();
	}

	public void refreshAllPoints()
	{
		if(mDbHelper == null)
		{
			mDbHelper.open();
			mCursor = mDbHelper.fetchAllRows();
		}
		startManagingCursor(mCursor);
		mCursor.moveToFirst();
		drawable = getResources().getDrawable(R.drawable.g_icon);
		gItemizedOverlay = new ParkingLocationItemizedOverlay(drawable, mapView, false);
		drawable = getResources().getDrawable(R.drawable.l_icon);
		lItemizedOverlay = new ParkingLocationItemizedOverlay(drawable, mapView, false);
		drawable = getResources().getDrawable(R.drawable.m_icon);
		mItemizedOverlay = new ParkingLocationItemizedOverlay(drawable, mapView, false);

		GeoPoint currentLocation = getCurrentLocation();
		if(currentLocation != null)
			cLocationOverlay = new CurrentLocationOverlay(currentLocation);

		hideAllBalloons();

		gItemizedOverlay.setBalloonTouchListener(new MyBalloonTouchListener());
		lItemizedOverlay.setBalloonTouchListener(new MyBalloonTouchListener());
		mItemizedOverlay.setBalloonTouchListener(new MyBalloonTouchListener());


		OverlayItem overlayItem;
		GeoPoint point;

		//Will take the cursor (contains every record in the db) and iterate through adding each point to the appropriate overlay
		if(mCursor.getCount() > 0)
		{
			do{
				ParkingLocation pl = new ParkingLocation(mCursor.getInt(1), mCursor.getInt(2));

				pl.setName(mCursor.getString(4));
				pl.setRate(mCursor.getFloat(8));
				pl.setType(mCursor.getInt(3));
				pl.setPayment(mCursor.getInt(5));
				pl.setLimit(mCursor.getInt(6));

				if(mCursor.getInt(3) == 0)
					mItemizedOverlay.addOverlay(pl);
				else if(mCursor.getInt(3) == 1)
					lItemizedOverlay.addOverlay(pl);
				else
					gItemizedOverlay.addOverlay(pl);
				mCursor.moveToNext();
			}while(!mCursor.isAfterLast());
		}

		mapView.getOverlays().clear();
		mapView.getOverlays().add(gItemizedOverlay);
		mapView.getOverlays().add(lItemizedOverlay);
		mapView.getOverlays().add(mItemizedOverlay);
		mapView.getOverlays().add(cLocationOverlay);
		mapView.postInvalidate();
	}

	public void onLocationChanged(Location location) {
		//updateLocation(location);
	}

	public void recenterMap(View v)
	{
		updateLocation(getCurrentLocation());
	}

	private void updateLocation(GeoPoint p)
	{
		if(p != null)
			mapCtrl.animateTo(p);
	}

	private GeoPoint getCurrentLocation()
	{
		List<String> providers = mlocManager.getProviders(true);
		Location location = null;
		int i = providers.size();
		for( i = providers.size()-1; i>=0; i--)
		{
			location = mlocManager.getLastKnownLocation( providers.get(i) );
			if(location != null )
			{
				mlocManager.requestLocationUpdates(providers.get(i),0,0,this);
				break;
			}
		}

		if(location == null)
			return null;
		
		
		Double lat = location.getLatitude()*1E6;
		Double lng = location.getLongitude()*1E6;
		p = new GeoPoint(lat.intValue(), lng.intValue());

		return p;
	}

	public void onProviderDisabled(String provider) {
		// required for interface, not used
	}

	public void onProviderEnabled(String provider) {
		// required for interface, not used
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// required for interface, not used
	}

	private class MyIDirectionsListener implements IDirectionsListener
	{

		@Override
		public void onDirectionsAvailable(Route route, Mode mode) {
			
			List<Overlay> points = mapView.getOverlays();
			
			if(m_route != null)
				points.remove(m_route);
			
			m_route = new RouteOverlay(route.getGeoPoints());
			//RouteSegmentOverlay rso = new RouteSegmentOverlay(route.getGeoPoints().get(0), route.getGeoPoints().get(route.getGeoPoints().size() - 2));
			points.add(m_route);

			
			
			mMode = startActionMode(new RouteActionMode());
			
			//Log.i("MyIDirectionsListener", "Route achieved! Size = " + ro.size());
			mapView.postInvalidate();
			
			
			
		}

		@Override
		public void onDirectionsNotAvailable() {
			Log.i("MyIDirectionsListener", "No Route!!!!!!");
			//TODO provide a warning to the user
		}
		
	}

	private class MyBalloonTouchListener implements BalloonTouchListener
	{

		@Override
		public void onBalloonTap(ParkingLocation pl) {
			Intent info = new Intent(ParkItActivity.this, Information.class);
			
			info.putExtra("edu.pitt.designs1635.ParkIt.location.info", pl);
			mDbHelper.close();
			startActivityForResult(info, INFORMATION_ACTIVITY);
			
		}

		@Override
		public void onNextClick(ParkingLocation pl) {
			
			if(mMode != null)	
				mMode.finish();
			
			ActionBar ab = getSupportActionBar();
			ab.setTitle("Loading Directions");
			setSupportProgressBarIndeterminateVisibility(true);
			getDirections(pl.getGeoPoint());
			
			
		}
		
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{

		Log.i("ParkItActivity.onActivityResult", "Start " + requestCode);
		
		
		switch (requestCode)
		{
			case INFORMATION_ACTIVITY:
				if(data == null)
					return;
				Bundle extras = data.getExtras();
				Log.i("ParkItActivity.onActivityResult", "in INFORMATION_ACTIVITY");
				
				switch(resultCode)
				{
				case Information.GOTO_TRUE:

					//Log.i("ParkItActivity.onActivityResult", "in GOTO_TRUE");

					ActionBar ab = getSupportActionBar();
					ab.setTitle("Loading Directions");
					setSupportProgressBarIndeterminateVisibility(true);
					
					ParkingLocation pl = extras.getParcelable("edu.pitt.designs1635.ParkIt.Information.info");
					getDirections(pl.getGeoPoint());
					
					break;
					
				case Information.GOTO_FALSE:
					Log.i("ParkItActivity.onActivityResult", "in GOTO_FALSE");
					break;
				default:

					Log.i("ParkItActivity.onActivityResult", "in default");
					break;
				}
			break;
		case ADD_LOCATION_ACTIVITY:
			Log.i("ParkItActivity", "RETURN FROM ADD LOCATION");
			refreshAllPoints();
			break;
		default:
			break;
		}
	}

	private void getDirections(GeoPoint destination)
	{
		
		DrivingDirections dir = DrivingDirectionsFactory.createDrivingDirections();
		GeoPoint start = getCurrentLocation();
		if(start != null)
			dir.driveTo(start, destination, Mode.DRIVING, new MyIDirectionsListener());
	}

	private final class RouteActionMode implements ActionMode.Callback {
		@Override
		public boolean onCreateActionMode(ActionMode mode, Menu menu) {
			//Used to put dark icons on light action bar           
			menu.add("Clear Route")
				.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

			return true;
		}

		@Override
		public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
			
			return false;
		}

		@Override
		public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
			setSupportProgressBarIndeterminateVisibility(false);
			ActionBar ab = getSupportActionBar();
			ab.setTitle("ParkIt");
			List<Overlay> points = mapView.getOverlays();
			
			if(m_route != null)
				points.remove(m_route);
			mapView.postInvalidate();

			mode.finish();
			return true;
		}

		@Override
		public void onDestroyActionMode(ActionMode mode) {
			setSupportProgressBarIndeterminateVisibility(false);
			ActionBar ab = getSupportActionBar();
			ab.setTitle("ParkIt");
			List<Overlay> points = mapView.getOverlays();
			
			if(m_route != null)
				points.remove(m_route);
			mapView.postInvalidate();
		}
	}
	
	private final class AddPointActionMode implements ActionMode.Callback {

		TouchLocationOverlay plo;
		
		final int BUTTON_GROUP = 0;
		final int EXIT_BUTTON = 0;
		final int ADD_BUTTON = 2;
		final int CANCEL_BUTTON = 1;
		
		@Override
        public boolean onCreateActionMode(ActionMode mode, final Menu menu) {
            
			mode.setTitle("Tap Map to Add Point");
			
			//Used to put dark icons on light action bar           
            menu.add(BUTTON_GROUP,EXIT_BUTTON,0,"Exit")
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
            
            
            
            menu.add(BUTTON_GROUP,CANCEL_BUTTON,1,"Cancel")
        	.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        
            menu.getItem(CANCEL_BUTTON).setVisible(false);
            
            menu.add(BUTTON_GROUP,ADD_BUTTON,2,"Add Point")
				.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
            
            menu.getItem(ADD_BUTTON).setVisible(false);
            
           
            
            //menu.add(groupId, itemId, order, title)
            plo = new TouchLocationOverlay(getApplicationContext());
            
            final List<Overlay> points = mapView.getOverlays();
            
            plo.setTapListener(new TapListener(){

    			public void onTap(GeoPoint p) {
    				
    				//only add the first point
    				if (addedLocation == null)
    				{	
    					//this is stored so the overlay can be removed later
	    				addedLocation = new AddLocationOverlay(p);
	    				
	    				points.add(addedLocation);
	    				
	    				menu.getItem(ADD_BUTTON).setVisible(true);
	    				menu.getItem(CANCEL_BUTTON).setVisible(true);
	    				menu.getItem(EXIT_BUTTON).setVisible(false);
	    				
	    			
	    				
    				}else
    				{
    					points.remove(addedLocation);
    					
    					addedLocation = new AddLocationOverlay(p);
	    				
	    				points.add(addedLocation);
	    				
    				}
    			}
            	
            });
            
            
            points.add(plo);
            
            
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        	setSupportProgressBarIndeterminateVisibility(false);
            ActionBar ab = getSupportActionBar();

			ab.setTitle("ParkIt");
			
			
			//TODO make this a switch
			if(item.getItemId() == ADD_BUTTON)
			{
				//Log.i("AddPointActionMode", "Yay! add point");

				mDbHelper.close();
				
				
				Intent intent = new Intent(getApplicationContext(), Add.class);
				
				intent.putExtra("edu.pitt.designs1635.ParkIt.location.lat", addedLocation.getGeoPoint().getLatitudeE6());
				intent.putExtra("edu.pitt.designs1635.ParkIt.location.long", addedLocation.getGeoPoint().getLongitudeE6());
				//Log.i("AddActionMode", "BEFORE ADD ACTIVITY");

            	startActivityForResult(intent, ADD_LOCATION_ACTIVITY);
            	//Log.i("AddActionMode", "AFTER ADD ACTIVITY");
            	mode.finish();
				
				
			}else if (item.getItemId() == CANCEL_BUTTON)
			{
				//Log.i("AddPointActionMode", "Boo! Cancel");
				mode.getMenu().getItem(EXIT_BUTTON).setVisible(true);
				mode.getMenu().getItem(ADD_BUTTON).setVisible(false);
				mode.getMenu().getItem(CANCEL_BUTTON).setVisible(false);
				
				
			}else if (item.getItemId() == EXIT_BUTTON)
			{
				mode.finish();
			}
			
			if(addedLocation != null)
			{
				List<Overlay> points = mapView.getOverlays();
				points.remove(addedLocation);
				addedLocation = null;
			}

			
            return true;
        }


		@Override
		public void onDestroyActionMode(ActionMode mode) {
			setSupportProgressBarIndeterminateVisibility(false);
			ActionBar ab = getSupportActionBar();
			ab.setTitle("ParkIt");
			List<Overlay> points = mapView.getOverlays();
			
			if(addedLocation != null)
			{
				
				points.remove(addedLocation);
				addedLocation = null;
			}
			
			if(plo != null)
			{
				points.remove(plo);
			}
        }

    }

	private boolean isNetworkAvailable() {
		ConnectivityManager connectivityManager = 
				(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		return activeNetworkInfo != null;
	}
	
	private boolean isGPSAvailable() {
		LocationManager locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		if (locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {	
			return true;	
		} else {
			return false;
		}

	}
	
	private void showAlertMessageNoGPS() {
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("GPS is not enabled on this phone.  Do you want to enable it?")
			.setCancelable(false)
			.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				public void onClick(final DialogInterface dialog, final int id) {
					startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
				}
			})
			.setNegativeButton("No", new DialogInterface.OnClickListener() {
				public void onClick(final DialogInterface dialog, final int id) {
					dialog.cancel();
				}
			});
		final AlertDialog alert = builder.create();
		alert.show();	
	}
	
	private void showAlertMessageNoInternets() {
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("No connection to the Internet detected.  Most features of this app require an active Internet connection.")
			.setCancelable(false)
			.setPositiveButton("Continue Anyway", new DialogInterface.OnClickListener() {
				public void onClick(final DialogInterface dialog, final int id) {
					dialog.cancel();
				}
			})
			.setNegativeButton("Exit App", new DialogInterface.OnClickListener() {
				public void onClick(final DialogInterface dialog, final int id) {
					finish();
				}
			});
		final AlertDialog alert = builder.create();
		alert.show();	
	}
	
	private void hideAllBalloons()
	{
		if(gItemizedOverlay != null)
			gItemizedOverlay.hideAllBalloons();
		if(lItemizedOverlay != null)
			lItemizedOverlay.hideAllBalloons();
		if(mItemizedOverlay != null)
			mItemizedOverlay.hideAllBalloons();	
	}
	
}

