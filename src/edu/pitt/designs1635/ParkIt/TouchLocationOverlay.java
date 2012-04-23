package edu.pitt.designs1635.ParkIt;

import android.content.Context;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;


public class TouchLocationOverlay extends Overlay {

	private Context m_context;
	private GeoPoint m_point;
	
	private TapListener m_tapListener = null;
	
	public TouchLocationOverlay(Context context)
	{
		m_context = context;
	}

	
    
    public boolean onTap(GeoPoint p, MapView mapView)
    {
    	

    	m_point = p;
    	
    	if(m_tapListener != null)
    	{
    		m_tapListener.onTap(p);
    	}
    	
		return true;
    	
    }
        
    public GeoPoint getGeoPoint()
    {
		return m_point;
    }
    
    
    /**
     * Interface for firing an event when the Overlay's onTap event is fired. 
     * @author Thaddeus
     *
     */
    public interface TapListener
    {
    	public abstract void onTap(GeoPoint p);
    }
    
    /**
     * Allow the TapListener to be set.
     * 
     * @param listener - the user implemented interface.
     */
    public void setTapListener(TapListener listener)
    {
    	m_tapListener = listener;
    }
}
