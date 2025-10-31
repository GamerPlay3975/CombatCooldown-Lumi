package ru.GamerPlay3975.combatcooldown;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.event.player.PlayerCommandPreprocessEvent;
import cn.nukkit.event.player.PlayerDeathEvent;
import cn.nukkit.event.player.PlayerQuitEvent;

public class CombatListener implements Listener {
    private final CooldownManager manager;

    public CombatListener(CooldownManager manager) {
        this.manager = manager;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPVP(EntityDamageByEntityEvent event) {
        if(event.isCancelled() || !(event.getDamager() instanceof Player) || !(event.getEntity() instanceof Player)) {
            return;
        }

        Player damager = (Player) event.getDamager();
        Player victim = (Player) event.getEntity();

        // Respect DGuard region PvP flag: do not allow combat mode if PvP is disabled in region
        if (!isPvPAllowedByRegions(damager, victim)) {
            event.setCancelled(true);
            damager.sendTip("§cВ данном месте PvP отключено");
            return;
        }

        manager.start(damager, victim);
        manager.start(victim, damager);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if (check(player)) {
            return;
        }

        player.kill();
        manager.stop(player);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        manager.stop(event.getEntity());
    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();

        if (check(player)) {
            return;
        }

        player.sendMessage(manager.TEXT_ON_COMMAND);
        event.setCancelled();
    }

    private boolean check(Player player) {
        return player.hasPermission("combatcooldown.bypass") || manager.isNotPVP(player);
    }

    private boolean isPvPAllowedByRegions(Player damager, Player victim) {
        try {
            Class<?> dguardClass = Class.forName("ru.dragonestia.dguard.DGuard");
            Object dguard = dguardClass.getMethod("getInstance").invoke(null);
            if (dguard == null) return true; // DGuard not loaded -> allow

            Object flags = dguardClass.getMethod("getFlags").invoke(dguard);
            Object pvpFlag = ((java.util.Map<?, ?>) flags).get("pvp");
            if (pvpFlag == null) return true;

            Class<?> pointClass = Class.forName("ru.dragonestia.dguard.util.Point");
            Class<?> regionClass = Class.forName("ru.dragonestia.dguard.region.Region");
            Class<?> flagClass = Class.forName("ru.dragonestia.dguard.region.Flag");

            // Check victim location
            Object victimPoint = pointClass.getConstructor(cn.nukkit.Player.class).newInstance(victim);
            Object victimRegion = pointClass.getMethod("getCacheRegion", cn.nukkit.Player.class).invoke(victimPoint, victim);
            if (victimRegion != null) {
                Boolean victimPvP = (Boolean) regionClass.getMethod("getFlag", flagClass).invoke(victimRegion, pvpFlag);
                if (victimPvP != null && !victimPvP) return false;
            }

            // Check damager location
            Object damagerPoint = pointClass.getConstructor(cn.nukkit.Player.class).newInstance(damager);
            Object damagerRegion = pointClass.getMethod("getCacheRegion", cn.nukkit.Player.class).invoke(damagerPoint, damager);
            if (damagerRegion != null) {
                Boolean damagerPvP = (Boolean) regionClass.getMethod("getFlag", flagClass).invoke(damagerRegion, pvpFlag);
                if (damagerPvP != null && !damagerPvP) return false;
            }

            return true;
        } catch (Throwable ignored) {
            return true; // If anything goes wrong, do not block PvP to avoid breaking servers without DGuard
        }
    }
}


