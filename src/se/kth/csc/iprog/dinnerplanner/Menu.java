package se.kth.csc.iprog.dinnerplanner;
import java.util.HashSet;
import java.util.Set;

import se.kth.csc.iprog.dinnerplanner.model.Dish;

public class Menu {
	public int numberOfGuests;
	public Set<Dish> dishes = new HashSet<Dish>();
}
