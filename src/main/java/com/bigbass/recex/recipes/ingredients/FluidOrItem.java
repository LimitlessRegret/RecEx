package com.bigbass.recex.recipes.ingredients;

public class FluidOrItem {
	/** id */
	public int id;

	/** damage */
	public int d;

	/** isFluid */
	public boolean f;

	/** unlocalizedName */
	public String uN;

	/** localizedName */
	public String lN;

	public FluidOrItem(){

	}

	public FluidOrItem(int id, int damage, boolean isFluid, String unlocalizedName, String localizedName) {
		this.id = id;
		this.d = damage;
		this.f = isFluid;
		this.uN = unlocalizedName;
		this.lN = localizedName;
	}
}
