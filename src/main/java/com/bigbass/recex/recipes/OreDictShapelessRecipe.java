package com.bigbass.recex.recipes;

import com.bigbass.recex.recipes.ingredients.ItemAmount;

import java.util.ArrayList;
import java.util.List;

public class OreDictShapelessRecipe implements Recipe {
    /** input items */
    public List<Object> iI;
    /** output item */
    public ItemAmount o;

    public OreDictShapelessRecipe(){
        iI = new ArrayList<Object>();
    }
}
