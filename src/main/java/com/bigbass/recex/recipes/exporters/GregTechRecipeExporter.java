package com.bigbass.recex.recipes.exporters;

import com.bigbass.recex.recipes.Machine;
import com.bigbass.recex.recipes.Mod;
import com.bigbass.recex.recipes.gregtech.GregtechRecipe;
import com.bigbass.recex.recipes.gregtech.RecipeUtil;
import com.bigbass.recex.recipes.ingredients.ItemAmount;
import gregtech.api.util.GT_LanguageManager;
import gregtech.api.util.GT_Recipe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GregTechRecipeExporter implements RecipeExporter {

    @Override
    public Mod getRecipes() {
        List<Machine> machines = new ArrayList<>();
        for (GT_Recipe.GT_Recipe_Map map : GT_Recipe.GT_Recipe_Map.sMappings) {
            machines.add(getMachine(map.mUnlocalizedName, map.mRecipeList));
        }
        return new Mod("gregtech", machines);
    }

    public  <T extends GT_Recipe> Machine getMachine(String unlocalizedName, Collection<T> recipeList) {
        Machine machine = new Machine(GT_LanguageManager.getTranslation(unlocalizedName));

        // machine name retrieval
        if (machine.name == null || machine.name.isEmpty()) {
            machine.name = unlocalizedName;
        }

        for (GT_Recipe rec : recipeList) {
            GregtechRecipe gtr = new GregtechRecipe();
            gtr.en = rec.mEnabled;
            gtr.dur = rec.mDuration;
            gtr.eut = rec.mEUt;

            // item inputs
            for (ItemStack stack : rec.mInputs) {
                ItemAmount item = RecipeUtil.formatRegularItemStack(stack);
                if (item == null) {
                    continue;
                }
                gtr.iI.add(item);
            }

            // item outputs
            for (int i = 0; i < rec.mOutputs.length; i++) {
                ItemStack stack = rec.mOutputs[i];
                ItemAmount item = RecipeUtil.formatRegularItemStack(stack);

                if (item == null) {
                    continue;
                }

                item.c = rec.getOutputChance(i);
                gtr.iO.add(item);
            }

            // fluid inputs
            for (FluidStack stack : rec.mFluidInputs) {
                ItemAmount fluid = RecipeUtil.formatRegularFluidStack(stack);
                if (fluid == null) {
                    continue;
                }
                gtr.fI.add(fluid);
            }

            // fluid outputs
            for (FluidStack stack : rec.mFluidOutputs) {
                ItemAmount fluid = RecipeUtil.formatRegularFluidStack(stack);
                if (fluid == null) {
                    continue;
                }
                gtr.fO.add(fluid);
            }
            machine.recipes.add(gtr);
        }
        return machine;
    }
}
