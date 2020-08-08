package com.bigbass.recex.recipes;

import com.bigbass.recex.RecipeExporterMod;
import com.bigbass.recex.model.Machine;
import com.bigbass.recex.model.OreDictEntry;
import com.bigbass.recex.model.RecipeBase;
import com.bigbass.recex.recipes.exporters.*;
import com.bigbass.recex.recipes.gregtech.RecipeUtil;
import com.bigbass.recex.recipes.renderer.IconRenderer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.OreDictionary;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class RecipeExporter {

	private static RecipeExporter instance;

	private RecipeExporter() {
	}

	public static RecipeExporter getInst() {
		if (instance == null) {
			instance = new RecipeExporter();
		}

		return instance;
	}

	/**
	 * <p>Collects recipes into a master Hashtable (represents a JSON Object),
	 * then serializes it and saves it to a datetime-stamped file.</p>
	 *
	 * <p>Recipes are stored in collections, often either List's or Hashtable's.
	 * The Gson library will serialize objects based on their public fields.
	 * The field name becomes the key, and the value is also serialized the same way.
	 * Lists are serialized as JSON arrays.</p>
	 *
	 * <p>Schema for existing recipe sources should not be radically changed unless
	 * truly necessary. Adding additional data is acceptable however.</p>
	 */
	public void run() {
		Hashtable<String, Object> root = new Hashtable<String, Object>();

		IconRenderer.getInstance().init();

		List<Object> sources = new ArrayList<Object>();
		sources.add(new GregTechRecipeExporter().getRecipes());
		sources.add(new GTPPRecipeExporter().getRecipes());
		sources.add(new ForestryRecipeExporter().getRecipes());
		sources.addAll(new AvaritiaExporter().getRecipes().machines);
		sources.addAll(new CraftingTableRecipeExporter().getRecipes().machines);
		sources.add(getFurnaceRecipes());

		List<OreDictEntry> oreDictEntries = new ArrayList<>();
		for (String name : OreDictionary.getOreNames()) {
			OreDictEntry entry = new OreDictEntry();
			entry.name = name;
			for (ItemStack stack : OreDictionary.getOres(name)) {
				entry.ids.add(ItemUtil.getItemId(stack));
			}

			oreDictEntries.add(entry);
		}

		root.put("sources", sources);
		root.put("items", ItemUtil.getFluidOrItemList());
		root.put("oreDict", oreDictEntries);
		IconRenderer.getInstance().dispose();

		Gson gson = new GsonBuilder()
                .create();
		try {
			saveData(gson.toJson(root));
		} catch (Exception e) {
			e.printStackTrace();
			RecipeExporterMod.log.error("Recipes failed to export!");
		}
		ItemUtil.reset();
	}

	private Machine getFurnaceRecipes() {
		List<RecipeBase> recipes = new ArrayList<>();
		Map<ItemStack, ItemStack> smeltingList = FurnaceRecipes.smelting().getSmeltingList();
		for (Map.Entry<ItemStack, ItemStack> entry : smeltingList.entrySet()) {
            RecipeBase recipe = new RecipeBase();
			recipe.input.add(RecipeUtil.formatRegularItemStack(entry.getKey()));
			recipe.output.add(RecipeUtil.formatRegularItemStack(entry.getValue()));
			recipes.add(recipe);
		}
		return new Machine("furnace", recipes);
	}

	private void saveData(String json) {
		try {
			FileWriter writer = new FileWriter(getSaveFile());
			writer.write(json);
			writer.close();

			RecipeExporterMod.log.info("Recipes have been exported.");
		} catch (IOException | NullPointerException e) {
			e.printStackTrace();
			RecipeExporterMod.log.error("Recipes failed to save!");
		}
	}

	private File getSaveFile() {
		String dateTime = ZonedDateTime.now(ZoneId.of("UTC")).format(DateTimeFormatter.ofPattern("uuuu-MM-dd_HH-mm-ss"));
		File file = new File(RecipeExporterMod.clientConfigDir.getParent() + "/RecEx-Records/" + dateTime + ".json");
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return file;
	}
}
