package com.bigbass.recex.model;

import com.google.gson.annotations.SerializedName;

public class RfRecipe extends RecipeBase {
    @SerializedName("en")
    public boolean enabled;
    @SerializedName("dur")
    public int duration;
    public int rft;

    public RfRecipe() {
    }
}
