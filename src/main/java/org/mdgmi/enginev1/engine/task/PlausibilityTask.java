package org.mdgmi.enginev1.engine.task;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.mdgmi.enginev1.engine.manager.PlausibilityManager;

public class PlausibilityTask extends BukkitRunnable {

    @Override
    public void run() {

        for (Player player : Bukkit.getOnlinePlayers()) {

            PlausibilityManager.restore(player, 3);

        }

    }

}