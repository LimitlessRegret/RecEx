package com.bigbass.recex.recipes;

import com.bigbass.recex.recipes.ingredients.ItemAmount;

import java.util.ArrayList;
import java.util.List;

public class ShapedRecipe implements Recipe {
	
	/** input items */
	public List<ItemAmount> iI;
	/** output item */
	public ItemAmount o;
	
	public ShapedRecipe(){
		iI = new ArrayList<ItemAmount>();
	}
}
