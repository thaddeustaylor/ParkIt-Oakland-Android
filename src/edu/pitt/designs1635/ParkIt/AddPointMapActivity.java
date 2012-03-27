package edu.pitt.designs1635.ParkIt;

import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockMapActivity;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

import edu.pitt.designs1635.ParkIt.TouchLocationOverlay.TapListener;


public class AddPointMapActivity extends SherlockMapActivity {
    

    MapView mapView;
    GeoPoint m_point;
    
    public static final int SAVE_TO_PHONE = 10;
    public static final int SAVE_TO_SERVER = 11;
    public final int SAVE_DIALOG = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ActionBar ab = getSupportActionBar();
		ab.setTitle(R.string.add_title);
        
        mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
        
        //TODO get the type of point being added
        //TODO add a icon with the point.
        Bundle extras = getIntent().getExtras(); 
        GeoPoint center = new GeoPoint(extras.getInt("edu.pitt.designs1635.ParkIt.center.lat"), 
        								extras.getInt("edu.pitt.designs1635.ParkIt.center.long"));
        

        //mapView.setOnTouchListener(new PointListener());
        
        

        MapController mapCtrl = mapView.getController();

     
        mapView.getMapCenter();

        TouchLocationOverlay plo = new TouchLocationOverlay(this);
        
        plo.setTapListener(new TapListener(){

			public void onTap(GeoPoint p) {
				m_point = p;
				
				showDialog(SAVE_DIALOG);
				
			}
        	
        });
        
        List<Overlay> points = mapView.getOverlays();
        points.clear();
        points.add(plo);

        mapCtrl.animateTo(center);
        mapCtrl.setZoom(17);        
    }
    
    
    private void returnToMain(int saveLocation)
    {
    	Intent intent = new Intent();
    	
    	intent.putExtra("edu.pitt.designs1635.ParkIt.Add.lat", m_point.getLatitudeE6());
    	intent.putExtra("edu.pitt.designs1635.ParkIt.Add.long", m_point.getLongitudeE6());
    	
    	setResult(saveLocation, intent);
    	finish();
    }
    
    
    protected Dialog onCreateDialog(int id, Bundle b)
    {
    	Toast.makeText(this, "in dialog " + id, Toast.LENGTH_SHORT).show();
		
    	switch(id)
    	{
    	case SAVE_DIALOG:
    		AlertDialog.Builder builder = new AlertDialog.Builder(this);
	        
	        builder.setMessage("Do you want to save the new parking location to your phone only or to the public server?")
	               .setCancelable(false)
	               .setPositiveButton("Phone", new DialogInterface.OnClickListener() {
	                   public void onClick(DialogInterface dialog, int id) {
	                        returnToMain(SAVE_TO_PHONE);
	                   }
	               })
	               .setNegativeButton("Server", new DialogInterface.OnClickListener() {
	                   public void onClick(DialogInterface dialog, int id) {
	                	   returnToMain(SAVE_TO_SERVER);
	                   }
	               })
	               .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
	                       public void onClick(DialogInterface dialog, int id) {
	                            dialog.cancel();
	                       }
	                       
	               });
	        return builder.create();
	       
    	}
	    
    	return null;
    	
    }
    
    
    @Override
    protected void onStop()
    {
	    super.onStop();

    }
    
    @Override
    protected void onResume()
    {
	    super.onResume();

    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	
	
    private class PointListener implements OnTouchListener
    {

		public boolean onTouch(View view, MotionEvent event) {
			switch(event.getAction())
			{
				//case MotionEvent.ACTION_MOVE: 	
					//view.dispatchTouchEvent(event);
					//for (int i = 0; i < event.getHistorySize(); i ++)
					//	mdv.setCoord(event.getHistoricalX(0, i), event.getHistoricalY(0, i)); 
					
					//mdv.setCoord(event.getX(0), event.getY(0));
					//mdv.invalidate();
					//return true;
					//break;
				//case MotionEvent.ACTION_DOWN: 	
					//view.dispatchTouchEvent(event);
					//mdv.startLine(event.getX(0), event.getY(0));
					//mdv.invalidate();
					//Toast.makeText(view.getContext(), "Hi there", Toast.LENGTH_SHORT).show();
					//return true;
					//break;
				
				case MotionEvent.ACTION_UP:		
					//mdv.endLine();
					m_point = mapView.getProjection().fromPixels( (int) event.getX(),
							 (int) event.getY());
					
					//Toast.makeText(view.getContext(), "From AddPointMApActivity " + m_point.getLatitudeE6() + ", " +
					//		m_point.getLongitudeE6(),	Toast.LENGTH_LONG).show();
		
					
					showDialog(SAVE_DIALOG);
					
					return true;
					//break;
				
			}
			
			return true;
			
		}
    	
    }


}