package org.mdgmi.enginev1.characters.dokja;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.mdgmi.enginev1.engine.manager.CooldownManager;
import org.mdgmi.enginev1.engine.manager.StatusManager;
import org.mdgmi.enginev1.engine.skill.ActiveSkill;
import org.mdgmi.enginev1.engine.skill.SkillCategory;
import org.mdgmi.enginev1.engine.skill.SkillId;
import org.mdgmi.enginev1.engine.skill.SkillType;
import org.mdgmi.enginev1.engine.skill.context.SkillContext;
import org.mdgmi.enginev1.engine.status.StatusType;

public class ElectrificationSkill implements ActiveSkill {

    @Override
    public SkillId getId() {
        return SkillId.ELECTRIFICATION;
    }

    @Override
    public String getName() {
        return "전인화";
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
    public int getCooldown() {
        return 90;
    }

    @Override
    public int getPlausibilityCost() {
        return 20;
    }

    @Override
    public Material getTriggerItem() {
        return Material.BLAZE_ROD;
    }

    @Override
    public void use(SkillContext context) {

        Player player = context.getPlayer();

        if (CooldownManager.isCooling(player, this)) {
            player.sendMessage("§c아직 쿨타임입니다.");
            return;
        }

        if (!context.getData().consumePlausibility(getPlausibilityCost())) {
            player.sendMessage("§c개연성이 부족합니다.");
            return;
        }

        StatusManager.addStatus(
                player,
                StatusType.ELECTRIFICATION,
                20 * 20,
                context.getLevel()
        );

        CooldownManager.startCooldown(player, this);

        player.playSound(
                player.getLocation(),
                Sound.ENTITY_LIGHTNING_BOLT_THUNDER,
                1.0f,
                1.2f
        );

        player.sendMessage("§b⚡ 전인화를 발동했습니다!");
    }
}