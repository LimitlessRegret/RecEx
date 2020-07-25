package com.bigbass.recex.recipes;

import com.bigbass.recex.recipes.ingredients.ItemAmount;

import java.util.ArrayList;
import java.util.List;

public class OreDictShapedRecipe implements Recipe {
	
	/** input items */
	public List<ItemBase> iI;
	/** output item */
	public ItemAmount o;
	
	public OreDictShapedRecipe(){
		iI = new ArrayList<>();
	}
}
