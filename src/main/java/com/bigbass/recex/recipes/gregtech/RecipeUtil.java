package com.bigbass.recex.recipes.gregtech;

import java.util.ArrayList;
import java.util.List;

import com.bigbass.recex.recipes.ingredients.FluidOrItem;
import com.bigbass.recex.recipes.ingredients.ItemAmount;
import com.bigbass.recex.recipes.ingredients.ItemOreDict;

import com.bigbass.recex.recipes.ingredients.ItemUtil;
import gregtech.api.util.GT_LanguageManager;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

public class RecipeUtil {
	
	public static ItemAmount formatRegularItemStack(ItemStack stack){
		if(stack == null){
			return null;
		}
		
		ItemAmount item = new ItemAmount();
		item.id = ItemUtil.getItemId(stack);
		item.a = stack.stackSize;
		return item;
	}
	
	public static ItemAmount formatGregtechItemStack(ItemStack stack){
		if(stack == null){
			return null;
		}
		
		ItemAmount item = new ItemAmount();
		item.a = stack.stackSize;
		item.id = ItemUtil.getItemId(stack);

		return item;
	}
	
	/**
	 * Might return null!
	 * 
	 * @param name
	 * @return
	 */
	public static ItemOreDict parseOreDictionary(String name){
		if(name == null || name.isEmpty()){
			return null;
		}
		
		ItemOreDict item = new ItemOreDict();
		item.ods.add(OreDictionary.getOreID(name));
		item.a = 1;

		return item;
	}
	
	/**
	 * Might return null!
	 * 
	 * @param names
	 * @return
	 */
	public static ItemOreDict parseOreDictionary(String[] names){
		if(names == null || names.length == 0){
			return null;
		}
		
		ItemOreDict retItem = new ItemOreDict();
		for(String name : names){
			retItem.ods.add(OreDictionary.getOreID(name));
		}
		retItem.a = 1;
		
		return retItem;
	}
	
	public static ItemAmount formatGregtechFluidStack(FluidStack stack){
		if(stack == null){
			return null;
		}
		
		ItemAmount fluid = new ItemAmount();
		
		fluid.a = stack.amount;
		fluid.id = ItemUtil.getFluidId(stack.getFluid());

		return fluid;
	}
}
