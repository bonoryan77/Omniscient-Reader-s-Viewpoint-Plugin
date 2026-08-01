package org.mdgmi.enginev1.characters.heewon;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.mdgmi.enginev1.engine.manager.CooldownManager;
import org.mdgmi.enginev1.engine.manager.StatusManager;
import org.mdgmi.enginev1.engine.player.PlayerData;
import org.mdgmi.enginev1.engine.skill.ActiveSkill;
import org.mdgmi.enginev1.engine.skill.SkillCategory;
import org.mdgmi.enginev1.engine.skill.SkillId;
import org.mdgmi.enginev1.engine.skill.SkillType;
import org.mdgmi.enginev1.engine.skill.context.SkillContext;
import org.mdgmi.enginev1.engine.status.StatusType;

import java.util.Random;

public class TimeOfJudgmentSkill implements ActiveSkill {

    private static final Random RANDOM = new Random();

    @Override
    public SkillId getId() {
        return SkillId.TIME_OF_JUDGMENT;
    }

    @Override
    public String getName() {
        return "심판의 시간";
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
        return 60;
    }

    @Override
    public int getPlausibilityCost() {
        return 40;
    }

    @Override
    public Material getTriggerItem() {
        return Material.IRON_SWORD;
    }

    @Override
    public void use(SkillContext context) {

        Player player = context.getPlayer();
        PlayerData data = context.getData();

        if (CooldownManager.isCooling(player, this)) {
            player.sendMessage("§c아직 사용할 수 없습니다.");
            return;
        }

        if (!data.consumePlausibility(getPlausibilityCost())) {
            player.sendMessage("§c개연성이 부족합니다.");
            return;
        }

        CooldownManager.startCooldown(player, this);

        if (RANDOM.nextBoolean()) {

            StatusManager.addStatus(
                    player,
                    StatusType.TIME_OF_JUDGMENT,
                    20 * 30,
                    1
            );

            player.sendTitle(
                    "§6『심판의 시간』",
                    "§e죄를 심판합니다.",
                    10,
                    40,
                    10
            );

            player.playSound(
                    player.getLocation(),
                    Sound.ITEM_TRIDENT_THUNDER,
                    1f,
                    1f
            );

            player.getWorld().spawnParticle(
                    Particle.SOUL_FIRE_FLAME,
                    player.getLocation().add(0,1,0),
                    100,
                    0.5,
                    1,
                    0.5,
                    0.02
            );

        } else {

            player.sendMessage("§7심판은 내려지지 않았습니다.");

        }

    }

}