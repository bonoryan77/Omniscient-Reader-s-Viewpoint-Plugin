package org.mdgmi.enginev1.engine.player;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DataManager {

    private static final Map<
            UUID,
            PlayerData
            > dataMap =
            new HashMap<>();

    public static PlayerData get(
            Player player
    ) {

        return dataMap.computeIfAbsent(
                player.getUniqueId(),
                PlayerData::new
        );

    }

    public static void remove(
            Player player
    ) {

        dataMap.remove(
                player.getUniqueId()
        );

    }

}