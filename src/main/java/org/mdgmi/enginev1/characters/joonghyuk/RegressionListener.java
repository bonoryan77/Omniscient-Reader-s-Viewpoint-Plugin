package org.mdgmi.enginev1.characters.joonghyuk;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.mdgmi.enginev1.Enginev1;
import org.mdgmi.enginev1.engine.character.CharacterType;
import org.mdgmi.enginev1.engine.manager.StatusManager;
import org.mdgmi.enginev1.engine.player.DataManager;
import org.mdgmi.enginev1.engine.player.PlayerData;
import org.mdgmi.enginev1.engine.status.StatusType;

public class RegressionListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event) {

        if (!(event.getEntity() instanceof Player player))
            return;

        PlayerData data = DataManager.get(player);

        if (data.getCharacterType() != CharacterType.YOO_JOONGHYUK)
            return;

        if (data.isRegressionUsed())
            return;

        double health = player.getHealth() - event.getFinalDamage();

        if (health > 0)
            return;

        // 회귀 발동
        event.setCancelled(true);

        data.setRegressionUsed(true);

        player.setInvulnerable(true);
        player.setHealth(1);

        player.sendTitle(
                "§c『회귀』",
                "죽음은 끝이 아니다.",
                10,
                40,
                10
        );

        Bukkit.getScheduler().runTaskLater(
                Enginev1.getInstance(),
                () -> {

                    double maxHealth =
                            player.getAttribute(Attribute.GENERIC_MAX_HEALTH)
                                    .getValue();

                    player.setHealth(maxHealth / 2);

                    data.setPlausibility(50);

                    StatusManager.addStatus(
                            player,
                            StatusType.INVINCIBLE,
                            60,
                            1
                    );

                    player.setInvulnerable(false);

                },
                60L
        );
    }
}