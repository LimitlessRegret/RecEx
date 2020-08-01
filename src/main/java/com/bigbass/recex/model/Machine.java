package com.bigbass.recex.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Machine {
    @SerializedName("n")
    public String name;
    @SerializedName("recs")
    public List<RecipeBase> recipes;

    public Machine(String name, List<RecipeBase> recipes) {
        this.name = name;
        this.recipes = recipes;
    }

    public Machine(String name) {
        this(name, new ArrayList<>());
    }
}
