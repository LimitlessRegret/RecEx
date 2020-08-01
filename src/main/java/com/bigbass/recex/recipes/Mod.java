package com.bigbass.recex.recipes;

import com.bigbass.recex.model.Machine;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Mod {
    @SerializedName("type")
    public String modName;
    public List<Machine> machines;

    public Mod(String modName, List<Machine> machines) {
        this.modName = modName;
        this.machines = machines;
    }
}
