package org.mdgmi.enginev1.characters.joonghyuk;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.mdgmi.enginev1.Enginev1;
import org.mdgmi.enginev1.engine.manager.CooldownManager;
import org.mdgmi.enginev1.engine.manager.StatusManager;
import org.mdgmi.enginev1.engine.skill.ActiveSkill;
import org.mdgmi.enginev1.engine.skill.SkillCategory;
import org.mdgmi.enginev1.engine.skill.SkillId;
import org.mdgmi.enginev1.engine.skill.SkillType;
import org.mdgmi.enginev1.engine.skill.context.SkillContext;
import org.mdgmi.enginev1.engine.status.StatusType;

public class SkyBreakingSwordSkill implements ActiveSkill {

    @Override
    public SkillId getId() {
        return SkillId.SKY_BREAKING_SWORD;
    }

    @Override
    public String getName() {
        return "파천검도";
    }

    @Override
    public SkillType getType() {
        return SkillType.ACTIVE;
    }

    @Override
    public SkillCategory getCategory() {
        return SkillCategory.ATTACK;
    }

    @Override
    public int getCooldown() {
        return 90;
    }

    @Override
    public int getPlausibilityCost() {
        return 20;
    }

    @Override
    public Material getTriggerItem() {
        return Material.IRON_SWORD;
    }

    @Override
    public void use(SkillContext context) {

        Player player = context.getPlayer();

        if (CooldownManager.isCooling(player, this))
            return;

        if (!context.getData().consumePlausibility(getPlausibilityCost()))
            return;

        CooldownManager.startCooldown(player, this);

        StatusManager.addStatus(
                player,
                StatusType.BLACK_HEAVEN_SWORD,
                20 * 20,
                context.getLevel()
        );

        player.sendMessage("§6『파천검도』");

        new BukkitRunnable() {

            @Override
            public void run() {

                player.addPotionEffect(
                        new PotionEffect(
                                PotionEffectType.BLINDNESS,
                                20 * 5,
                                0
                        )
                );

                player.addPotionEffect(
                        new PotionEffect(
                                PotionEffectType.NAUSEA,
                                20 * 5,
                                0
                        )
                );

                player.setMaxHealth(
                        Math.max(
                                2.0,
                                player.getMaxHealth() - 1
                        )
                );

            }

        }.runTaskLater(
                Enginev1.getInstance(),
                20L * 20
        );

    }

}