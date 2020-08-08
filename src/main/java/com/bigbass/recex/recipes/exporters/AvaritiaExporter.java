package com.bigbass.recex.recipes.exporters;

import com.bigbass.recex.RecipeExporterMod;
import com.bigbass.recex.model.*;
import com.bigbass.recex.recipes.Mod;
import com.bigbass.recex.recipes.gregtech.RecipeUtil;
import fox.spiteful.avaritia.crafting.ExtremeCraftingManager;
import fox.spiteful.avaritia.crafting.ExtremeShapedOreRecipe;
import fox.spiteful.avaritia.crafting.ExtremeShapedRecipe;
import fox.spiteful.avaritia.crafting.ExtremeShapelessRecipe;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.*;

public class AvaritiaExporter implements RecipeExporter {
    @Override
    public Mod getRecipes() {

        HashMap<Class<?>, Machine> machineMap = new HashMap<>();
        List<?> recipes = ExtremeCraftingManager.getInstance().getRecipeList();
        HashSet<Class<?>> unhandledClasses = new HashSet<>();
        for (Object obj : recipes) {
            RecipeBase rec = new RecipeBase();
            if (obj instanceof ExtremeShapedRecipe) {
                ExtremeShapedRecipe original = (ExtremeShapedRecipe) obj;

                rec.input.addAll(extractItemList(original.recipeItems));
                rec.output.add(RecipeUtil.formatRegularItemStack(original.getRecipeOutput()));
            } else if (obj instanceof ExtremeShapedOreRecipe) {
                ExtremeShapedOreRecipe original = (ExtremeShapedOreRecipe) obj;

                rec.input.addAll(extractItemList(original.getInput()));
                rec.output.add(RecipeUtil.formatRegularItemStack(original.getRecipeOutput()));
            } else if (obj instanceof ExtremeShapelessRecipe) {
                ExtremeShapelessRecipe original = (ExtremeShapelessRecipe) obj;

                rec.input.addAll(extractItemList(original.recipeItems));
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

    private List<ItemBase> extractItemList(ItemStack[] items) {
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
                if (stack != null) {
                    RecipeExporterMod.log.info("Unhandled item list type: " + stack.getClass().getCanonicalName());
                }
                ret.add(null);
            }
        }
        return ret;
    }
}
