package se.kth.csc.iprog.dinnerplanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;


public class PrepareFragment extends Fragment {
	ListView dishesListView;
	TextView titleTextView;
	List<RowItem> rowItems;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_preparations, container, false);
		
		dishesListView = (ListView) view.findViewById(R.id.list_dishes);
		titleTextView = (TextView) view.findViewById(R.id.choose_title);

		titleTextView.setText(ChooseDish.dishTitles.get(ChooseDish.currentDishType-1)); // "Preparation"
		createList();
		
		return view;
	}

	public void getRowItems() {
		DinnerModel model = ((DinnerPlannerApplication) this.getActivity().getApplicationContext()).getModel();
	    Set<Dish> dishes = model.getFullMenu(); // Hämtar hela menyn
	    rowItems = new ArrayList<RowItem>();
		for (Dish d : dishes) {
			String imageString = d.getImage();
			if (!imageString.contains("drawable/"))
				imageString = "drawable/"+imageString;
			imageString = imageString.replace(".jpg", "");	
			Integer image = getResources().getIdentifier(imageString, null, this.getActivity().getApplicationContext().getPackageName()); 	// Får att ta fram imagestrŠngen som en int till rowItem
			RowItem item = new RowItem(image, d.getName(), d.getDescription());		// LŠgga in all information som ska visas i listan i en anpassad RowItem
			rowItems.add(item);
		}
	}
	
	public void createList() {
		getRowItems();
		Prepareadapter listAdapter = new Prepareadapter (this.getActivity().getApplicationContext(), R.layout.item_prepare, rowItems);  
		dishesListView.setAdapter(listAdapter);     
		dishesListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long id) {
				/*Toast.makeText(getApplicationContext(), "Clicked: "+ rowItems.get(position).getTitle(),
						Toast.LENGTH_SHORT).show();*/
			}
		});

	}

}
