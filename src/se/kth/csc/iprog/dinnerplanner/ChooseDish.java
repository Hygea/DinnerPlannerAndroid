package se.kth.csc.iprog.dinnerplanner;

import java.util.ArrayList;
import java.util.Set;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ChooseDish extends Activity {
	ListView list_dishes;
	Handler handler;
	ArrayList<String> dishList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_dish);


		list_dishes = (ListView) findViewById(R.id.list_dishes);
		dishList = new ArrayList<String>();
		
		DinnerModel model = ((DinnerPlannerApplication) this.getApplication()).getModel();
		Set<Dish> dishes = model.getDishes();
		
		for (Dish d : dishes) {
			dishList.add(d.getName());
		}
		
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
				Toast.makeText(getApplicationContext(), "Test dish:"+ dishList.get(position),
						Toast.LENGTH_SHORT).show();
			}
		});

	}

}
