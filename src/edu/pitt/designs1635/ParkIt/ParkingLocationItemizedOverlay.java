
package edu.pitt.designs1635.ParkIt;

import java.text.NumberFormat;
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
	private NumberFormat nf;
	
	
	public ParkingLocationItemizedOverlay(Drawable defaultMarker, MapView mapView, boolean shadow) 
	{
		super(boundCenterBottom(defaultMarker), mapView);
		c = mapView.getContext();
		setBalloonBottomOffset(defaultMarker.getIntrinsicHeight());
		//setShadow(shadow);
	
		
		populate();
		
		nf = NumberFormat.getCurrencyInstance();
	}

	public ParkingLocationItemizedOverlay(Drawable defaultMarker, MapView mapView) 
	{
		super(boundCenterBottom(defaultMarker), mapView);
		c = mapView.getContext();
		setBalloonBottomOffset(defaultMarker.getIntrinsicHeight());
		//setShadow(true);
		populate();
		
		nf = NumberFormat.getCurrencyInstance();
	}
	
	public void addOverlay(OverlayItem overlay) {
	    m_overlays.add(overlay);
	    populate();
	}
	
	public void addOverlay(ParkingLocation pl) {
	    
		OverlayItem overlay = new OverlayItem(pl.getGeoPoint(), pl.getName(),	
                "Rate: "+ nf.format(pl.getRate()) + " " + pl.getRateTime() + "\n" +
				"Tuesday Hours: 9-6");
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
		//Toast.makeText(c, "onBalloonTap for overlay index " + index,
		//		Toast.LENGTH_LONG).show();
		return true;
	}
	
	protected boolean onNextClick(int index, OverlayItem item) {
		Intent info = new Intent(c, Information.class);
		
		info.putExtra("edu.pitt.designs1635.ParkIt.location", m_locations.get(index));
		
		c.startActivity(info);
		
		return true;
	}
	
	
}

