package ru.GamerPlay3975.combatcooldown;

import cn.nukkit.plugin.PluginBase;

public class CombatCooldown extends PluginBase {
    @Override
    public void onEnable() {
        saveDefaultConfig();
        CooldownManager manager = new CooldownManager(this);
        getServer().getPluginManager().registerEvents(new CombatListener(manager), this);
    }
}


