package com.bigbass.recex.recipes.forestry;

import com.bigbass.recex.recipes.Recipe;
import com.bigbass.recex.recipes.ingredients.ItemAmount;

import java.util.ArrayList;
import java.util.List;

public class RfRecipe implements Recipe {
    public boolean en;
    public int dur;
    public int rft;
    public List<ItemAmount> iI;
    public List<ItemAmount> iO;
    public List<ItemAmount> fI;
    public List<ItemAmount> fO;

    public RfRecipe() {
        iI = new ArrayList<>();
        iO = new ArrayList<>();
        fI = new ArrayList<>();
        fO = new ArrayList<>();
    }
}
