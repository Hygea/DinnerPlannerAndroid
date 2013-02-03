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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class IngredientsFragment extends Fragment {
	ListView dishesListView;
	TextView titleTextView;
	List<RowItem> rowItems;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_dish, container, false);
		
		dishesListView = (ListView) view.findViewById(R.id.list_dishes);
		titleTextView = (TextView) view.findViewById(R.id.choose_title);

		titleTextView.setText(ChooseDish.dishTitles.get(ChooseDish.currentDishType-1));

		return view;
	}

	
	
	


}
