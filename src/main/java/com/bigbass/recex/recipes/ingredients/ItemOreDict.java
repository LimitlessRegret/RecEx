package com.bigbass.recex.recipes.ingredients;

import java.util.HashSet;
import java.util.Set;

public class ItemOreDict implements IItem {
	/** dictionary ids */
	public Set<Integer> ods;
	/** amount */
	public int a;
	
    public ItemOreDict(){
    	ods = new HashSet<>();
    }
}
