package se.kth.csc.iprog.dinnerplanner;

import java.util.ArrayList;
import java.util.List;
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
import android.widget.ListView;
import android.widget.Toast;


public class ChooseDish extends Activity {
	ListView dishesListView;
	Handler handler;	
	List<RowItem> rowItems;
	int currentDishType;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_dish);
		
		dishesListView = (ListView) findViewById(R.id.list_dishes);
		currentDishType = Dish.STARTER;
		handler = new Handler();

		createList();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_choose_dish, menu);
		return true;
	}
	
	

	public void getRowItems() {
		DinnerModel model = ((DinnerPlannerApplication) this.getApplication()).getModel();
	    Set<Dish> dishes = model.getDishesOfType(currentDishType);
	    rowItems = new ArrayList<RowItem>();
		for (Dish d : dishes) {
			String imageString = d.getImage();
			if (!imageString.contains("drawable/"))
				imageString = "drawable/"+imageString;
			imageString = imageString.replace(".jpg", "");	
			Integer image = getResources().getIdentifier(imageString, null, getPackageName()); 	// F�r att ta fram imagestr�ngen som en int till rowItem
			RowItem item = new RowItem(image, d.getName(), d.getDescription());		// L�gga in all information som ska visas i listan i en anpassad RowItem
			rowItems.add(item);
		}
	}
	
	public void createList() {
		getRowItems();
		CustomListViewAdapter listAdapter = new CustomListViewAdapter(this, R.layout.item, rowItems);  
		dishesListView.setAdapter(listAdapter);     
		dishesListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long id) {
				Toast.makeText(getApplicationContext(), "Clicked: "+ rowItems.get(position).getTitle(),
						Toast.LENGTH_SHORT).show();
			}
		});

	}

}
