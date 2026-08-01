package org.mdgmi.enginev1.characters.dokja;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.mdgmi.enginev1.Enginev1;
import org.mdgmi.enginev1.engine.manager.CooldownManager;
import org.mdgmi.enginev1.engine.skill.ActiveSkill;
import org.mdgmi.enginev1.engine.skill.SkillCategory;
import org.mdgmi.enginev1.engine.skill.SkillId;
import org.mdgmi.enginev1.engine.skill.SkillType;
import org.mdgmi.enginev1.engine.skill.context.SkillContext;

public class OmniscientViewpointSkill implements ActiveSkill {

    @Override
    public SkillId getId() {
        return SkillId.OMNISCIENT_VIEWPOINT;
    }

    @Override
    public String getName() {
        return "전지적 독자 시점";
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
        return 180;
    }

    @Override
    public int getPlausibilityCost() {
        return 60;
    }

    @Override
    public Material getTriggerItem() {
        return Material.NETHER_STAR;
    }

    @Override
    public void use(SkillContext context) {

        Player player = context.getPlayer();

        if (CooldownManager.isCooling(player, this)) {
            player.sendMessage("§c궁극기가 아직 준비되지 않았습니다.");
            return;
        }

        if (!context.getData().consumePlausibility(getPlausibilityCost())) {
            player.sendMessage("§c개연성이 부족합니다.");
            return;
        }

        Player target = getTarget(player);

        if (target == null) {
            player.sendMessage("§c바라보고 있는 플레이어가 없습니다.");
            return;
        }

        CooldownManager.startCooldown(player, this);

        ViewpointManager.start(player, target);

        target.addPotionEffect(
                new PotionEffect(
                        PotionEffectType.GLOWING,
                        20 * 12,
                        0,
                        false,
                        false,
                        true
                )
        );

        player.playSound(
                player.getLocation(),
                Sound.BLOCK_BEACON_ACTIVATE,
                1f,
                1f
        );

        player.sendMessage(ChatColor.GOLD + "========== 전지적 독자 시점 ==========");
        player.sendMessage("§e대상 : §f" + target.getName());
        player.sendMessage("§e체력 : §f" + target.getHealth() + " / " + target.getMaxHealth());
        player.sendMessage(ChatColor.GOLD + "================================");

        new BukkitRunnable() {

            @Override
            public void run() {

                ViewpointManager.stop(player);

                target.removePotionEffect(
                        PotionEffectType.GLOWING
                );

                player.sendMessage("§7『전지적 독자 시점』이 종료되었습니다.");

            }

        }.runTaskLater(
                Enginev1.getInstance(),
                20L * 12
        );

    }

    private Player getTarget(Player player) {

        Entity entity =
                player.getTargetEntity(20);

        if(entity instanceof Player target)
            return target;

        return null;

    }

}