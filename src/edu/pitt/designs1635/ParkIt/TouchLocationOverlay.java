package edu.pitt.designs1635.ParkIt;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class TouchLocationOverlay extends Overlay {

	private Context m_context;
	private GeoPoint m_point;
	
	public TouchLocationOverlay(Context context)
	{
		m_context = context;
	}

	
    @Override
    public boolean onTouchEvent(MotionEvent event, MapView mapView) 
    {
    	
        //---when user lifts his finger---
        if (event.getAction() == 1) {                
        	m_point = mapView.getProjection().fromPixels( (int) event.getX(),
            												 (int) event.getY());
        }                            
        return false;
		
    	
    }
        
    public GeoPoint getGeoPoint()
    {
		return m_point;
    }
    
}
