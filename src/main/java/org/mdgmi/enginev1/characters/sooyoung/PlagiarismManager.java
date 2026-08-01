package org.mdgmi.enginev1.characters.sooyoung;

import org.bukkit.entity.Player;
import org.mdgmi.enginev1.engine.player.DataManager;
import org.mdgmi.enginev1.engine.player.PlayerData;
import org.mdgmi.enginev1.engine.skill.Skill;
import org.mdgmi.enginev1.engine.skill.SkillId;
import org.mdgmi.enginev1.engine.skill.SkillRegistry;

public final class PlagiarismManager {

    private PlagiarismManager() {
    }

    public static boolean copyBestSkill(
            Player player
    ) {

        PlayerData data =
                DataManager.get(player);

        if (!data.getSkills()
                .hasSkill(SkillId.PLAGIARISM)) {

            player.sendMessage(
                    "§c예상표절을 보유하고 있지 않습니다."
            );

            return false;
        }

        Skill bestSkill = null;
        double bestScore =
                Double.NEGATIVE_INFINITY;

        for (Skill skill :
                SkillRegistry.getAll()) {

            double score =
                    PlagiarismAI.calculateScore(
                            player,
                            data,
                            skill
                    );

            if (score > bestScore) {

                bestScore = score;
                bestSkill = skill;
            }
        }

        if (bestSkill == null) {

            player.sendMessage(
                    "§7복사할 수 있는 적합한 스킬이 없습니다."
            );

            return false;
        }

        data.getSkills()
                .removeSkill(
                        SkillId.PLAGIARISM
                );

        data.getSkills()
                .addSkill(bestSkill);

        player.sendTitle(
                "§5『예상표절』",
                "§d" + bestSkill.getName()
                        + "을(를) 이해했습니다.",
                10,
                50,
                10
        );

        player.sendMessage(
                "§d['설화' 분석 완료] §f현재 상황에 가장 적합한 스킬은 §e"
                        + bestSkill.getName()
                        + "§f입니다."
        );

        player.sendMessage(
                "§7적합도 점수: "
                        + String.format(
                        "%.1f",
                        bestScore
                )
        );

        return true;
    }
}