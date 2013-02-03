package se.kth.csc.iprog.dinnerplanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class ChooseDish extends FragmentActivity {
	Button leftButton;
	Button rightButton;
	
	public static int currentDishType = Dish.STARTER;
	public static ArrayList<String> dishTitles;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_dish);
		
		leftButton = (Button) findViewById(R.id.button_left);
		rightButton = (Button) findViewById(R.id.button_right);
		
		// TODO: Knapp fšr Ingredients och de andra
		
		dishTitles = new ArrayList<String>();
		dishTitles.add(getString(R.string.startDish));
		dishTitles.add(getString(R.string.mainDish));
		dishTitles.add(getString(R.string.dessertDish));
		dishTitles.add(getString(R.string.prepare));

		final FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.frame);
        
        
        
        
		leftButton.setOnClickListener(new View.OnClickListener() {
			 @Override
			public void onClick(View v) {
				 if (currentDishType!=Dish.STARTER) {
					// Uppdatera listviewn i ChooseDishFragment genom att ladda om Fragmenten
					 rightButton.setVisibility(View.VISIBLE);
					 currentDishType--;
					 FragmentTransaction ft = fm.beginTransaction();
			         ft.replace(R.id.frame, new ChooseDishFragment()); 
			         ft.commit();
				 }
				 else {
					 // GŒr tillbaka till startsidan
					 Intent myIntent = new Intent(ChooseDish.this, MainActivity.class);
					 ChooseDish.this.startActivity(myIntent);					 }
			}
		});
		rightButton.setOnClickListener(new View.OnClickListener() {
			 @Override
			public void onClick(View v) {
				 currentDishType++;
				// Uppdatera listviewn i ChooseDishFragment genom att ladda om Fragmenten
				 if (currentDishType<=Dish.DESERT) { 
					 FragmentTransaction ft = fm.beginTransaction();
			         ft.replace(R.id.frame, new ChooseDishFragment());
			         ft.commit();				  
			     }
				 else {
					// Nu Šr vi vid Preparation
					 rightButton.setVisibility(View.INVISIBLE);
					 //TODO: Check if at least 1 dish has been chosen
					 FragmentTransaction ft = fm.beginTransaction();
			         ft.replace(R.id.frame, new PrepareFragment());
			         ft.commit();	
				 }
			}
		});
		
		
		// Fšrsta fragmenten som laddas nŠr man startar ChooseDish-activityn
        if (fragment == null) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.frame, new ChooseDishFragment());
            ft.commit();
        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_choose_dish, menu);
		return true;
	}
	

}
