package se.kth.csc.iprog.dinnerplanner;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new View.OnClickListener() {
			 @Override
			public void onClick(View v) {
				  showPopUp2();
			}
		});
		//To get the dinner model you can use the following code:
		DinnerModel model = ((DinnerPlannerApplication) this.getApplication()).getModel();
	
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	private void showPopUp2() {
		 AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
		 helpBuilder.setTitle("Guests");
		 helpBuilder.setMessage("Enter Number of Guests");
		 final EditText input = new EditText(this);
		 input.setSingleLine();
		 input.setText("");
		 helpBuilder.setView(input);
		 input.setInputType(InputType.TYPE_CLASS_NUMBER);
		 helpBuilder.setPositiveButton("Next",
		   new DialogInterface.OnClickListener() {

		    @Override
			public void onClick(DialogInterface dialog, int which) {
		    	Editable t = input.getText();
		    	String s = t.toString();
		    	int guests = Integer.parseInt(s);

		    	Intent myIntent = new Intent(MainActivity.this, ChooseDish.class);
		    	myIntent.putExtra("guests", guests);
		    	MainActivity.this.startActivity(myIntent);		    }
		   });

		 // Remember, create doesn't show the dialog
		 AlertDialog helpDialog = helpBuilder.create();
		 helpDialog.show();

		}

}
