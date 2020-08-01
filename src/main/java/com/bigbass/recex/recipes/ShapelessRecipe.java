package com.bigbass.recex.recipes;

import com.bigbass.recex.recipes.ingredients.ItemAmount;

import java.util.ArrayList;
import java.util.List;

public class ShapelessRecipe implements Recipe {
	
	/** input items */
	public List<ItemBase> iI;
	/** output item */
	public ItemAmount o;
	
	public ShapelessRecipe(){
		iI = new ArrayList<>();
	}
}
