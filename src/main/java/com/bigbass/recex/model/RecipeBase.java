package com.bigbass.recex.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RecipeBase {
    @SerializedName("i")
    public List<ItemBase> input;
    @SerializedName("o")
    public List<ItemBase> output;

    public RecipeBase() {
        input = new ArrayList<>();
        output = new ArrayList<>();
    }
}
