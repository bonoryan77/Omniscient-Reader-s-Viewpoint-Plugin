package org.mdgmi.enginev1.engine.task;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.mdgmi.enginev1.engine.manager.ScoreboardManager;

public class ScoreboardTask
        extends BukkitRunnable {

    @Override
    public void run() {

        for(Player player :
                Bukkit.getOnlinePlayers()){

            ScoreboardManager.update(player);

        }

    }

}