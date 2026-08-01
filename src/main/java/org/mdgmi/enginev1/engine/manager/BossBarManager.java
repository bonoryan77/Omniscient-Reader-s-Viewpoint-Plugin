package org.mdgmi.enginev1.engine.manager;

import org.bukkit.Bukkit;
import org.bukkit.boss.*;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BossBarManager {

    private static final Map<UUID, BossBar> bars =
            new HashMap<>();

    public static void create(
            Player player,
            String title
    ) {

        remove(player);

        BossBar bar = Bukkit.createBossBar(
                title,
                BarColor.RED,
                BarStyle.SOLID
        );

        bar.addPlayer(player);
        bar.setProgress(1.0);

        bars.put(
                player.getUniqueId(),
                bar
        );

    }

    public static void setProgress(
            Player player,
            double progress
    ) {

        BossBar bar =
                bars.get(player.getUniqueId());

        if(bar != null)
            bar.setProgress(
                    Math.max(
                            0,
                            Math.min(
                                    1,
                                    progress
                            )
                    )
            );

    }

    public static void remove(
            Player player
    ) {

        BossBar bar =
                bars.remove(
                        player.getUniqueId()
                );

        if(bar != null)
            bar.removeAll();

    }

}