package org.mdgmi.enginev1.characters.joonghyuk;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.mdgmi.enginev1.Enginev1;
import org.mdgmi.enginev1.engine.manager.CombatManager;
import org.mdgmi.enginev1.engine.manager.CooldownManager;
import org.mdgmi.enginev1.engine.skill.ActiveSkill;
import org.mdgmi.enginev1.engine.skill.SkillCategory;
import org.mdgmi.enginev1.engine.skill.SkillId;
import org.mdgmi.enginev1.engine.skill.SkillType;
import org.mdgmi.enginev1.engine.skill.context.SkillContext;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class VermilionStepSkill implements ActiveSkill {

    @Override
    public SkillId getId() {
        return SkillId.VERMILION_MOVEMENT;
    }

    @Override
    public String getName() {
        return "주작신보";
    }

    @Override
    public SkillType getType() {
        return SkillType.ACTIVE;
    }

    @Override
    public SkillCategory getCategory() {
        return SkillCategory.DEFAULT;
    }

    @Override
    public Material getTriggerItem() {
        return Material.GOLDEN_SWORD;
    }

    @Override
    public int getCooldown() {
        return 20;
    }

    @Override
    public int getPlausibilityCost() {
        return 20;
    }

    @Override
    public void use(SkillContext context) {

        Player player = context.getPlayer();

        if (CooldownManager.isCooling(player, this))
            return;

        if (!context.getData().consumePlausibility(getPlausibilityCost()))
            return;

        CooldownManager.startCooldown(player, this);

        Vector direction =
                player.getLocation()
                        .getDirection()
                        .normalize();

        player.setVelocity(
                direction.multiply(2.3)
        );

        Set<UUID> hit = new HashSet<>();

        new BukkitRunnable() {

            int tick = 0;

            LivingEntity lastTarget = null;

            @Override
            public void run() {

                tick++;

                for (Entity entity :
                        player.getNearbyEntities(
                                1.5,
                                1.5,
                                1.5
                        )) {

                    if (!(entity instanceof LivingEntity target))
                        continue;

                    if (target == player)
                        continue;

                    if (hit.contains(target.getUniqueId()))
                        continue;

                    hit.add(target.getUniqueId());

                    double damage =
                            CombatManager.attack(
                                    player,
                                    target,
                                    6 + context.getLevel()
                            );

                    target.damage(
                            damage,
                            player
                    );

                    lastTarget = target;

                }

                if (tick >= 10) {

                    if (lastTarget != null) {

                        lastTarget.setVelocity(
                                direction.clone()
                                        .multiply(1.2)
                                        .setY(0.5)
                        );

                    }

                    cancel();

                }

            }

        }.runTaskTimer(
                Enginev1.getInstance(),
                0L,
                1L
        );

    }

}