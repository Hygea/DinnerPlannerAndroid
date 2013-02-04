package se.kth.csc.iprog.dinnerplanner;

import java.util.List;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;


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
