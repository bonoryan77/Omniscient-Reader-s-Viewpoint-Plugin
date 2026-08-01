package org.mdgmi.enginev1.engine.manager;

import org.bukkit.entity.Player;
import org.mdgmi.enginev1.engine.player.DataManager;
import org.mdgmi.enginev1.engine.player.PlayerData;

public class PlausibilityManager {

    public static void restore(Player player, int amount) {

        PlayerData data = DataManager.get(player);

        int value = Math.min(
                100,
                data.getPlausibility() + amount
        );

        data.setPlausibility(value);

    }

}