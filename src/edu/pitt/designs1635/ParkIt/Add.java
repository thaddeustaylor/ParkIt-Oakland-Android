package edu.pitt.designs1635.ParkIt;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.maps.GeoPoint;

public class Add extends Activity
{
	private ParkingLocation pl;
	private Button save, cancel;
	public final int ADD_ACTIVITY = 0;
    private SharedPreferences prefs;
    private EditText name, limit, rate;
    private Spinner type, payment;
    private dbAdapter mDbHelper;
    public static final int SAVE_TO_PHONE = 10;
    public static final int SAVE_TO_SERVER = 11;
    public final int SAVE_DIALOG = 1;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add);
		mDbHelper = new dbAdapter(this);

		//prefs = this.getSharedPreferences("parkItPrefs", Activity.MODE_PRIVATE);

		save = (Button) findViewById(R.id.save_button);
		cancel = (Button) findViewById(R.id.cancel_button);
		
		name = (EditText) findViewById(R.id.nv_add);

		type = (Spinner) findViewById(R.id.tv_add);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
			this, R.array.park_type, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		type.setAdapter(adapter);

		payment = (Spinner) findViewById(R.id.pt_add);
		ArrayAdapter<CharSequence> payment_adapter = ArrayAdapter.createFromResource(
			this, R.array.park_pay_type, android.R.layout.simple_spinner_item);
		payment_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		payment.setAdapter(payment_adapter);

		limit = (EditText) findViewById(R.id.lv_add);
		rate = (EditText) findViewById(R.id.rv_add);

		
		Bundle extras = getIntent().getExtras(); 
		pl = new ParkingLocation(extras.getInt("edu.pitt.designs1635.ParkIt.location.lat"), 
        								extras.getInt("edu.pitt.designs1635.ParkIt.location.long"));
		
      
		
		name.addTextChangedListener(new TextWatcher(){
			public void afterTextChanged(Editable s) {}
			public void beforeTextChanged(CharSequence s, int start, int count, int after){}
			public void onTextChanged(CharSequence s, int start, int before, int count){
				if(s.length() > 0)
					save.setEnabled(true);
				else
					save.setEnabled(false);
			}
		}); 

		save.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				//Intent intent = new Intent(getApplicationContext(), AddPointMapActivity.class);
            	            	
            	//intent.putExtra("edu.pitt.designs1635.ParkIt.center.lat", prefs.getInt("last_location_lat", 0));
            	//intent.putExtra("edu.pitt.designs1635.ParkIt.center.long", prefs.getInt("last_location_lon", 0));
            	
                //startActivityForResult(intent, ADD_ACTIVITY);
				showDialog(SAVE_DIALOG);
			}
		});
	
		cancel.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				finish();
			}
		});
	
	}

	
	
	  protected Dialog onCreateDialog(int id, Bundle b)
	    {
	    	//Toast.makeText(this, "in dialog " + id, Toast.LENGTH_SHORT).show();
			
	    	switch(id)
	    	{
	    	case SAVE_DIALOG:
	    		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		        
		        builder.setMessage("Do you want to save the new parking location to your phone only or to the public server?")
		               .setCancelable(false)
		               .setPositiveButton("Phone", new DialogInterface.OnClickListener() {
		                   public void onClick(DialogInterface dialog, int id) {
		                        saveLocation(SAVE_TO_PHONE);
		                   }
		               })
		               .setNegativeButton("Server", new DialogInterface.OnClickListener() {
		                   public void onClick(DialogInterface dialog, int id) {
		                	   saveLocation(SAVE_TO_SERVER);
		                   }
		               })
		               .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
		                       public void onClick(DialogInterface dialog, int id) {
		                            dialog.cancel();
		                            
		                       }
		                       
		               });
		        AlertDialog ad = builder.create();
		        return ad;
	    	}
		    
	    	return null;
	    	
	    }
	
	
	
	private void saveLocation(int saveCode)
	{
		
		pl.setName(name.getText().toString());
		pl.setType(type.getSelectedItem().toString());
		pl.setPayment(payment.getSelectedItem().toString());
		if(limit.getText().toString().compareTo("") != 0)
			pl.setLimit(Integer.parseInt(limit.getText().toString()));
		if(rate.getText().toString().compareTo("") != 0)
			pl.setRate(Float.parseFloat(rate.getText().toString()));
		
		
		mDbHelper.open();
			switch(saveCode)
			{
			case SAVE_TO_PHONE:
				mDbHelper.addPoint(pl);
				break;
			case SAVE_TO_SERVER:
				mDbHelper.addRemotePoint(pl);
				break;
			default:
				break;
			}
		finish();
	}

	@Override
    protected void onStop()
    {
	    super.onStop();
        mDbHelper.close();
    }
    
    @Override
    protected void onResume()
    {
	    super.onResume();
        mDbHelper.open();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        mDbHelper.close();
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
    	case android.R.id.home:
    		Intent intent = new Intent(this, ParkItActivity.class);
    		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    		startActivity(intent);
    		return true;
    	default:
    		return super.onOptionsItemSelected(item);
    		
    	}
    }
}