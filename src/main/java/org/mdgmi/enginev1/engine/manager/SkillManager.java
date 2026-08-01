package org.mdgmi.enginev1.engine.manager;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.mdgmi.enginev1.engine.ex.EXSkill;
import org.mdgmi.enginev1.engine.player.DataManager;
import org.mdgmi.enginev1.engine.player.PlayerData;
import org.mdgmi.enginev1.engine.player.progress.SkillProgress;
import org.mdgmi.enginev1.engine.skill.ActiveSkill;
import org.mdgmi.enginev1.engine.skill.Skill;
import org.mdgmi.enginev1.engine.skill.context.SkillContext;
import org.mdgmi.enginev1.engine.status.StatusType;

public class SkillManager {

    public static void useSkills(
            Player player
    ) {

        PlayerData data =
                DataManager.get(player);

        ItemStack item =
                player.getInventory()
                        .getItemInMainHand();

        Material material =
                item.getType();

        for (Skill skill : data.getSkills().getAllSkills()) {

            if (!(skill instanceof ActiveSkill activeSkill))
                continue;

            if (skill.getTriggerItem() != material)
                continue;

            if (CooldownManager.isCooling(player, skill)) {

                player.sendMessage(
                        "§c쿨타임 "
                                + CooldownManager.getRemaining(
                                player,
                                skill
                        )
                                + "초"
                );

                continue;
            }

            if (data.getPlausibility()
                    < skill.getPlausibilityCost()) {

                player.sendMessage(
                        "§c마력이 부족합니다."
                );

                continue;
            }

            if (StatusManager.hasStatus(
                    player,
                    StatusType.SILENCE
            )) {

                player.sendMessage(
                        "§c침묵 상태입니다."
                );

                continue;
            }
            if (skill instanceof EXSkill exSkill) {

                if (!data.hasHidden(
                        exSkill.getHiddenId()
                )) {

                    player.sendMessage(
                            "§c아직 각성하지 못한 스킬입니다."
                    );

                    return;

                }

            }

            SkillProgress progress =
                    data.getProgress()
                            .get(skill.getId());

            SkillContext context =
                    new SkillContext(
                            player,
                            data,
                            skill,
                            progress
                    );

            activeSkill.use(context);

            data.consumePlausibility(
                    skill.getPlausibilityCost()
            );

            MasteryManager.addMastery(
                    player,
                    skill.getId(),
                    1
            );

            MasteryManager.addExperience(
                    player,
                    skill.getId(),
                    5
            );

            CooldownManager.startCooldown(
                    player,
                    skill
            );

            return;
        }

    }

}