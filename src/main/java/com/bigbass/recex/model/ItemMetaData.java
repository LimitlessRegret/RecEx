package com.bigbass.recex.model;

public class ItemMetaData extends ItemAmount {
    public String meta;

    public ItemMetaData(ItemAmount item, String metaData) {
        super(item.a, item.a, item.c);
        this.meta = metaData;
    }
}
