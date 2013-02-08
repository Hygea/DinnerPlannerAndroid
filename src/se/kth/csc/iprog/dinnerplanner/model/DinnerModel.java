package se.kth.csc.iprog.dinnerplanner.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import java.util.Observable;

public class DinnerModel extends Observable implements IDinnerModel  {
	int guests;
	public ArrayList<Dish> menu = new ArrayList<Dish>();
	
	@Override
	public int getNumberOfGuests(){
		return guests;
	}
	
	
	public void setNumberOfGuests(int numberOfGuests){
		guests = numberOfGuests;
		setChanged();
		notifyObservers();
	};

	
	/**
	 * Returns the dish that is on the menu for selected type (1 = starter, 2 = main, 3 = desert).
	 */
	@Override
	public Dish getSelectedDish(int type){		// OBS! Lär returnera null om menu är tom. Snyggare lösning behövs.
		for (int i=0; i < menu.size(); i++) {
			if (menu.get(i).getType() == type)
				return menu.get(i);
		}
			return null;
	};
	
	/**
	 * Returns all the dishes on the menu.
	 */
	@Override
	public Set<Dish> getFullMenu(){
		Set<Dish> result = new HashSet<Dish>();
		for(Dish d : menu){
			result.add(d);
		}
		return result;
	};
	
