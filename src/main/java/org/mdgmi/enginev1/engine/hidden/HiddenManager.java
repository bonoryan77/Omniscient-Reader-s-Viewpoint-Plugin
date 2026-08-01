package org.mdgmi.enginev1.engine.hidden;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.mdgmi.enginev1.engine.player.DataManager;
import org.mdgmi.enginev1.engine.player.PlayerData;

public class HiddenManager {

    public static void unlock(
            Player player,
            HiddenId id
    ) {

        PlayerData data =
                DataManager.get(player);

        if (data.hasHidden(id))
            return;

        data.unlockHidden(id);

        player.playSound(
                player.getLocation(),
                Sound.UI_TOAST_CHALLENGE_COMPLETE,
                1,
                1
        );

        player.sendTitle(
                "§6????",
                "§e숨겨진 조건을 달성했습니다.",
                10,
                60,
                20
        );

        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("§6§l????");
        Bukkit.broadcastMessage(
                "§e"
                        + player.getName()
                        + "님이 숨겨진 시나리오를 개방했습니다."
        );
        Bukkit.broadcastMessage("");

    }

}