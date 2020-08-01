package com.bigbass.recex.recipes.exporters;

import com.bigbass.recex.model.Machine;
import com.bigbass.recex.recipes.Mod;
import gregtech.api.util.GTPP_Recipe;

import java.util.ArrayList;
import java.util.List;

public class GTPPRecipeExporter extends GregTechRecipeExporter {

    @Override
    public Mod getRecipes() {
        List<Machine> machines = new ArrayList<>();
        for (GTPP_Recipe.GTPP_Recipe_Map map : GTPP_Recipe.GTPP_Recipe_Map.sMappings) {
            machines.add(getMachine(map.mUnlocalizedName, map.mRecipeList));
        }
        for (GTPP_Recipe.GTPP_Recipe_Map_Internal map : GTPP_Recipe.GTPP_Recipe_Map_Internal.sMappingsEx) {
            machines.add(getMachine(map.mUnlocalizedName, map.mRecipeList));
        }
        return new Mod("gregtechpp", machines);
    }
}
