package com.bigbass.recex.recipes.gregtech;

import com.bigbass.recex.recipes.ingredients.ItemAmount;
import com.bigbass.recex.recipes.ingredients.ItemUtil;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

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

	public static ItemAmount formatRegularFluidStack(FluidStack stack) {
		if (stack == null) {
			return null;
		}

		ItemAmount item = new ItemAmount();
		item.a = stack.amount; // stackSize?
		item.id = ItemUtil.getFluidId(stack);

		return item;
	}
}
