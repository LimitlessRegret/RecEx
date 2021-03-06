package com.bigbass.recex.recipes.exporters;

import com.bigbass.recex.RecipeExporterMod;
import com.bigbass.recex.model.ItemBase;
import com.bigbass.recex.model.Machine;
import com.bigbass.recex.recipes.Mod;
import com.bigbass.recex.model.RecipeBase;
import com.bigbass.recex.recipes.gregtech.RecipeUtil;
import com.bigbass.recex.model.ItemAmount;
import com.bigbass.recex.model.ItemOreDict;
import ic2.core.AdvRecipe;
import ic2.core.AdvShapelessRecipe;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import java.util.*;

public class CraftingTableRecipeExporter implements RecipeExporter {
    @Override
    public Mod getRecipes() {
        HashMap<Class<?>, Machine> machineMap = new HashMap<>();
        List<?> recipes = CraftingManager.getInstance().getRecipeList();
        HashSet<Class<?>> unhandledClasses = new HashSet<>();
        for (Object obj : recipes) {
            RecipeBase rec = new RecipeBase();
            if (obj instanceof ShapelessOreRecipe) {
                ShapelessOreRecipe original = (ShapelessOreRecipe) obj;

                rec.input.addAll(extractItemList(original.getInput()));
                rec.output.add(RecipeUtil.formatRegularItemStack(original.getRecipeOutput()));
            } else if (obj instanceof ShapedOreRecipe) {
                ShapedOreRecipe original = (ShapedOreRecipe) obj;

                rec.input.addAll(extractItemList(original.getInput()));
                rec.output.add(RecipeUtil.formatRegularItemStack(original.getRecipeOutput()));
            } else if (obj instanceof ShapedRecipes) {
                ShapedRecipes original = (ShapedRecipes) obj;

                rec.input.addAll(extractItemList(Arrays.asList(original.recipeItems)));
                rec.output.add(RecipeUtil.formatRegularItemStack(original.getRecipeOutput()));
            } else if (obj instanceof ShapelessRecipes) {
                ShapelessRecipes original = (ShapelessRecipes) obj;

                rec.input.addAll(extractItemList(original.recipeItems));
                rec.output.add(RecipeUtil.formatRegularItemStack(original.getRecipeOutput()));
            } else if (obj instanceof AdvRecipe) {
                AdvRecipe original = (AdvRecipe) obj;

                rec.input.addAll(extractItemList(original.input));
                rec.output.add(RecipeUtil.formatRegularItemStack(original.getRecipeOutput()));
            } else if (obj instanceof AdvShapelessRecipe) {
                AdvShapelessRecipe original = (AdvShapelessRecipe) obj;

                rec.input.addAll(extractItemList(original.input));
                rec.output.add(RecipeUtil.formatRegularItemStack(original.getRecipeOutput()));
            } else {
                unhandledClasses.add(obj.getClass());
                continue;
            }
            machineMap.computeIfAbsent(obj.getClass(), clz -> new Machine(clz.getSimpleName())).recipes.add(rec);
        }
        return new Mod("todo", new ArrayList<>(machineMap.values()));
    }

    private List<ItemBase> extractItemList(Object[] items) {
        return extractItemList(Arrays.asList(items));
    }

    private List<ItemBase> extractItemList(List<?> items) {
        List<ItemBase> ret = new ArrayList<>();
        for (Object stack : items) {
            if (stack instanceof ItemStack) {
                ItemAmount item = RecipeUtil.formatRegularItemStack((ItemStack) stack);
                ret.add(item);
            } else if (stack instanceof net.minecraft.item.Item) {
                ret.add(RecipeUtil.formatRegularItemStack(new ItemStack((net.minecraft.item.Item) stack)));
            } else if (stack instanceof Block) {
                ret.add(RecipeUtil.formatRegularItemStack(new ItemStack((Block) stack, 1, Short.MAX_VALUE)));
            } else if (stack instanceof ArrayList && !((ArrayList) stack).isEmpty()) {
                ArrayList<?> list = (ArrayList<?>) stack;
                ItemOreDict item = new ItemOreDict();
                for (Object listObj : list) {
                    if (listObj instanceof ItemStack) {
                        ItemStack stack2 = (ItemStack) listObj;
                        if (item.a != 0 && item.a != stack2.stackSize) {
                            RecipeExporterMod.log.warn("Stack size in ore dict'd slot not consistent!");
                        }
                        item.a = stack2.stackSize;

                        int[] ids = OreDictionary.getOreIDs(stack2);
                        for (int id : ids) {
                            item.ods.add(id);
                        }
                    }
                }
                if (!item.ods.isEmpty()) {
                    ret.add(item);
                } else {
                    ret.add(null);
                }
            } else {
                ret.add(null);
            }
        }
        return ret;
    }
}
