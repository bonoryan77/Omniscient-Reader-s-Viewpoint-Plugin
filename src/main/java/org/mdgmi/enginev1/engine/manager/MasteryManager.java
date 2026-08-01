package org.mdgmi.enginev1.engine.manager;

import org.bukkit.entity.Player;
import org.mdgmi.enginev1.engine.player.DataManager;
import org.mdgmi.enginev1.engine.player.progress.SkillProgress;
import org.mdgmi.enginev1.engine.skill.SkillId;

public class MasteryManager {

    public static void addMastery(
            Player player,
            SkillId skill,
            int amount
    ) {

        SkillProgress progress =
                DataManager.get(player)
                        .getProgress()
                        .get(skill);

        progress.addMastery(amount);

    }

    public static void addExperience(
            Player player,
            SkillId skill,
            int amount
    ) {

        SkillProgress progress =
                DataManager.get(player)
                        .getProgress()
                        .get(skill);

        int before = progress.getLevel();

        progress.addExperience(amount);

        if(progress.getLevel() > before){

            player.sendMessage(
                    "§6[스킬 성장] "
                            + skill.name()
                            + " Lv."
                            + progress.getLevel()
            );

        }

    }

}