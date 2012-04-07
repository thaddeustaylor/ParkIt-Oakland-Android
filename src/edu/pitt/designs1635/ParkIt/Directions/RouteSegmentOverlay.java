package edu.pitt.designs1635.ParkIt.Directions;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class RouteSegmentOverlay extends Overlay {

	GeoPoint m_start; 
	GeoPoint m_finish;
	
	public RouteSegmentOverlay (GeoPoint start, GeoPoint finish)
	{
		m_start = start;
		m_finish = finish;
		
	}
	
    @Override
    public boolean draw(Canvas canvas, 
    					MapView mapView,
    					boolean shadow, 
    					long when) 
    {
        super.draw(canvas, mapView, shadow);                   

        //---translate the GeoPoint to screen pixels---
        Point startPt = new Point();
        Point finishPt = new Point();
        
        mapView.getProjection().toPixels(m_start, startPt);    
        mapView.getProjection().toPixels(m_finish, finishPt);    

        //Bitmap bmp = BitmapFactory.decodeResource(m_context.getResources(), R.drawable.g_icon); 
        
        Paint paint = new Paint(Color.BLUE);
        
        paint.setStrokeWidth(5);
        paint.setAlpha(50);//120
        
        canvas.drawLine(startPt.x, startPt.y, finishPt.x, finishPt.y, paint);
        return true;
        
    }
}
