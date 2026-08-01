package org.mdgmi.enginev1.engine.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.mdgmi.enginev1.Enginev1;
import org.mdgmi.enginev1.engine.player.DataManager;
import org.mdgmi.enginev1.engine.status.StatusInstance;

import java.util.Iterator;

public class StatusTickListener extends BukkitRunnable {

    @Override
    public void run() {

        for (Player player : Bukkit.getOnlinePlayers()) {

            Iterator<StatusInstance> iterator =
                    DataManager.get(player)
                            .getStatuses()
                            .getAll()
                            .iterator();

            while (iterator.hasNext()) {

                StatusInstance status = iterator.next();

                status.tick();

                if (status.expired()) {
                    iterator.remove();
                }
            }
        }
    }

    public static void start(Enginev1 plugin) {
        new StatusTickListener().runTaskTimer(plugin, 1L, 1L);
    }
}