	public void addToMenu(Dish d) {
		menu.add(d);
		setChanged();
		notifyObservers();
	}
	public void removeFromMenu(Dish d) {
		menu.remove(d);
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Returns all ingredients for all the dishes on the menu.
	 */
	@Override
	public Set<Ingredient> getAllIngredients(){
		Set<Ingredient> result = new HashSet<Ingredient>();
		for(Dish d: menu){
			Set<Ingredient> dishIngredients = d.getIngredients();
			for(Ingredient i : dishIngredients){
				//for(Ingredient iInSet : result){
				//	if(i.getName().equals(iInSet.getName())){ // ingredient already exists in the result
				//		double quantity = i.getQuantity();
				//		iInSet.setQuantity(iInSet.getQuantity()+quantity);
				//	} else{
				//		result.add(i);
				//	}
				//}
				result.add(i); // testing, tar inte hänsyn till om ingrediens redan finns tillagd
			}
		}
		return result;
	};
	
	/**
	 * Returns the total price of the menu (all the ingredients multiplied by number of guests).
	 */
	@Override
	public float getTotalMenuPrice(){
		Set<Ingredient> allIngredients = getAllIngredients();
		double price = 0;
		float totalprice = 0;
		guests = getNumberOfGuests();
		
		for(Ingredient i : allIngredients){
			price = i.getPrice();
			totalprice += price;
		}
		totalprice = totalprice * guests;
		return totalprice;
	};

	

	Set<Dish> dishes = new HashSet<Dish>();
	
	/**
	 * TODO: For Lab2 you need to implement the IDinnerModel interface.
	 * When you do this you will have all the needed fields and methods
	 * for the dinner planner (number of guests, selected dishes, etc.). 
	 */
	
	
	/**
	 * The constructor of the overall model. Set the default values here
	 */
	public DinnerModel(){
		
		//Adding some example data, you can add more
		Dish dish1 = new Dish("French toast",Dish.STARTER,"toast.jpg","In a large mixing bowl, beat the eggs. Add the milk, brown sugar and nutmeg; stir well to combine. Soak bread slices in the egg mixture until saturated. Heat a lightly oiled griddle or frying pan over medium high heat. Brown slices on both sides, sprinkle with cinnamon and serve hot.");
		Ingredient dish1ing1 = new Ingredient("egg",0.5,"",1);
		Ingredient dish1ing2 = new Ingredient("milk",30,"ml",6);
		Ingredient dish1ing3 = new Ingredient("brown sugar",7,"g",1);
		Ingredient dish1ing4 = new Ingredient("ground nutmeg",0.5,"g",12);
		Ingredient dish1ing5 = new Ingredient("white bread",2,"slices",2);
		dish1.addIngredient(dish1ing1);
		dish1.addIngredient(dish1ing2);
		dish1.addIngredient(dish1ing3);
		dish1.addIngredient(dish1ing4);
		dish1.addIngredient(dish1ing5);
		dishes.add(dish1);
		
		Dish dish2 = new Dish("Meat balls",Dish.MAIN,"meatballs.jpg","Preheat an oven to 400 degrees F (200 degrees C). Place the beef into a mixing bowl, and season with salt, onion, garlic salt, Italian seasoning, oregano, red pepper flakes, hot pepper sauce, and Worcestershire sauce; mix well. Add the milk, Parmesan cheese, and bread crumbs. Mix until evenly blended, then form into 1 1/2-inch meatballs, and place onto a baking sheet. Bake in the preheated oven until no longer pink in the center, 20 to 25 minutes.");
		Ingredient dish2ing1 = new Ingredient("extra lean ground beef",115,"g",20);
		Ingredient dish2ing2 = new Ingredient("sea salt",0.7,"g",3);
		Ingredient dish2ing3 = new Ingredient("small onion, diced",0.25,"",2);
		Ingredient dish2ing4 = new Ingredient("garlic salt",0.6,"g",3);
		Ingredient dish2ing5 = new Ingredient("Italian seasoning",0.3,"g",3);
		Ingredient dish2ing6 = new Ingredient("dried oregano",0.3,"g",3);
		Ingredient dish2ing7 = new Ingredient("crushed red pepper flakes",0.6,"g",3);
		Ingredient dish2ing8 = new Ingredient("Worcestershire sauce",16,"ml",7);
		Ingredient dish2ing9 = new Ingredient("milk",20,"ml",4);
		Ingredient dish2ing10 = new Ingredient("grated Parmesan cheese",5,"g",8);
		Ingredient dish2ing11 = new Ingredient("seasoned bread crumbs",115,"g",4);
		dish2.addIngredient(dish2ing1);
		dish2.addIngredient(dish2ing2);
		dish2.addIngredient(dish2ing3);
		dish2.addIngredient(dish2ing4);
		dish2.addIngredient(dish2ing5);
		dish2.addIngredient(dish2ing6);
		dish2.addIngredient(dish2ing7);
		dish2.addIngredient(dish2ing8);
		dish2.addIngredient(dish2ing9);
		dish2.addIngredient(dish2ing10);
		dish2.addIngredient(dish2ing11);
		dishes.add(dish2);
		
		Dish dish3 = new Dish("Baked Brie", Dish.DESERT, "bakedbrie.jpg", "Place on a nice plate. Cut up the cheese. Sprinkle some sea salt over the cheese. Savour with care!");
		Ingredient dish3ing1 = new Ingredient("Brie cheese", 20, "g", 10);
		Ingredient dish3ing2 = new Ingredient("sea salt",0.2,"g",3);
		Ingredient dish3ing3 = new Ingredient("Crackers", 6, "", 0.5);
		dish3.addIngredient(dish3ing1);
		dish3.addIngredient(dish3ing2);
		dish3.addIngredient(dish3ing3);
		dishes.add(dish3);
		
		Dish dish4 = new Dish("Crab Cakes",Dish.STARTER,"crab.jpg","In a large mixing bowl, combine crabmeat, 1 egg, lemon zest, Old Bay Seasoning, chopped basil, crushed crackers and mayonnaise. Mix thoroughly. Form 5 ounce patties out of the crab mixture (should make about 16 patties), and chill until cold before cooking. In a skillet, heat 4 tablespoons of oil over medium heat. Saute the crab cakes for 4 minutes on each side or until golden brown. For the sauce, in a blender place the egg yolks, Old Bay Seasoning, lime juice, cilantro, salt and pepper. Blend for 10 seconds. Keeping the blender running, slowly drizzle the oil into the blender. Blend until sauce is creamy.");
		Ingredient dish4ing1 = new Ingredient("eggs",1,"",2);
		Ingredient dish4ing2 = new Ingredient("mayonnaise",1,"cup",1);
		Ingredient dish4ing3 = new Ingredient("vegetable oil",7,"tablespoons",1);
		Ingredient dish4ing4 = new Ingredient("crabmeat",4,"pounds",14);
		Ingredient dish4ing5 = new Ingredient("saltine crackers",1,"cup",3);
		dish4.addIngredient(dish4ing1);
		dish4.addIngredient(dish4ing2);
		dish4.addIngredient(dish4ing3);
		dish4.addIngredient(dish4ing4);
		dish4.addIngredient(dish4ing5);
		dishes.add(dish4);
		
		Dish dish5 = new Dish("The Italian Irishman's Pie",Dish.MAIN,"pie.jpg","Preheat oven to 350 degrees F (175 degrees C). Grease a 2-quart casserole dish. Heat olive oil in a skillet over medium heat; cook and stir Italian sausage until browned and crumbly, about 10 minutes. Use a slotted spoon to transfer sausage to prepared casserole dish. Drain drippings and reserve 2 tablespoons drippings in the skillet. Melt 2 tablespoons margarine in a separate skillet over medium heat; cook and stir apple and onion slices until tender, about 8 minutes. Place apple and onion in casserole dish with sausage. Lightly stir sausage, apple, and onion together; season with salt, black pepper, and oregano. Brown flour in reserved drippings in skillet over medium heat. Whisk water into flour until thick gravy forms, about 3 minutes. Pour gravy over sausage mixture in the casserole dish; spread mashed potatoes over the top with a spoon, creating peaks. Lightly brush mashed potatoes with 1 tablespoon melted margarine. Bake in the preheated oven until the potatoes are lightly browned and the sausage mixture is bubbling, about 30 minutes.");
		Ingredient dish5ing1 = new Ingredient("olive oil",1,"tablespoon",2);
		Ingredient dish5ing2 = new Ingredient("Italian sausage",1,"pound",4);
		Ingredient dish5ing3 = new Ingredient("margarine",2,"margarine",1);
		Ingredient dish5ing4 = new Ingredient("onion",1,"sliced",0.5);
		Ingredient dish5ing5 = new Ingredient("mashed potatoes",3,"cups",2);
		dish5.addIngredient(dish5ing1);
		dish5.addIngredient(dish5ing2);
		dish5.addIngredient(dish5ing3);
		dish5.addIngredient(dish5ing4);
		dish5.addIngredient(dish5ing5);
		dishes.add(dish5);
		
		Dish dish6 = new Dish("Blueberry Crumb Bars",Dish.DESERT,"blueberry.jpg","Preheat the oven to 375 degrees F (190 degrees C). Grease a 9x13 inch pan. In a medium bowl, stir together 1 cup sugar, 3 cups flour, and baking powder. Mix in salt and cinnamon, if desired. Use a fork or pastry cutter to blend in the shortening and egg. Dough will be crumbly. Pat half of dough into the prepared pan. In another bowl, stir together the sugar and cornstarch. Gently mix in the blueberries. Sprinkle the blueberry mixture evenly over the crust. Crumble remaining dough over the berry layer. Bake in preheated oven for 45 minutes, or until top is slightly brown. Cool completely before cutting into squares.");
		Ingredient dish6ing1 = new Ingredient("white sugar",1,"cup",0.5);
		Ingredient dish6ing2 = new Ingredient("baking powder",1,"teaspoon",0.5);
		Ingredient dish6ing3 = new Ingredient("flour",3,"cups",1);
		Ingredient dish6ing4 = new Ingredient("fresh blueberries",4,"cups",4);
		Ingredient dish6ing5 = new Ingredient("egg",1,"",2);
		dish6.addIngredient(dish6ing1);
		dish6.addIngredient(dish6ing2);
		dish6.addIngredient(dish6ing3);
		dish6.addIngredient(dish6ing4);
		dish6.addIngredient(dish6ing5);
		dishes.add(dish6);

		
		// Hårdkodad menu att hämta data ifrån
		/*menu.add(dish1);
		menu.add(dish2);
		menu.add(dish3);
		*/
		guests = 1;
		
	}
	
	
	
	/**
	 * Returns the set of dishes of specific type. (1 = starter, 2 = main, 3 = desert).
	 */
	public Set<Dish> getDishes(){
		return dishes;
	}
	
	/**
	 * Returns the set of dishes of specific type. (1 = starter, 2 = main, 3 = desert).
	 */
	public Set<Dish> getDishesOfType(int type){
		Set<Dish> result = new HashSet<Dish>();
		for(Dish d : dishes){
			if(d.getType() == type){
				result.add(d);
			}
		}
		return result;
	}
	
	/**
	 * Returns the set of dishes of specific type, that contain filter in their name
	 * or name of any ingredient. 
	 */
	public Set<Dish> filterDishesOfType(int type, String filter){
		Set<Dish> result = new HashSet<Dish>();
		for(Dish d : dishes){
			if(d.getType() == type && d.contains(filter)){
				result.add(d);
			}
		}
		return result;
	}
	
	

}
