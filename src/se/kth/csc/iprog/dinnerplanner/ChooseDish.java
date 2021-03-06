package se.kth.csc.iprog.dinnerplanner;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.InputType;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class ChooseDish extends FragmentActivity implements Observer {
	Button leftButton;
	Button rightButton;

	public static int currentDishType = Dish.STARTER;
	public static ArrayList<String> dishTitles;
	Context context = this;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_dish);
		DinnerModel model = ((DinnerPlannerApplication) this.getApplication()).getModel();

		model.addObserver(this);
		
		Button guestsButton = (Button) findViewById(R.id.numberOfGuestsButton);
		leftButton = (Button) findViewById(R.id.button_left);
		rightButton = (Button) findViewById(R.id.button_right);
		Button ingredients = (Button) findViewById(R.id.ingredientslist);
		
		// Ladda total price, b�r senare uppdateras vid select av dish samt �ndring av guests
		Intent intent = getIntent();
		int guests = intent.getIntExtra("guests", -1);
		
		updateGuests(guests);
		
		
		guestsButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showPopUp();
			}
		});


		// TODO: Knapp f�r Ingredients och de andra

		dishTitles = new ArrayList<String>();
		dishTitles.add(getString(R.string.startDish));
		dishTitles.add(getString(R.string.mainDish));
		dishTitles.add(getString(R.string.dessertDish));
		dishTitles.add(getString(R.string.prepare));

		final FragmentManager fm = getSupportFragmentManager();
		Fragment fragment = fm.findFragmentById(R.id.frame);
		ingredients.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(ChooseDish.this, Ingredients.class);
				ChooseDish.this.startActivity(myIntent);
			}
		});



		leftButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (currentDishType!=Dish.STARTER) {
					// Uppdatera listviewn i ChooseDishFragment genom att ladda om Fragmenten
					rightButton.setVisibility(View.VISIBLE);
					currentDishType--;
					FragmentTransaction ft = fm.beginTransaction();
					ft.replace(R.id.frame, new ChooseDishFragment(context)); 
					ft.commit();
				}
				else {
					// G�r tillbaka till startsidan
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
					ft.replace(R.id.frame, new ChooseDishFragment(context));
					ft.commit();				  
				}
				else {
					// Nu �r vi vid Preparation
					rightButton.setVisibility(View.INVISIBLE);
					//TODO: Check if at least 1 dish has been chosen
					FragmentTransaction ft = fm.beginTransaction();
					ft.replace(R.id.frame, new PrepareFragment());
					ft.commit();	
				}
			}
		});


		// F�rsta fragmenten som laddas n�r man startar ChooseDish-activityn
		if (fragment == null) {
			FragmentTransaction ft = fm.beginTransaction();
			ft.add(R.id.frame, new ChooseDishFragment(context));
			ft.commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_choose_dish, menu);
		return true;
	}

	private void showPopUp() {
		AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
		helpBuilder.setTitle("Guests");
		helpBuilder.setMessage("Enter Number of Guests");
		final EditText input = new EditText(this);
		input.setSingleLine();
		input.setText("");
		helpBuilder.setView(input);
		input.setInputType(InputType.TYPE_CLASS_NUMBER);
		helpBuilder.setPositiveButton("OK",
				new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Editable t = input.getText();
				String s = t.toString();
				if(s.equals("")){
					s = "1";
				}
				int guests = Integer.parseInt(s);
				updateGuests(guests);
				
			}
		});

		// Remember, create doesn't show the dialog
		AlertDialog helpDialog = helpBuilder.create();
		helpDialog.show();
	}
	
	private void updateGuests(int g){
		// Ladda total price, b�r senare uppdateras vid select av dish samt �ndring av guests
		DinnerModel model = ((DinnerPlannerApplication) this.getApplication()).getModel();
		model.setNumberOfGuests(g);
	}
	
	@Override
	public void update(Observable observable, Object data) {
		DinnerModel model = ((DinnerPlannerApplication) this.getApplication()).getModel();
		Button guestsButton = (Button) findViewById(R.id.numberOfGuestsButton);

		float price = (float) model.getTotalMenuPrice();
		String s = Float.toString(price);
		s ='$'+s;

		TextView totalcost = (TextView)findViewById(R.id.textView1);
		totalcost.setText(s);

		String guestsString = Integer.toString(model.getNumberOfGuests());
		guestsButton.setText(guestsString);		
	}
}
