package edu.pitt.designs1635.ParkIt;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import android.database.Cursor;

import android.os.Bundle;

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
    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}