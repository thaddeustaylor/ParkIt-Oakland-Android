package edu.pitt.designs1635.ParkIt;

import java.util.List;
import android.graphics.drawable.Drawable;
import android.content.res.Resources;
import android.database.Cursor;
import android.content.Context;
import android.util.Log;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;


public class ParkItActivity extends MapActivity {
    
	private dbAdapter mDbHelper;
    private Cursor mCursor;
    MapView mapView;
    ParkingLocationItemizedOverlay gItemizedOverlay, lItemizedOverlay, mItemizedOverlay;

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
                if(mCursor.getInt(3) == 0)
                {
                    point = new GeoPoint(mCursor.getInt(1), mCursor.getInt(2));
                    overlayItem = new OverlayItem(point, mCursor.getString(4), 
                    "Rate: "+mCursor.getFloat(8));
                    gItemizedOverlay.addOverlay(overlayItem);
                }
                else if(mCursor.getInt(3) == 1)
                {
                    point = new GeoPoint(mCursor.getInt(1), mCursor.getInt(2));
                    overlayItem = new OverlayItem(point, mCursor.getString(4), 
                    "Rate: "+mCursor.getFloat(8));
                    lItemizedOverlay.addOverlay(overlayItem);
                }

                mCursor.moveToNext();
                Log.i("PARRRRRKKKKKIT", "AFter MAPSHIT");
            }while(!mCursor.isLast());
        }

        List<Overlay> points = mapView.getOverlays();
        points.clear();
        points.add(gItemizedOverlay);
        points.add(lItemizedOverlay);
        //point.add(mItemizedOverlay);

        //This will attempt to grab the current location and have the map automatically center to there
        //Buuuuut it doesn't use the current location yet. It uses the LAST known location...
        //LocationManager mLocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        //LocationListener mLocListener = new MyLocationListener();
        //mLocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mLocListener);
        //Location loc = mLocManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        //mapCtrl.animateTo(new GeoPoint((int) (loc.getLatitude() * 1E6), (int) (loc.getLongitude() * 1E6)));
        //mapCtrl.setZoom(17);
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
            /*
            case R.id.menu_alarm:
                startActivity(new Intent(this, alarm .class));
                return true;
            */
        }
        return super.onMenuItemSelected(featureId, item);
    }
}