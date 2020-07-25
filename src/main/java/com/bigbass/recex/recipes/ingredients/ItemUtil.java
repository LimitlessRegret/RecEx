package com.bigbass.recex.recipes.ingredients;

import com.bigbass.recex.RecipeExporterMod;
import gregtech.api.util.GT_LanguageManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemUtil {
    private static int itemCounter = 1;
    private static int checkCount = 0;
    private static List<FluidOrItem> fluidOrItemList = new ArrayList<>();
    private static Map<Pair<Item, Integer>, Integer> itemIdMap = new HashMap<>();
    private static Map<Fluid, Integer> fluidIdMap = new HashMap<>();

    public static void reset() {
        itemCounter = 1;
        checkCount = 0;
        itemIdMap.clear();
        fluidIdMap.clear();
        fluidOrItemList.clear();
    }

    public static int getItemId(ItemStack stack) {
        checkCount++;
        return itemIdMap.computeIfAbsent(Pair.of(stack.getItem(), stack.getItemDamage()), pair -> {
            addItem(stack);
            return itemCounter++;
        });
    }

    public static int getFluidId(Fluid fluid) {
        checkCount++;
        return fluidIdMap.computeIfAbsent(fluid, fluid1 -> {
            addFluid(fluid1);
            return itemCounter++;
        });
    }

    public static List<FluidOrItem> getFluidOrItemList() {
        RecipeExporterMod.log.info("foi count: " + fluidOrItemList.size() + ", check count: " + checkCount);
        return fluidOrItemList;
    }

    private static void addItem(ItemStack stack) {
        FluidOrItem foi = new FluidOrItem();
        foi.id = itemCounter;
        foi.d = stack.getItemDamage();
        try {
            foi.lN = stack.getDisplayName();
        } catch (Exception e) {
            try {
                foi.lN = GT_LanguageManager.getTranslation(stack.getUnlocalizedName());
            } catch (Exception e2) {
            }
        }
        try {
            foi.uN = stack.getUnlocalizedName();
        } catch (Exception e) {
        }
        foi.f = false;

        fluidOrItemList.add(foi);
    }

    private static void addFluid(Fluid fluid) {
        FluidOrItem foi = new FluidOrItem();
        foi.id = itemCounter;
        try {
            foi.lN = fluid.getLocalizedName();
        } catch (Exception e) {
            try {
                foi.lN = GT_LanguageManager.getTranslation(fluid.getUnlocalizedName());
            } catch (Exception e2) {
                try {
                    foi.lN = fluid.getName();
                } catch (Exception e3) {
                }
            }
        }
        try {
            foi.uN = fluid.getUnlocalizedName();
        } catch (Exception e) {
        }
        foi.f = true;

        fluidOrItemList.add(foi);
    }
}
