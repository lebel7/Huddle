package com.lebel.huddle;

import com.lebel.huddle.ActStaffList;
import com.lebel.huddle.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setupUiEvents();
	}

	private void setupUiEvents() {
		// TODO Auto-generated method stub
		Button viewStaffButton = (Button) findViewById(R.id.btnStaffList);
		viewStaffButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				handleBtnStaffList_Click((Button) v);
			}
		});
	}

	protected void handleBtnStaffList_Click(Button v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this.getApplicationContext(), ActStaffList.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}