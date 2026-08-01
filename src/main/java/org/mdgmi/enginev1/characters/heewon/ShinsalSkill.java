package org.mdgmi.enginev1.characters.heewon;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.mdgmi.enginev1.Enginev1;
import org.mdgmi.enginev1.engine.manager.CooldownManager;
import org.mdgmi.enginev1.engine.manager.StatusManager;
import org.mdgmi.enginev1.engine.player.PlayerData;
import org.mdgmi.enginev1.engine.skill.ActiveSkill;
import org.mdgmi.enginev1.engine.skill.SkillCategory;
import org.mdgmi.enginev1.engine.skill.SkillId;
import org.mdgmi.enginev1.engine.skill.SkillType;
import org.mdgmi.enginev1.engine.skill.context.SkillContext;
import org.mdgmi.enginev1.engine.status.StatusType;

public class ShinsalSkill implements ActiveSkill {

    @Override
    public SkillId getId() {
        return SkillId.SHINSAL;
    }

    @Override
    public String getName() {
        return "신살";
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
        return 300;
    }

    @Override
    public int getPlausibilityCost() {
        return 100;
    }

    @Override
    public Material getTriggerItem() {
        return Material.NETHERITE_AXE;
    }

    @Override
    public void use(SkillContext context) {


        Player player = context.getPlayer();
        PlayerData data = context.getData();

        if (CooldownManager.isCooling(player, this))
            return;

        if (!data.consumePlausibility(getPlausibilityCost())) {

            player.sendMessage("§c개연성이 부족합니다.");
            return;

        }

        CooldownManager.startCooldown(player, this);

        StatusManager.addStatus(
                player,
                StatusType.SHINSAL,
                20 * 20,
                1
        );

        player.sendTitle(
                "§4『신살』",
                "§c죄를 심판합니다.",
                10,
                40,
                10
        );

        player.getWorld().strikeLightningEffect(
                player.getLocation()
        );

        player.playSound(
                player.getLocation(),
                Sound.ENTITY_WITHER_SPAWN,
                1,
                0.7f
        );

        player.getWorld().spawnParticle(
                Particle.SOUL_FIRE_FLAME,
                player.getLocation().add(0,1,0),
                150,
                0.8,
                1,
                0.8,
                0.02
        );

    }

}