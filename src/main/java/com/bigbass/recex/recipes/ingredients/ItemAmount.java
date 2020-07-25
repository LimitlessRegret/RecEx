package com.bigbass.recex.recipes.ingredients;

public class ItemAmount implements IItem {
    /** amount */
    public int a;

    /** chance */
    public Integer c;

    /** id */
    public int id;

    public ItemAmount() {

    }

    public ItemAmount(int id, int amount, Integer chance) {
        this.id = id;
        this.a = amount;
        this.c = chance;
    }
}
