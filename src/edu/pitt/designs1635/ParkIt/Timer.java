package edu.pitt.designs1635.ParkIt;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class Timer extends Activity
{
	private Spinner spinner, spinner2, spinner3;
	private int timerVal;

	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer);

        spinner = (Spinner) findViewById(R.id.spinner1);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
			this, R.array.intervals, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
				if(parent.getItemAtPosition(pos).toString().compareTo("120") == 0)
					timerVal = 120;
				else if(parent.getItemAtPosition(pos).toString().compareTo("90") == 0)
					timerVal =  90;
				else if(parent.getItemAtPosition(pos).toString().compareTo("60") == 0)
					timerVal =  60;
				else if(parent.getItemAtPosition(pos).toString().compareTo("45") == 0)
					timerVal =  45;
				else if(parent.getItemAtPosition(pos).toString().compareTo("30") == 0)
					timerVal =  30;
				else if(parent.getItemAtPosition(pos).toString().compareTo("15") == 0)
					timerVal =  15;
				else if(parent.getItemAtPosition(pos).toString().compareTo("5") == 0)
					timerVal =  5;
			}
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

		spinner2 = (Spinner) findViewById(R.id.spinner2);
		ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
			this, R.array.intervals, android.R.layout.simple_spinner_item);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner2.setAdapter(adapter2);
		spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
				if(parent.getItemAtPosition(pos).toString().compareTo("120") == 0)
					timerVal = 120;
				else if(parent.getItemAtPosition(pos).toString().compareTo("90") == 0)
					timerVal =  90;
				else if(parent.getItemAtPosition(pos).toString().compareTo("60") == 0)
					timerVal =  60;
				else if(parent.getItemAtPosition(pos).toString().compareTo("45") == 0)
					timerVal =  45;
				else if(parent.getItemAtPosition(pos).toString().compareTo("30") == 0)
					timerVal =  30;
				else if(parent.getItemAtPosition(pos).toString().compareTo("15") == 0)
					timerVal =  15;
				else if(parent.getItemAtPosition(pos).toString().compareTo("5") == 0)
					timerVal =  5;
			}
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

		spinner3 = (Spinner) findViewById(R.id.spinner3);
		ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(
			this, R.array.intervals, android.R.layout.simple_spinner_item);
		adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner3.setAdapter(adapter3);
		spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
				if(parent.getItemAtPosition(pos).toString().compareTo("120") == 0)
					timerVal = 120;
				else if(parent.getItemAtPosition(pos).toString().compareTo("90") == 0)
					timerVal =  90;
				else if(parent.getItemAtPosition(pos).toString().compareTo("60") == 0)
					timerVal =  60;
				else if(parent.getItemAtPosition(pos).toString().compareTo("45") == 0)
					timerVal =  45;
				else if(parent.getItemAtPosition(pos).toString().compareTo("30") == 0)
					timerVal =  30;
				else if(parent.getItemAtPosition(pos).toString().compareTo("15") == 0)
					timerVal =  15;
				else if(parent.getItemAtPosition(pos).toString().compareTo("5") == 0)
					timerVal =  5;
			}
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
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