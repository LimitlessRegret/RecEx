package com.bigbass.recex.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemList {
    public String type;
    @SerializedName("items")
    public List<ItemBase> itemList;

    public ItemList(String type, List<ItemBase> itemList) {
        this.type = type;
        this.itemList = itemList;
    }
}
