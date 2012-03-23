package edu.pitt.designs1635.ParkIt;

import java.util.List;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;

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

import android.os.Bundle;

public class ParkItActivity extends MapActivity {
    private dbAdapter mDbHelper;
    private Cursor mCursor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mDbHelper = new dbAdapter(this);
        mDbHelper.open();
        mCursor = mDbHelper.fetchRows();

        

        MapView mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);

        MapController mapCtrl = mapView.getController();

        //Dummy point - Cathedral of Learning
        double lat = 40.444282;
        double lng = -79.953108;
 
        //A ParkingLocation for the dummy point
        ParkingLocation pl = new ParkingLocation((int) (lat * 1E6), (int) (lng * 1E6));
        
        //center on the geoPoint in the 
        mapCtrl.animateTo(pl.getGeoPoint());
        mapCtrl.setZoom(17);

        Drawable drawable = getResources().getDrawable(R.drawable.g_icon);
        ParkingLocationItemizedOverlay itemizedOverlay = new ParkingLocationItemizedOverlay(drawable, mapView);
        
        GeoPoint point = new GeoPoint((int) (lat * 1E6), (int) (lng * 1E6));
        OverlayItem overlayItem = new OverlayItem(point, "Cathedral of Learning", 
                "Learning.....yeah right.");
        itemizedOverlay.addOverlay(overlayItem);

        lat = 40.5;
        lng = -80;
        point = new GeoPoint((int) (lat * 1E6), (int) (lng * 1E6));
        overlayItem = new OverlayItem(point, "Some Place", 
                "stuff");
        itemizedOverlay.addOverlay(overlayItem);

        List<Overlay> points = mapView.getOverlays();
        points.clear();
        points.add(itemizedOverlay);
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