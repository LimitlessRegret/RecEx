package com.bigbass.recex.model;

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

	/** mod */
    public String m;

	public FluidOrItem(){

	}

	public FluidOrItem(int id, int damage, boolean isFluid, String unlocalizedName, String localizedName, String mod) {
		this.id = id;
		this.d = damage;
		this.f = isFluid;
		this.uN = unlocalizedName;
		this.lN = localizedName;
		this.m = mod;
	}
}
