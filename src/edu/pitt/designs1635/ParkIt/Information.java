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
        
        TextView tv1 = (TextView) this.findViewById(R.id.namevalue);
        TextView tv2 = (TextView) this.findViewById(R.id.typevalue);
        TextView tv3 = (TextView) this.findViewById(R.id.paytypevalue);
        TextView tv4 = (TextView) this.findViewById(R.id.limitvalue);
        TextView tv5 = (TextView) this.findViewById(R.id.ratevalue);
        
        //tv.setText(tv.getText() + pl.getName());
        
        tv1.setText(pl.getName());
        tv2.setText(pl.getType().toString());
        tv3.setText(pl.getPayment().toString());
        tv4.setText(""+pl.getLimit());
        tv5.setText(String.valueOf(pl.getRate()));
        
        //System.out.println(pl.getName());
    }
}