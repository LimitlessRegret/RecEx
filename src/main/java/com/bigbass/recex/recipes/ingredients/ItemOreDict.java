package com.bigbass.recex.recipes.ingredients;

import com.bigbass.recex.recipes.ItemBase;

import java.util.HashSet;
import java.util.Set;

public class ItemOreDict implements ItemBase {
	/** dictionary ids */
	public Set<Integer> ods;
	/** amount */
	public int a;
	
    public ItemOreDict(){
    	ods = new HashSet<>();
    }
}
