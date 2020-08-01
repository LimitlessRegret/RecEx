package com.bigbass.recex.model;

import com.google.gson.annotations.SerializedName;

public class GregtechRecipe extends RecipeBase {
    @SerializedName("en")
    public boolean enabled;
    @SerializedName("dur")
    public int duration;
    public int eut;

    public GregtechRecipe() {
    }
}
