
package edu.pitt.designs1635.ParkIt;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.Toast;
import android.content.Intent;

import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import com.readystatesoftware.mapviewballoons.BalloonItemizedOverlay;

public class ParkingLocationItemizedOverlay extends BalloonItemizedOverlay<OverlayItem> {

	private ArrayList<OverlayItem> m_overlays = new ArrayList<OverlayItem>();
	private ArrayList<ParkingLocation> m_locations = new ArrayList<ParkingLocation>();
	private Context c;
	
	public ParkingLocationItemizedOverlay(Drawable defaultMarker, MapView mapView, boolean shadow) 
	{
		super(boundCenterBottom(defaultMarker), mapView);
		c = mapView.getContext();
		populate();
		//setShadow(shadow);
	}

	public ParkingLocationItemizedOverlay(Drawable defaultMarker, MapView mapView) 
	{
		super(boundCenterBottom(defaultMarker), mapView);
		c = mapView.getContext();
		populate();
		//setShadow(false);
	}
	
	public void addOverlay(OverlayItem overlay) {
	    m_overlays.add(overlay);
	    populate();
	}
	
	public void addOverlay(ParkingLocation pl) {
	    
		OverlayItem overlay = new OverlayItem(pl.getGeoPoint(), pl.getName(),	
                "Rate: "+pl.getRate());
		m_locations.add(pl);
		m_overlays.add(overlay);
	    populate();
	}
	
	@Override
	protected OverlayItem createItem(int i) {
		return m_overlays.get(i);
	}

	
	
	@Override
	public int size() {
		return m_overlays.size();
	}

	@Override
	protected boolean onBalloonTap(int index, OverlayItem item) {
		Toast.makeText(c, "onBalloonTap for overlay index " + index,
				Toast.LENGTH_LONG).show();
		return true;
	}
	
	protected boolean onNextClick(int index, OverlayItem item) {
		//c.startActivity(new Intent(c, Information.class));
		return true;
	}
}

