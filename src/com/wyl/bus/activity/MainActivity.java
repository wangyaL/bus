package com.wyl.bus.activity;

import com.wyl.bus.R;
import com.wyl.bus.R.layout;
import com.wyl.bus.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}
	@Override  
	public boolean onCreateOptionsMenu(Menu menu) {  
	    getMenuInflater().inflate(R.menu.main, menu);
	    return true;  
	}  

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			Toast.makeText(this, "home", 1).show();
			return true;
		
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
