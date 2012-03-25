package edu.pitt.designs1635.ParkIt;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Intent;

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

		TextView nameValue = (TextView) this.findViewById(R.id.namevalue);
		TextView typeValue = (TextView) this.findViewById(R.id.typevalue);
		TextView paymentValue = (TextView) this.findViewById(R.id.paytypevalue);
		TextView limitValue = (TextView) this.findViewById(R.id.limitvalue);
		TextView rateValue = (TextView) this.findViewById(R.id.ratevalue);

		nameValue.setText(pl.getName());
		typeValue.setText(pl.getType().toString());
		paymentValue.setText(pl.getPayment().toString());
		if(pl.getLimit() == 0)
		limitValue.setText("No limit recorded");
		else
		limitValue.setText(""+pl.getLimit());
		if(pl.getRate() == 0)
		rateValue.setText("No rate recorded");
		else
		rateValue.setText(String.valueOf(pl.getRate()));

		final Button edit = (Button) findViewById(R.id.edit_button);
			edit.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				startActivity(new Intent(this, Edit.class));
			}
		});
	}
}