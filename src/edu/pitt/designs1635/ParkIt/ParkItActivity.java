package edu.pitt.designs1635.ParkIt;

import java.util.List;

import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.content.res.Resources;
import android.database.Cursor;
import android.content.Context;
import android.util.Log;
import android.content.Intent;

import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;



public class ParkItActivity extends MapActivity {
    
	private dbAdapter mDbHelper;
    private Cursor mCursor;
    MapView mapView;
    ParkingLocationItemizedOverlay gItemizedOverlay, lItemizedOverlay, mItemizedOverlay;
    
    
    public final int ADD_ACTIVITY = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mDbHelper = new dbAdapter(this);
        mDbHelper.open();
        mDbHelper.addDummyData();
        mCursor = mDbHelper.fetchAllRows();
        mCursor.moveToFirst();
        
        
        mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
        
        Drawable drawable = getResources().getDrawable(R.drawable.g_icon);
        gItemizedOverlay = new ParkingLocationItemizedOverlay(drawable, mapView);
        drawable = getResources().getDrawable(R.drawable.l_icon);
        lItemizedOverlay = new ParkingLocationItemizedOverlay(drawable, mapView);
        drawable = getResources().getDrawable(R.drawable.m_icon);
        mItemizedOverlay = new ParkingLocationItemizedOverlay(drawable, mapView);

        OverlayItem overlayItem;
        GeoPoint point;

        MapController mapCtrl = mapView.getController();

        //Will take the cursor (contains every record in the db) and iterate through adding each point to the appropriate overlay
        if(mCursor.getCount() > 0)
        {
            do{                
            	ParkingLocation pl = new ParkingLocation(mCursor.getInt(1), mCursor.getInt(2));
            	
            	pl.setName(mCursor.getString(4));
            	pl.setRate(mCursor.getFloat(8));
            	
                if(mCursor.getInt(3) == 0)
                {

                    //point = new GeoPoint(mCursor.getInt(1), mCursor.getInt(2));
                    //overlayItem = new OverlayItem(point, mCursor.getString(4), 
                    //"Rate: "+mCursor.getFloat(8));
                    gItemizedOverlay.addOverlay(pl);
                }
                else if(mCursor.getInt(3) == 1)
                {
                    //point = new GeoPoint(mCursor.getInt(1), mCursor.getInt(2));
                    //overlayItem = new OverlayItem(point, mCursor.getString(4), 
                    //"Rate: "+mCursor.getFloat(8));
                    lItemizedOverlay.addOverlay(pl);
                }
                else
                {
                    //point = new GeoPoint(mCursor.getInt(1), mCursor.getInt(2));
                    //overlayItem = new OverlayItem(point, mCursor.getString(4), 
                    //"Rate: "+mCursor.getFloat(8));
                    mItemizedOverlay.addOverlay(pl);
                }


                mCursor.moveToNext();
            }while(!mCursor.isAfterLast());
        }
        
        mDbHelper.close();

        //TouchLocationOverlay plo = new TouchLocationOverlay(this);
        	
        List<Overlay> points = mapView.getOverlays();
        points.clear();
        //points.add(plo);
        points.add(gItemizedOverlay);
        points.add(lItemizedOverlay);
        
        //point.add(mItemizedOverlay);

        //This will attempt to grab the current location and have the map automatically center to there
        /* Buuuuut it doesn't use the current location yet. It uses the LAST known location...
        LocationManager mLocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        LocationListener mLocListener = new MyLocationListener();
        mLocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mLocListener);
        Location loc = mLocManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        mapCtrl.animateTo(new GeoPoint((int) (loc.getLatitude() * 1E6), (int) (loc.getLongitude() * 1E6)));
        mapCtrl.setZoom(17);
        */
    }
    
    @Override
    protected void onStop()
    {
	    super.onStop();
	    mDbHelper.close();
    }
    
    @Override
    protected void onResume()
    {
	    super.onResume();
	    mDbHelper.open();
    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menu_add:
            	Intent intent = new Intent(this, AddPointMapActivity.class);
            	
            	GeoPoint p = mapView.getMapCenter();
            	
            	intent.putExtra("edu.pitt.designs1635.ParkIt.center.lat", p.getLatitudeE6());
            	intent.putExtra("edu.pitt.designs1635.ParkIt.center.long", p.getLongitudeE6());
            	
                this.startActivityForResult(intent, ADD_ACTIVITY);
                return true;
            case R.id.menu_alarm:
                startActivity(new Intent(this, Timer.class));
                return true;
        }
        return super.onMenuItemSelected(featureId, item);
    }
    
    
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		switch (requestCode)
		{
		case ADD_ACTIVITY:
			Bundle extras = data.getExtras();
		
			GeoPoint newPoint = new GeoPoint(extras.getInt("edu.pitt.designs1635.ParkIt.Add.lat"),
											extras.getInt("edu.pitt.designs1635.ParkIt.Add.long"));
			
			switch(resultCode)
			{
			case AddPointMapActivity.SAVE_TO_PHONE:
				Toast.makeText(this, "Save point to Phone " + newPoint.getLatitudeE6() + ", " +
								newPoint.getLongitudeE6(),	Toast.LENGTH_LONG).show();
				break;
			case AddPointMapActivity.SAVE_TO_SERVER:
				Toast.makeText(this, "Save point to the server " + newPoint.getLatitudeE6() + ", " +
								newPoint.getLongitudeE6(),	Toast.LENGTH_LONG).show();
				break;
			default:
				Toast.makeText(this, "I don't know where to save it " + newPoint.getLatitudeE6() + ", " +
								newPoint.getLongitudeE6(),	Toast.LENGTH_LONG).show();
				break;
			}
			
			
			break;
		default:
			break;
		}
		
		
		
	}
}