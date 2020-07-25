package com.bigbass.recex.recipes.gregtech;

import com.bigbass.recex.recipes.Recipe;
import com.bigbass.recex.recipes.ingredients.ItemAmount;

import java.util.ArrayList;
import java.util.List;

public class GregtechRecipe implements Recipe {
	
	/** enabled */
	public boolean en;
	/** duration */
	public int dur;
	/** EU/t */
	public int eut;
	/** itemInputs */
	public List<ItemAmount> iI;
	/** itemOutputs */
	public List<ItemAmount> iO;
	/** fluidInputs */
	public List<ItemAmount> fI;
	/** fluidOutputs */
	public List<ItemAmount> fO;
	
	public GregtechRecipe(){
		iI = new ArrayList<>();
		iO = new ArrayList<>();
		fI = new ArrayList<>();
		fO = new ArrayList<>();
	}
}
