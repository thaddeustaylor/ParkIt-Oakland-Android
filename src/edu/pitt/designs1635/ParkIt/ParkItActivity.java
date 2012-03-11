package edu.pitt.designs1635.ParkIt;

import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import edu.pitt.designs1635.ParkIt.ParkingLocation.*;
import android.database.Cursor;
import android.os.Bundle;


public class ParkItActivity extends MapActivity {
    //private dbAdapter mDbHelper;
    //private Cursor mCursor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //mDbHelper = new dbAdapter(this);

        MapView mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
    
        //MapController mapCtrl = mapView.getController(); 
        
        //double lat = 40.444282;
        //double lng = -79.953108;
    
        //GeoPoint center = new GeoPoint((int) (lat * 1E6), (int) (lng * 1E6));
    
        //mapCtrl.animateTo(center);
        //mapCtrl.setZoom(17);
        //mapView.invalidate();
        
        //ParkingLocation pl;
        //System.out.println(PAYMENT_TYPE.CASH);
        
    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}