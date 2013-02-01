package se.kth.csc.iprog.dinnerplanner;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ChooseDish extends Activity {
	ListView list_dishes;
	Handler handler;
	String[] dishList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_dish);


		list_dishes = (ListView) findViewById(R.id.list_dishes);
		dishList = new String[] { "Mat1", "Mat2", "Mat3" };

		handler = new Handler();
		
		createList();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_choose_dish, menu);
		return true;
	}



	public void createList() {
		ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, R.layout.item, R.id.item_text, dishList);  
		list_dishes.setAdapter(listAdapter);     
		list_dishes.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long id) {
				Toast.makeText(getApplicationContext(), "Test dish:"+ dishList[position],
						Toast.LENGTH_SHORT).show();
			}
		});

	}

}
