package com.bigbass.recex.recipes.ingredients;

import com.bigbass.recex.RecipeExporterMod;
import com.bigbass.recex.recipes.renderer.IconRenderer;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import gregtech.api.util.GT_LanguageManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class ItemUtil {
    private static Pattern pattern = Pattern.compile("(?i)" + '\u00a7' + "[0-9A-FK-OR]");

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

    public static int getFluidId(FluidStack stack) {
        checkCount++;
        return fluidIdMap.computeIfAbsent(stack.getFluid(), fluid -> {
            addFluid(stack);
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
            foi.lN = pattern.matcher(stack.getDisplayName()).replaceAll("");
        } catch (Exception e) {
            try {
                foi.lN = pattern.matcher(GT_LanguageManager.getTranslation(stack.getUnlocalizedName())).replaceAll("");
            } catch (Exception e2) {
            }
        }
        try {
            foi.uN = stack.getUnlocalizedName();
        } catch (Exception e) {
        }
        foi.f = false;
        IconRenderer.getInstance().printItemStack(stack, foi.id);

        fluidOrItemList.add(foi);

        String modPrefix = stack.getItem().delegate.name().split(":")[0];
        ModContainer modContainer = Loader.instance().getIndexedModList().get(modPrefix);
        if (modContainer != null) {
            foi.m = modContainer.getName();
        } else {
            foi.m = "unknown";
        }
    }

    private static void addFluid(FluidStack stack) {
        FluidOrItem foi = new FluidOrItem();
        foi.id = itemCounter;
        try {
            foi.lN = pattern.matcher(GT_LanguageManager.getTranslation(stack.getUnlocalizedName())).replaceAll("");
        } catch (Exception e) {
            try {
                foi.lN = pattern.matcher(stack.getFluid().getName()).replaceAll("");
            } catch (Exception e2) {
                try {
                    foi.lN = pattern.matcher(stack.getLocalizedName()).replaceAll("");
                } catch (Exception e3) {
                }
            }
        }
        try {
            foi.uN = stack.getUnlocalizedName();
        } catch (Exception e) {
        }
        IconRenderer.getInstance().printFluidStack(stack, foi.id);
        foi.f = true;

        fluidOrItemList.add(foi);
    }
}
