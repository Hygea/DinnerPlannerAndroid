package se.kth.csc.iprog.dinnerplanner;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ChooseDish extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_dish);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_choose_dish, menu);
		return true;
	}

}
