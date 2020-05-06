package com.bigbass.recex.recipes.gregtech;

import com.bigbass.recex.recipes.Fluid;
import com.bigbass.recex.recipes.Item;
import com.bigbass.recex.recipes.ItemProgrammedCircuit;

import gregtech.api.util.GT_LanguageManager;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class RecipeUtil {
	
	public static Item formatRegularItemStack(ItemStack stack){
		if(stack == null){
			return null;
		}
		
		Item item = new Item();
		
		item.a = stack.stackSize;
		try {
			item.uN = stack.getUnlocalizedName();
		} catch(Exception e){}
		try {
			item.lN = stack.getDisplayName();
		} catch(Exception e){}
		
		return item;
	}

	public static Fluid formatRegularFluidStack(FluidStack stack) {
		if (stack == null) {
			return null;
		}

		Fluid fluid = new Fluid();

		fluid.a = stack.amount;
		try {
			fluid.uN = stack.getUnlocalizedName();
		} catch (Exception e) {}
		try {
			fluid.lN = stack.getLocalizedName();
		} catch (Exception e) {}

		return fluid;
	}
	
	public static Item formatGregtechItemStack(ItemStack stack){
		if(stack == null){
			return null;
		}
		
		Item item = new Item();
		
		item.a = stack.stackSize;
		try {
			item.uN = stack.getUnlocalizedName();
		} catch(Exception e){}
		try {
			item.lN = stack.getDisplayName();
		} catch(Exception e1){
			try {
				item.lN = GT_LanguageManager.getTranslation(stack.getUnlocalizedName());
			} catch(Exception e2){}
		}
		
		if(item.uN != null && !item.uN.isEmpty() && item.uN.equalsIgnoreCase("gt.integrated_circuit")){ // Programmed Circuit
			item = new ItemProgrammedCircuit(item, stack.getItemDamage());
		}
		
		return item;
	}
	
	public static Fluid formatGregtechFluidStack(FluidStack stack){
		if(stack == null){
			return null;
		}
		
		Fluid fluid = new Fluid();
		
		fluid.a = stack.amount;
		try {
			fluid.uN = stack.getUnlocalizedName();
		} catch(Exception e){}
		try {
			fluid.lN = GT_LanguageManager.getTranslation(stack.getUnlocalizedName());
		} catch(Exception e1){
			try {
				fluid.lN = stack.getFluid().getName();
			} catch(Exception e2){
				try {
					fluid.lN = stack.getLocalizedName();
				} catch(Exception e3){}
			}
		}
		
		return fluid;
	}
}
