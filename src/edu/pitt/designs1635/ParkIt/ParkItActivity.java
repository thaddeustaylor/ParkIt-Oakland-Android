package edu.pitt.designs1635.ParkIt;

import java.util.List;

import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;


public class ParkItActivity extends MapActivity {
    private dbAdapter mDbHelper;
    private Cursor mCursor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mDbHelper = new dbAdapter(this);

        MapView mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
    
        MapController mapCtrl = mapView.getController(); 
        
        double lat = 40.444282;
        double lng = -79.953108;
    
        GeoPoint center = new GeoPoint((int) (lat * 1E6), (int) (lng * 1E6));
    
        mapCtrl.animateTo(center);
        mapCtrl.setZoom(17);
        
        ParkingLocation pl = new ParkingLocation((int) (lat * 1E6), (int) (lng * 1E6));
        
        
        ParkingLocationOverlay plo = new ParkingLocationOverlay(this.getResources(), pl);    

        List<Overlay> loo = mapView.getOverlays();
        loo.clear();
        loo.add(plo);
        
        mapView.invalidate();
        
        
        
        
    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}