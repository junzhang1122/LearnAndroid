package com.example.android_data_time;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends Activity {
	Button button;
	Button Timebutton;
	TextView textView;
	private DatePickerDialog datePickerDialog;
	private TimePickerDialog timePickerDialog;
	private int year, monthOfYear, dayOfMonth;
	private int hourOfDay, minute;
	private boolean is24HourView = true;
	private String dateAndTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button = (Button) this.findViewById(R.id.button1);
		Timebutton = (Button) this.findViewById(R.id.button2);
		textView = (TextView) this.findViewById(R.id.textView1);
		Calendar calendar = Calendar.getInstance();
		year = calendar.get(Calendar.YEAR);
		monthOfYear = calendar.get(Calendar.MONTH);
		dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
		minute = calendar.get(Calendar.MINUTE);

		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				datePickerDialog.show();
				//textView.setText(dateAndTime);
			}
		});

		Timebutton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				timePickerDialog.show();
				//textView.setText(dateAndTime);
			}
		});

		datePickerDialog = new DatePickerDialog(MainActivity.this,
				new DatePickerDialog.OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker arg0, int arg1, int arg2,
							int arg3) {
						// TODO Auto-generated method stub
						year = arg1;
						monthOfYear = arg2 + 1;
						dayOfMonth = arg3;
						dateAndTime = year + "/" + monthOfYear + "/"
								+ dayOfMonth;
						dateAndTime = dateAndTime.toString();
						textView.setText(dateAndTime);
					}
				}, year, monthOfYear, dayOfMonth);

		timePickerDialog = new TimePickerDialog(MainActivity.this,
				new OnTimeSetListener() {

					@Override
					public void onTimeSet(TimePicker arg0, int arg1, int arg2) {
						// TODO Auto-generated method stub
						hourOfDay = arg1;
						minute = arg2;
						dateAndTime = hourOfDay + ":" + minute;
						textView.setText(dateAndTime);
					}
				}, hourOfDay, minute, is24HourView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
