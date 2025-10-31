package ru.GamerPlay3975.combatcooldown;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.scheduler.ServerScheduler;

public class PVPEntry {
    private int secondsLeft;
    private int taskId = -1;
    private final Player player;
    private final Plugin plugin;
    private final CooldownManager manager;
    private final ServerScheduler scheduler = Server.getInstance().getScheduler();
    private String enemy;

    public PVPEntry(Player player, String enemy, Plugin plugin, CooldownManager manager) {
        this.player = player;
        this.enemy = enemy;
        this.plugin = plugin;
        this.manager = manager;
    }

    public void startTask() {
        secondsLeft = manager.TOTAL_SECONDS;
        taskId = scheduler.scheduleRepeatingTask(plugin, () -> {
            if (secondsLeft == 0) {
                player.sendMessage(manager.TEXT_QUIT);
                stopTask();
                manager.remove(player);
                return;
            }
            String text = manager.TEXT_BAR
                    .replace("%enemy%", enemy)
                    .replace("%seconds%", String.valueOf(secondsLeft));
            player.sendActionBar(text);
            secondsLeft--;
        }, manager.TICKS).getTaskId();
    }

    public void stopTask() {
        scheduler.cancelTask(taskId);
    }

    public void resetTask(String newEnemy) {
        this.enemy = newEnemy;
        stopTask();
        taskId = -1;
        startTask();
    }
}


