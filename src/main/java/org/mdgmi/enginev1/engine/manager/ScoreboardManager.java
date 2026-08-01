package org.mdgmi.enginev1.engine.manager;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;
import org.mdgmi.enginev1.engine.player.DataManager;
import org.mdgmi.enginev1.engine.player.PlayerData;
import org.mdgmi.enginev1.engine.skill.Skill;
import org.mdgmi.enginev1.engine.skill.SkillCategory;
import org.mdgmi.enginev1.engine.status.StatusInstance;

public class ScoreboardManager {

    public static void update(Player player) {

        PlayerData data = DataManager.get(player);

        org.bukkit.scoreboard.Scoreboard board =
                Bukkit.getScoreboardManager().getNewScoreboard();

        Objective objective =
                board.registerNewObjective(
                        "orv",
                        Criteria.DUMMY,
                        Component.text("§6§lORV")
                );

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        int line = 15;

        objective.getScore("§7━━━━━━━━━━━━").setScore(line--);

        objective.getScore(" ").setScore(line--);

        objective.getScore("§e마력").setScore(line--);
        objective.getScore("§f" + data.getPlausibility() + " / 100")
                .setScore(line--);

        objective.getScore("  ").setScore(line--);

        objective.getScore("§b상태").setScore(line--);

        if (data.getStatuses().getAll().isEmpty()) {

            objective.getScore("§7없음")
                    .setScore(line--);

        } else {

            for (StatusInstance status :
                    data.getStatuses().getAll()) {

                objective.getScore(
                        getIcon(status) + " "
                                + getName(status)
                                + " "
                                + (status.getDuration() / 20)
                                + "초"
                ).setScore(line--);

                if (line <= 0)
                    break;
            }

        }

        objective.getScore("§8 ").setScore(line--);

        player.setScoreboard(board);
        objective.getScore("§f캐릭터").setScore(line--);
        objective.getScore("§e" + data.getCharacterType().getDisplayName())
                .setScore(line--);
        for (Skill skill : data.getSkills().getAllSkills()) {

            if (skill.getCategory() != SkillCategory.ULTIMATE)
                continue;

            long remain =
                    data.getCooldown()
                            .getRemaining(skill.getId());

            objective.getScore("§f궁극기").setScore(line--);

            if (remain <= 0) {

                objective.getScore("§a준비 완료")
                        .setScore(line--);

            } else {

                objective.getScore("§c" + remain + "초")
                        .setScore(line--);

            }

        }
    }

    private static String getIcon(
            StatusInstance status
    ) {

        return switch (status.getType()) {

            case BURN -> "🔥";
            case BLEED -> "🩸";
            case POISON -> "☠";
            case STUN -> "💫";
            case FEAR -> "😨";
            case CONFUSION -> "❓";
            case SILENCE -> "🔇";
            case ELECTRIFICATION -> "⚡";
            case INVINCIBLE -> "🛡";
            case ATTACK_UP -> "🗡";
            case DEFENSE_UP -> "🛡";
            case SPEED_UP -> "💨";
            case REGENERATION -> "❤";
            case SHINSAL -> "⚔";
            case TIME_OF_JUDGMENT -> "⚖";
            case BLACK_HEAVEN_SWORD -> "🗡️";
            default -> "•";
        };


    }

    private static String getName(
            StatusInstance status
    ) {

        return switch (status.getType()) {

            case BURN -> "화상";
            case BLEED -> "출혈";
            case POISON -> "중독";
            case STUN -> "기절";
            case FEAR -> "공포";
            case CONFUSION -> "혼란";
            case SILENCE -> "침묵";
            case ELECTRIFICATION -> "감전";
            case INVINCIBLE -> "무적";
            case TIME_OF_JUDGMENT -> "심판의 시간";
            case SHINSAL -> "신살";
            case GIANT_TRANSFORMATION -> "거신화";
            case ATTACK_UP -> "공격 증가";
            case DEFENSE_UP -> "방어 증가";
            case SPEED_UP -> "속도 증가";
            case REGENERATION -> "재생";
            case BLACK_HEAVEN_SWORD -> "파천검도";
            default -> "상태";

        };

    }

}