package com.example.nfccarddemo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ComSetting extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.com_setting);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.com_setting, menu);
		return true;
	}

}
