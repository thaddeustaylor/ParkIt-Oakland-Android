package edu.pitt.designs1635.ParkIt;

import java.util.List;



import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;


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
        
        
        //Dummy point - Cathedral of Learning
        double lat = 40.444282;
        double lng = -79.953108;
 
        //A ParkingLocation for the dummy point
        ParkingLocation pl = new ParkingLocation((int) (lat * 1E6), (int) (lng * 1E6));
        
        //center on the geoPoint in the 
        mapCtrl.animateTo(pl.getGeoPoint());
        mapCtrl.setZoom(17);
        
        
        //TODO add call to the database.
        // first overlay
 		Drawable drawable = getResources().getDrawable(R.drawable.pushpin_small);
 		ParkingLocationItemizedOverlay itemizedOverlay = new ParkingLocationItemizedOverlay(drawable, mapView);
 		
 		GeoPoint point = new GeoPoint((int) (lat * 1E6), (int) (lng * 1E6));
 		
 		
 		OverlayItem overlayItem = new OverlayItem(point, "Cathedral of Learning", 
 				"Learning.....yeah right.\nLook at the birds!");
 		
 		
 		itemizedOverlay.addOverlay(overlayItem);
        //A this is for to display a push pin image for the dummy point.
        //ParkingLocationOverlay plo = new ParkingLocationOverlay(this, pl);    

        
        List<Overlay> points = mapView.getOverlays();
        points.clear();
        points.add(itemizedOverlay);
        
        //mapView.invalidate();
        
    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
}