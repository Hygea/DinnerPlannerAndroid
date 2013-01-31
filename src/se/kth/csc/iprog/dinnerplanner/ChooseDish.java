package se.kth.csc.iprog.dinnerplanner;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

public class ChooseDish extends Activity {
	ListView listDishes;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_dish);
		
		
		listDishes = (ListView) findViewById(R.id.imageView1);
		String[] values = new String[] { "Mat1", "Mat2", "Mat3" };
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_choose_dish, menu);
		return true;
	}
	
	
	
	

}
