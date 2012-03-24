package edu.pitt.designs1635.ParkIt;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.MotionEvent;
import android.widget.Toast;
import android.content.Context;

import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class ParkingLocationOverlay extends Overlay {

	private Context m_context;
	private ParkingLocation m_info;
	
	
	public ParkingLocationOverlay(Context context, ParkingLocation loc)
	{
		m_context = context;
		m_info = loc;	
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
        mapView.getProjection().toPixels(m_info.getGeoPoint(), screenPts);

        //---add the marker---
        Bitmap bmp = BitmapFactory.decodeResource(m_context.getResources(), R.drawable.pushpin_small); 
        
        canvas.drawBitmap(bmp, screenPts.x, screenPts.y - 50, null);         

        return true;
        
    }
	
    @Override
    public boolean onTouchEvent(MotionEvent event, MapView mapView) 
    {
    	Toast.makeText(m_context, "hi", Toast.LENGTH_SHORT).show();
    	
		return true;   
    	
    }
        
    public ParkingLocation getLocationInfo()
    {
    	return m_info;
    }
    
    public void setLocationInfo(ParkingLocation pl)
    {
    	m_info = pl;	
    }
}
