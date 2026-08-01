package org.mdgmi.enginev1.characters.joonghyuk;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.mdgmi.enginev1.Enginev1;
import org.mdgmi.enginev1.engine.manager.CooldownManager;
import org.mdgmi.enginev1.engine.player.PlayerData;
import org.mdgmi.enginev1.engine.skill.ActiveSkill;
import org.mdgmi.enginev1.engine.skill.SkillCategory;
import org.mdgmi.enginev1.engine.skill.SkillId;
import org.mdgmi.enginev1.engine.skill.SkillType;
import org.mdgmi.enginev1.engine.skill.context.SkillContext;

public class GiantTransformationSkill implements ActiveSkill {

    @Override
    public SkillId getId() {
        return SkillId.GIANT_TRANSFORMATION;
    }

    @Override
    public String getName() {
        return "거신화";
    }

    @Override
    public SkillType getType() {
        return SkillType.ACTIVE;
    }

    @Override
    public SkillCategory getCategory() {
        return SkillCategory.ULTIMATE;
    }

    @Override
    public int getCooldown() {
        return 500;
    }

    @Override
    public int getPlausibilityCost() {
        return 100;
    }

    @Override
    public Material getTriggerItem() {
        return Material.NETHERITE_SWORD;
    }

    @Override
    public void use(SkillContext context) {

        Player player = context.getPlayer();
        PlayerData data = context.getData();

        // 경기당 1회
        if (data.isGiantTransformationUsed()) {
            player.sendMessage("§c이미 거신화를 사용했습니다.");
            return;
        }

        // 체력 50% 이하
        double maxHealth =
                player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();

        if (player.getHealth() > maxHealth / 2.0) {
            player.sendMessage("§c체력이 50% 이하일 때만 사용할 수 있습니다.");
            return;
        }

        // 개연성
        if (!data.consumePlausibility(getPlausibilityCost())) {
            player.sendMessage("§c개연성이 부족합니다.");
            return;
        }

        // 쿨타임
        if (CooldownManager.isCooling(player, this)) {
            long remain = CooldownManager.getRemaining(player, this);

            player.sendMessage(
                    "§c쿨타임 : " +
                            String.format("%.1f", remain / 1000D) +
                            "초"
            );

            return;
        }

        data.setGiantTransformationUsed(true);

        CooldownManager.startCooldown(
                player,
                this
        );

        new GiantTransformationTask(player)
                .start(
                        Enginev1.getInstance()
                );

    }

}