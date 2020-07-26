package com.bigbass.recex.recipes;

import com.bigbass.recex.recipes.ingredients.ItemAmount;

public class ItemMetaData extends ItemAmount {
    public String meta;

    public ItemMetaData(ItemAmount item, String metaData) {
        super(item.a, item.a, item.c);
        this.meta = metaData;
    }
}
