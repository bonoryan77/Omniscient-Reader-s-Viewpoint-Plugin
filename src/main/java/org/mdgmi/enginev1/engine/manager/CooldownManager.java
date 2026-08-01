package org.mdgmi.enginev1.engine.manager;

import org.bukkit.entity.Player;
import org.mdgmi.enginev1.engine.player.DataManager;
import org.mdgmi.enginev1.engine.skill.Skill;

public class CooldownManager {

    public static void startCooldown(
            Player player,
            Skill skill
    ) {

        DataManager.get(player)
                .getCooldown()
                .start(
                        skill.getId(),
                        skill.getCooldown()
                );

    }

    public static boolean isCooling(
            Player player,
            Skill skill
    ) {

        return DataManager.get(player)
                .getCooldown()
                .isCooling(
                        skill.getId()
                );

    }

    public static long getRemaining(
            Player player,
            Skill skill
    ) {

        return DataManager.get(player)
                .getCooldown()
                .getRemaining(
                        skill.getId()
                );

    }

}