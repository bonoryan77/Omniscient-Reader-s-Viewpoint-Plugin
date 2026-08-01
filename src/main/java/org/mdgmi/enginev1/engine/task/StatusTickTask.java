package org.mdgmi.enginev1.engine.task;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.mdgmi.enginev1.engine.player.DataManager;

public class StatusTickTask extends BukkitRunnable {

    @Override
    public void run() {

        for (Player player : Bukkit.getOnlinePlayers()) {

            DataManager.get(player)
                    .getStatuses()
                    .tick();

        }

    }

}