package edu.pitt.designs1635.ParkIt;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseException;
import com.parse.FindCallback;

import android.preference.PreferenceManager;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;

public class ParkItActivity extends MapActivity {
    
	private dbAdapter mDbHelper;
    private Cursor mCursor;
    private MapView mapView;
    private ParkingLocationItemizedOverlay gItemizedOverlay, lItemizedOverlay, mItemizedOverlay;
    private Drawable drawable;
    private SharedPreferences prefs;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        prefs = this.getSharedPreferences("parkItPrefs", Activity.MODE_PRIVATE);

        mDbHelper = new dbAdapter(this);
        mDbHelper.open();
        mCursor = mDbHelper.fetchAllRows();
        startManagingCursor(mCursor);
        mCursor.moveToFirst();
        
        
        mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);

        //getRemotePoints();        
        refreshAllPoints();

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
        refreshAllPoints();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        mDbHelper.close();
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
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("last_location_lat", mapView.getMapCenter().getLatitudeE6());
                editor.putInt("last_location_lon", mapView.getMapCenter().getLongitudeE6());
                editor.commit();
            	startActivity(new Intent(this, Add.class));
                return true;
            case R.id.menu_refresh:
                getRemotePoints();
                return true;
            case R.id.menu_alarm:
                startActivity(new Intent(this, Timer.class));
                return true;
            case R.id.menu_settings:
                mDbHelper.abandonShip();
                refreshAllPoints();
                return true;
        }
        return super.onMenuItemSelected(featureId, item);
    }

    public void getRemotePoints()
    {
        Parse.initialize(this, "pAtl7R7WUbPl3RIVMD9Ov8UDVODGYSJ9tImxKTPQ", "cgjq64nO8l5RVbmrqYH3Nv2VC1zPyX4904htpXPy"); 
        ParseQuery query = new ParseQuery("Points");
        query.findInBackground(new FindCallback() {
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    pointsProcessing(objects);
                }
            }
        });
    }

    public void pointsProcessing(List<ParseObject> objs)
    {
        for(int i=0; i < objs.size(); i++)
        {
            mDbHelper.addPoint(objs.get(i));
        }
        refreshAllPoints();
    }

    public void refreshAllPoints()
    {
        mCursor = mDbHelper.fetchAllRows();
        startManagingCursor(mCursor);
        mCursor.moveToFirst();
        drawable = getResources().getDrawable(R.drawable.g_icon);
        gItemizedOverlay = new ParkingLocationItemizedOverlay(drawable, mapView);
        drawable = getResources().getDrawable(R.drawable.l_icon);
        lItemizedOverlay = new ParkingLocationItemizedOverlay(drawable, mapView);
        drawable = getResources().getDrawable(R.drawable.m_icon);
        mItemizedOverlay = new ParkingLocationItemizedOverlay(drawable, mapView);

        gItemizedOverlay.hideAllBalloons();
        lItemizedOverlay.hideAllBalloons();
        mItemizedOverlay.hideAllBalloons();
        
        
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
        
            
        List<Overlay> points = mapView.getOverlays();
        points.clear();
        points.add(gItemizedOverlay);
        points.add(lItemizedOverlay);
        points.add(mItemizedOverlay);
    }
    
}