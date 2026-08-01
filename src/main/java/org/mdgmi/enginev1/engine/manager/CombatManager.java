package org.mdgmi.enginev1.engine.manager;

import org.bukkit.Statistic;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.mdgmi.enginev1.engine.player.DataManager;
import org.mdgmi.enginev1.engine.player.PlayerData;
import org.mdgmi.enginev1.engine.player.progress.SkillProgress;
import org.mdgmi.enginev1.engine.skill.Skill;
import org.mdgmi.enginev1.engine.skill.context.SkillContext;
import org.mdgmi.enginev1.engine.skill.event.AttackSkill;
import org.mdgmi.enginev1.engine.skill.event.DamageSkill;
import org.mdgmi.enginev1.engine.status.StatusType;

public class CombatManager {

    public static double attack(
            Player attacker,
            LivingEntity victim,
            double damage
    ) {

        damage = applyHit(
                attacker,
                victim,
                damage
        );

        PlayerData data =
                DataManager.get(attacker);

        if (data.getStatuses()
                .has(StatusType.GIANT_TRANSFORMATION)) {

            giantAttack(
                    attacker,
                    victim,
                    damage
            );

        }

        return damage;

    }

    private static double applyHit(
            Player attacker,
            LivingEntity victim,
            double damage
    ) {

        PlayerData data =
                DataManager.get(attacker);

        for (Skill skill :
                data.getSkills().getAllSkills()) {

            SkillProgress progress =
                    data.getProgress()
                            .get(skill.getId());

            SkillContext context =
                    new SkillContext(
                            attacker,
                            data,
                            skill,
                            progress
                    );

            if (skill instanceof DamageSkill damageSkill) {

                damage =
                        damageSkill.modifyDamage(
                                context,
                                victim,
                                damage
                        );

            }

            if (skill instanceof AttackSkill attackSkill) {

                attackSkill.onAttack(
                        context,
                        victim
                );

            }

        }
        if (data.getStatuses().has(StatusType.TIME_OF_JUDGMENT)
                && victim instanceof Player target) {

            int kills = target.getStatistic(Statistic.PLAYER_KILLS);

            damage += kills;
        }
        if (data.getStatuses().has(StatusType.SHINSAL)
                && victim instanceof Player target) {

            int kills =
                    target.getStatistic(
                            Statistic.PLAYER_KILLS
                    );

            damage += kills * 1.5;

            victim.setFireTicks(
                    victim.getFireTicks()
                            + (40 + kills * 20)
            );

            double executePercent;

            if (kills >= 11)
                executePercent = 0.30;

            else if (kills >= 6)
                executePercent = 0.20;

            else if (kills >= 3)
                executePercent = 0.15;

            else
                executePercent = -1;

            if (executePercent > 0) {

                double remain =
                        victim.getHealth() - damage;

                if (remain <= victim.getMaxHealth() * executePercent) {

                    CombatManager.applyInternalDamage(
                            victim,
                            9999,
                            attacker
                    );

                    attacker.sendTitle(
                            "",
                            "§4『신살』",
                            0,
                            20,
                            10
                    );

                }

            }

        }
        return damage;

    }

    private static void giantAttack(
            Player attacker,
            LivingEntity original,
            double damage
    ) {

        for (Entity entity :
                attacker.getNearbyEntities(
                        4,
                        3,
                        4
                )) {

            if (!(entity instanceof LivingEntity victim))
                continue;

            if (victim == attacker)
                continue;

            if (victim == original)
                continue;

            if (!isInFront(
                    attacker,
                    victim,
                    120
            ))
                continue;

            double finalDamage =
                    applyHit(
                            attacker,
                            victim,
                            damage * 0.7
                    );

            CombatManager.applyInternalDamage(
                    victim,
                    finalDamage,
                    attacker
            );

        }

    }

    private static boolean isInFront(
            Player player,
            LivingEntity target,
            double angle
    ) {

        Vector look =
                player.getLocation()
                        .getDirection()
                        .normalize();

        Vector toTarget =
                target.getLocation()
                        .toVector()
                        .subtract(
                                player.getLocation()
                                        .toVector()
                        )
                        .normalize();

        double degrees =
                Math.toDegrees(
                        look.angle(
                                toTarget
                        )
                );

        return degrees <= angle / 2;

    }
    private static final ThreadLocal<Boolean> INTERNAL_DAMAGE =
            ThreadLocal.withInitial(() -> false);

    public static boolean isInternalDamage() {
        return INTERNAL_DAMAGE.get();
    }

    public static void applyInternalDamage(
            LivingEntity victim,
            double damage,
            Player attacker
    ) {

        if (damage <= 0 || victim.isDead())
            return;

        INTERNAL_DAMAGE.set(true);

        try {

            victim.damage(
                    damage,
                    attacker
            );

        } finally {

            INTERNAL_DAMAGE.set(false);

        }
    }
    public static void damage(
            Player attacker,
            LivingEntity victim,
            double damage
    ) {

        applyInternalDamage(
                victim,
                damage,
                attacker
        );

    }
}