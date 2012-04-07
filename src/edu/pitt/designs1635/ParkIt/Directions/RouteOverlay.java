package edu.pitt.designs1635.ParkIt.Directions;

import java.util.List;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class RouteOverlay extends Overlay {

	List<GeoPoint> m_route; 
	Paint m_paint;
    
    
	
	public RouteOverlay (List<GeoPoint> route)
	{
		m_route = route; 
	
		m_paint = new Paint();
		m_paint.setColor(Color.RED);
		m_paint.setStrokeWidth(5);
	    m_paint.setAlpha(75);//120
	    
	    //Log.i("RouteOverlay", "[" +  m_route.get(0).getLatitudeE6() + ", " + m_route.get(0).getLongitudeE6() + "]" + "[" + m_route.get(1).getLatitudeE6()+ ", " + m_route.get(1).getLongitudeE6() + "]");
        
	    
	}
	
	public int size()
	{
		return m_route.size();
	}
	
    @Override
    public boolean draw(Canvas canvas, 
    					MapView mapView,
    					boolean shadow, 
    					long when) 
    {
        super.draw(canvas, mapView, shadow);                   

        //---translate the GeoPoint to screen pixels---
        
        
        for (int i = 0; i < m_route.size() - 1; i++)
        {
        	Point startPt = new Point();
        	Point finishPt = new Point();
        
        	mapView.getProjection().toPixels(m_route.get(i), startPt);    
        	mapView.getProjection().toPixels(m_route.get(i + 1), finishPt);    
        	
        	canvas.drawLine(startPt.x, startPt.y, finishPt.x, finishPt.y, m_paint);
        	
        }
        
        return true;
        
    }
}
