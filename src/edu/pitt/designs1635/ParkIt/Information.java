package edu.pitt.designs1635.ParkIt;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.util.Log;
import android.widget.Spinner;

import android.widget.EditText;
import android.widget.TextView;

public class Information extends Activity
{
	private ParkingLocation pl;
	private TextView nameValue, typeValue, paymentValue, limitValue, rateValue;
	private EditText nameValueEdit, limitValueEdit, rateValueEdit;
	private Spinner typeValueEdit, paymentValueEdit;
	private Button edit, save;
	private dbAdapter mDbHelper;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info);

		Bundle extras = getIntent().getExtras();

		pl = extras.getParcelable("edu.pitt.designs1635.ParkIt.location");

		nameValue = (TextView) this.findViewById(R.id.namevalue);
		nameValueEdit = (EditText) findViewById(R.id.namevalue_edit);
		typeValue = (TextView) this.findViewById(R.id.typevalue);
		paymentValue = (TextView) this.findViewById(R.id.paytypevalue);
		limitValue = (TextView) this.findViewById(R.id.limitvalue);
		limitValueEdit = (EditText) findViewById(R.id.limitvalue_edit);
		rateValue = (TextView) this.findViewById(R.id.ratevalue);
		rateValueEdit = (EditText) findViewById(R.id.ratevalue_edit);

		typeValueEdit = (Spinner) findViewById(R.id.tv_edit);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
			this, R.array.park_type, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		typeValueEdit.setAdapter(adapter);

		paymentValueEdit = (Spinner) findViewById(R.id.ptv_edit);
		ArrayAdapter<CharSequence> payment_adapter = ArrayAdapter.createFromResource(
			this, R.array.park_pay_type, android.R.layout.simple_spinner_item);
		payment_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		paymentValueEdit.setAdapter(payment_adapter);

		nameValue.setText(pl.getName());
		typeValue.setText(pl.getType().toString());
		paymentValue.setText(pl.getPayment().toString());
		if(pl.getLimit() == 0)
			limitValue.setText("No limit recorded");
		else
			limitValue.setText(String.valueOf(pl.getRate()));
		if(pl.getRate() == 0)
			rateValue.setText("No rate recorded");
		else
			rateValue.setText(String.valueOf(pl.getRate()));

		edit = (Button) findViewById(R.id.edit_button);
		save = (Button) findViewById(R.id.save_button);

		edit.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				nameValue.setVisibility(View.INVISIBLE);
				nameValueEdit.setVisibility(View.VISIBLE);
				nameValueEdit.setText(nameValue.getText());

				typeValue.setVisibility(View.INVISIBLE);
				typeValueEdit.setVisibility(View.VISIBLE);
				//typeValueEdit.setText(typeValue.getText());

				paymentValue.setVisibility(View.INVISIBLE);
				paymentValueEdit.setVisibility(View.VISIBLE);
				//paymentValueEdit.setText(paymentValue.getText());

				limitValue.setVisibility(View.INVISIBLE);
				limitValueEdit.setVisibility(View.VISIBLE);
				limitValueEdit.setText(limitValue.getText());

				rateValue.setVisibility(View.INVISIBLE);
				rateValueEdit.setVisibility(View.VISIBLE);
				rateValueEdit.setText(rateValue.getText());

				save.setEnabled(true);
				edit.setEnabled(false);
			}
		});

		mDbHelper = new dbAdapter(this);
		mDbHelper.open();

		save.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v)
			{
				ParkingLocation newPl = new ParkingLocation();
				newPl.setName(nameValueEdit.getText().toString());
				newPl.setType(typeValueEdit.getSelectedItem().toString());
				newPl.setPayment(paymentValueEdit.getSelectedItem().toString());
				if(limitValueEdit.getText().toString().compareTo("No limit recorded") != 0)
				{
					newPl.setLimit(Integer.parseInt(limitValueEdit.getText().toString()));
					limitValue.setText(limitValueEdit.getText().toString());
				}
				if(rateValueEdit.getText().toString().compareTo("No rate recorded") != 0)
				{
					newPl.setRate(Float.parseFloat(rateValueEdit.getText().toString()));
					rateValue.setText(rateValueEdit.getText().toString());
				}
				newPl.setLatitude(pl.getLatitude());
				newPl.setLongitude(pl.getLongitude());

				if(mDbHelper.deletePoint(pl.getLatitude(), pl.getLongitude()))
					Log.i("PARKIT INFO", "DELETE SUCCESSFUL");
				else
					Log.i("PARKIT INFO", "DELETE FAILUREEEEE");

				mDbHelper.addPoint(newPl);

				nameValue.setText(nameValueEdit.getText().toString());
				nameValue.setVisibility(View.VISIBLE);
				nameValueEdit.setVisibility(View.INVISIBLE);

				typeValue.setVisibility(View.VISIBLE);
				typeValueEdit.setVisibility(View.INVISIBLE);

				paymentValue.setVisibility(View.VISIBLE);
				paymentValueEdit.setVisibility(View.INVISIBLE);

				limitValue.setVisibility(View.VISIBLE);
				limitValueEdit.setVisibility(View.INVISIBLE);

				rateValue.setVisibility(View.VISIBLE);
				rateValueEdit.setVisibility(View.INVISIBLE);

				edit.setEnabled(true);
				save.setEnabled(false);
				mDbHelper.close();
				finish();
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
        mDbHelper.open();
    }
}