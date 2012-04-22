package edu.pitt.designs1635.ParkIt;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Information extends Activity
{
	private ParkingLocation pl;
	private TextView typeView, nameValue, rateValue, paytypeValue, limitValue, hoursView;
	private EditText nameValueEdit, rateValueEdit, limitValueEdit;
	private Spinner paytypeValueEdit;
	private Button edit, save, goto_button, cancel_button;
	private dbAdapter mDbHelper;
	private ArrayAdapter<CharSequence> adapter, payment_adapter;

	public static final int SAVE_TO_PHONE = 10;
	public static final int SAVE_TO_SERVER = 11;
	public static final int GOTO_TRUE = 12;
	public static final int GOTO_FALSE = 13;
	
	public final int SAVE_DIALOG = 1;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info);

		mDbHelper = new dbAdapter(this);
		mDbHelper.open();

		Bundle extras = getIntent().getExtras();

		pl = extras.getParcelable("edu.pitt.designs1635.ParkIt.location.info");

		typeView = (TextView)findViewById(R.id.typeview);
		nameValue = (TextView)findViewById(R.id.namevalue);
		rateValue = (TextView)findViewById(R.id.ratevalue);
		paytypeValue = (TextView)findViewById(R.id.paytypevalue);
		limitValue = (TextView)findViewById(R.id.limitvalue);
		hoursView = (TextView)findViewById(R.id.hoursview);
		nameValueEdit = (EditText)findViewById(R.id.namevalue_edit);
		rateValueEdit = (EditText)findViewById(R.id.ratevalue_edit);
		limitValueEdit = (EditText)findViewById(R.id.limitvalue_edit);

		typeView.setText(pl.getType().toString());

		paytypeValueEdit = (Spinner) findViewById(R.id.ptv_edit);
		payment_adapter = ArrayAdapter.createFromResource(
			this, R.array.park_pay_type, android.R.layout.simple_spinner_item);
		payment_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		paytypeValueEdit.setAdapter(payment_adapter);

		nameValue.setText(pl.getName());
		paytypeValue.setText(pl.getPayment().toString());

		if(pl.getLimit() == 0)
			limitValue.setText("No time limit recorded");
		else
			limitValue.setText(String.valueOf(pl.getLimit()));

		if(pl.getRate() == 0)
			rateValue.setText("No rate recorded");
		else
			rateValue.setText(String.valueOf(pl.getRate()));

		edit = (Button) findViewById(R.id.edit_button);
		save = (Button) findViewById(R.id.save_button);
		goto_button = (Button) findViewById(R.id.goto_button);
		cancel_button = (Button) findViewById(R.id.cancel_button);

		edit.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				nameValue.setVisibility(View.INVISIBLE);
				nameValueEdit.setVisibility(View.VISIBLE);
				nameValueEdit.setText(nameValue.getText());

				paytypeValue.setVisibility(View.INVISIBLE);
				paytypeValueEdit.setVisibility(View.VISIBLE);

				limitValue.setVisibility(View.INVISIBLE);
				limitValueEdit.setVisibility(View.VISIBLE);
				limitValueEdit.setText(limitValue.getText());

				rateValue.setVisibility(View.INVISIBLE);
				rateValueEdit.setVisibility(View.VISIBLE);
				rateValueEdit.setText(rateValue.getText());

				//typeValueEdit.setSelection(adapter.getPosition(typeValue.getText()));
				paytypeValueEdit.setSelection(payment_adapter.getPosition(paytypeValue.getText()));

				save.setEnabled(true);
				edit.setEnabled(false);
			}
		});
		
		goto_button.setEnabled(true);
		goto_button.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				Intent intent = new Intent();
				
				intent.putExtra("edu.pitt.designs1635.ParkIt.Information.info", pl);
				
				setResult(GOTO_TRUE, intent);
				finish();
			}
		});

		save.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v)
			{
				showDialog(SAVE_DIALOG);			
			}
		});
	}

	@Override
	protected void onStop()
	{
		super.onStop();
		mDbHelper.close();
	}

	@Override
	protected void onPause()
	{
		super.onPause();
		mDbHelper.close();
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		mDbHelper = new dbAdapter(this);
		mDbHelper.open();
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		mDbHelper.close();
	}
	
	protected Dialog onCreateDialog(int id, Bundle b)
	{
		//Toast.makeText(this, "in dialog " + id, Toast.LENGTH_SHORT).show();
		
		switch(id)
		{
		case SAVE_DIALOG:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			
			builder.setMessage("Do you want to save the changes to your phone only or to the public server?")
				   .setCancelable(false)
				   .setPositiveButton("Phone", new DialogInterface.OnClickListener() {
					   public void onClick(DialogInterface dialog, int id) {
							saveChanges(SAVE_TO_PHONE);
					   }
				   })
				   .setNegativeButton("Server", new DialogInterface.OnClickListener() {
					   public void onClick(DialogInterface dialog, int id) {
						   saveChanges(SAVE_TO_SERVER);
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
	
	private void saveChanges(int whereToSave)
	{
		ParkingLocation newPl = new ParkingLocation();
		newPl.setName(nameValueEdit.getText().toString());
		newPl.setType(pl.getType());
		newPl.setPayment(paytypeValueEdit.getSelectedItem().toString());
		if(limitValueEdit.getText().toString().compareTo("No time limit recorded") != 0)
		{
			newPl.setLimit((int)Float.parseFloat(limitValueEdit.getText().toString()));
		}
		if(rateValueEdit.getText().toString().compareTo("No rate recorded") != 0)
		{
			newPl.setRate(Float.parseFloat(rateValueEdit.getText().toString()));
		}
		newPl.setLatitude(pl.getLatitude());
		newPl.setLongitude(pl.getLongitude());

		if(mDbHelper.deletePoint(pl.getLatitude(), pl.getLongitude()))
			Log.i("PARKIT INFO", "DELETE SUCCESSFUL");
		else
			Log.i("PARKIT INFO", "DELETE FAILUREEEEE");
		
		switch(whereToSave)
		{
		case SAVE_TO_PHONE:
			mDbHelper.addPoint(newPl);
			break;
		case SAVE_TO_SERVER:
			mDbHelper.addRemotePoint(newPl);
			break;
		default:
			break;
		}

		//mDbHelper.close();
		finish();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			Intent intent = new Intent(this, ParkItActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			mDbHelper.close();
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);	
		}
	}
}