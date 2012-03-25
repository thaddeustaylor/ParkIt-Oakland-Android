package edu.pitt.designs1635.ParkIt;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Information extends Activity
{
	
	private ParkingLocation pl;
	
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);
        
        Bundle extras = getIntent().getExtras();
        
        pl = extras.getParcelable("edu.pitt.designs1635.ParkIt.location");
        
        TextView tv = (TextView) this.findViewById(R.id.name_info);
        EditText et;
        
        tv.setText(tv.getText() + pl.getName());
        
        //System.out.println(pl.getName());
    }
}