package org.mdgmi.enginev1.characters.dokja;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.mdgmi.enginev1.engine.player.DataManager;
import org.mdgmi.enginev1.engine.player.PlayerData;
import org.mdgmi.enginev1.engine.skill.ActiveSkill;
import org.mdgmi.enginev1.engine.skill.Skill;
import org.mdgmi.enginev1.engine.skill.SkillCategory;
import org.mdgmi.enginev1.engine.skill.SkillId;
import org.mdgmi.enginev1.engine.skill.SkillType;
import org.mdgmi.enginev1.engine.skill.context.SkillContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class BookmarkSkill implements ActiveSkill {

    private static final int TARGET_RANGE = 20;

    @Override
    public SkillId getId() {
        return SkillId.BOOKMARK;
    }

    @Override
    public String getName() {
        return "책갈피";
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
        return Material.ENCHANTED_BOOK;
    }

    @Override
    public void use(
            SkillContext context
    ) {

        Player player =
                context.getPlayer();

        Player target =
                findTarget(player);

        if (target == null) {

            player.sendMessage(
                    "§c바라보고 있는 플레이어가 없습니다."
            );

            return;
        }

        List<Skill> copyableSkills =
                findCopyableSkills(target);

        if (copyableSkills.isEmpty()) {

            player.sendMessage(
                    "§c대상에게 복사 가능한 액티브 스킬이 없습니다."
            );

            return;
        }

        Skill copiedSkill =
                copyableSkills.get(
                        ThreadLocalRandom.current()
                                .nextInt(
                                        copyableSkills.size()
                                )
                );

        BookmarkManager.setBookmark(
                player,
                copiedSkill
        );

        player.sendTitle(
                "§6『책갈피』",
                "§f" + copiedSkill.getName(),
                10,
                40,
                10
        );

        player.playSound(
                player.getLocation(),
                Sound.ITEM_BOOK_PAGE_TURN,
                1.0f,
                0.7f
        );

        player.getWorld().spawnParticle(
                Particle.ENCHANT,
                player.getLocation().add(0, 1, 0),
                80,
                0.6,
                1.0,
                0.6,
                0.2
        );

        player.sendMessage(
                "§7대상: §f"
                        + target.getName()
        );

        player.sendMessage(
                "§7기록된 스킬: §6"
                        + copiedSkill.getName()
        );
    }

    private Player findTarget(
            Player player
    ) {

        Entity target =
                player.getTargetEntity(
                        TARGET_RANGE
                );

        if (!(target instanceof Player targetPlayer))
            return null;

        if (targetPlayer.equals(player))
            return null;

        return targetPlayer;
    }

    private List<Skill> findCopyableSkills(
            Player target
    ) {

        PlayerData targetData =
                DataManager.get(target);

        List<Skill> result =
                new ArrayList<>();

        for (Skill skill :
                targetData.getSkills()
                        .getAllSkills()) {

            if (!(skill instanceof ActiveSkill))
                continue;

            if (skill.getId()
                    == SkillId.BOOKMARK)
                continue;

            /*
             * EX와 궁극기는 복사 금지
             */
            if (skill.getCategory()
                    == SkillCategory.ULTIMATE)
                continue;

            result.add(skill);
        }

        return result;
    }
}