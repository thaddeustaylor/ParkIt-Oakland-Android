package edu.pitt.designs1635.ParkIt;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class ParkingLocationOverlay extends Overlay {

	private GeoPoint m_point;
	public ParkingLocation info;
	
	public ParkingLocationOverlay(ParkingLocation loc)
	{
		info = loc;
		m_point = new GeoPoint(info.getLatitude(), info.getLongitude());
	}
	
	
	
    @Override
    public boolean draw(Canvas canvas, 
    					MapView mapView, 
    					boolean shadow, 
    					long when) 
    {
        super.draw(canvas, mapView, shadow);                   

        //---translate the GeoPoint to screen pixels---
        Point screenPts = new Point();
        mapView.getProjection().toPixels(m_point, screenPts);

        //---add the marker---
        Bitmap bmp = BitmapFactory.decodeResource(mapView.getResources(), R.drawable.pushpin);            
        canvas.drawBitmap(bmp, screenPts.x, screenPts.y-50, null);         
        return true;
    }
	
}
