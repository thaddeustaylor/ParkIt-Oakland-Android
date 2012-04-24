package edu.pitt.designs1635.ParkIt;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.content.res.Resources;
import com.actionbarsherlock.app.SherlockActivity;


public class Tutorial extends SherlockActivity
{
	private ImageView tutImage;
	private Button btnNext, btnBack;
	private int counter = 0;
	private String path;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tutorial);
		tutImage = (ImageView) findViewById(R.id.tut_image);

		btnNext = (Button) findViewById(R.id.next_button);
		btnBack = (Button) findViewById(R.id.back_button);

		if (counter == 0)
			btnBack.setEnabled(false);

		btnNext.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v)
			{
				btnBack.setEnabled(true);
				if(counter < 6)
					counter++;
				switch (counter){
					case 1:
						tutImage.setImageResource(R.drawable.tut_0);
						break;
					case 2:
						tutImage.setImageResource(R.drawable.tut_1);
						break;
					case 3:
						tutImage.setImageResource(R.drawable.tut_2);
						break;
					case 4:
						tutImage.setImageResource(R.drawable.tut_3);
						break;
					case 5:
						tutImage.setImageResource(R.drawable.tut_4);
						btnNext.setText("Finish");
						break;
					case 6:
						finish();
					default:
						break;
				}

			}
		});

		btnBack.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v)
			{
				if(counter > 0)
					counter--;
				switch (counter){
					case 0:
						btnBack.setEnabled(false);
						tutImage.setImageResource(R.drawable.legend);
						break;
					case 1:
						tutImage.setImageResource(R.drawable.tut_0);
						break;
					case 2:
						tutImage.setImageResource(R.drawable.tut_1);
						break;
					case 3:
						tutImage.setImageResource(R.drawable.tut_2);
						break;
					case 4:
						tutImage.setImageResource(R.drawable.tut_3);
						btnNext.setText("Next");
						break;
					default:
						break;
				}

			}
		});
	}

	public void skip(View view) {
		finish();
	}

}