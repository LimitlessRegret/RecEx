package com.bigbass.recex.recipes;

import com.bigbass.recex.recipes.ingredients.FluidOrItem;
import com.bigbass.recex.recipes.ingredients.IItem;
import com.bigbass.recex.recipes.ingredients.ItemAmount;

import java.util.ArrayList;
import java.util.List;

public class OreDictShapedRecipe {
	
	/** input items */
	public List<IItem> iI;
	/** output item */
	public ItemAmount o;
	
	public OreDictShapedRecipe(){
		iI = new ArrayList<IItem>();
	}
}
