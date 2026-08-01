package org.mdgmi.enginev1.characters.sooyoung;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.mdgmi.enginev1.engine.player.PlayerData;
import org.mdgmi.enginev1.engine.skill.Skill;
import org.mdgmi.enginev1.engine.skill.SkillCategory;
import org.mdgmi.enginev1.engine.skill.SkillId;
import org.mdgmi.enginev1.engine.skill.SkillType;

public final class PlagiarismAI {

    private PlagiarismAI() {
    }

    public static double calculateScore(
            Player player,
            PlayerData data,
            Skill skill
    ) {

        // 복사 불가 스킬
        if (skill.getId() == SkillId.PLAGIARISM)
            return Double.NEGATIVE_INFINITY;

        if (skill.getCategory() == SkillCategory.ULTIMATE)
            return Double.NEGATIVE_INFINITY;

        if (data.getSkills().hasSkill(skill.getId()))
            return Double.NEGATIVE_INFINITY;

        double score = 0.0;

        double maxHealth = player
                .getAttribute(Attribute.GENERIC_MAX_HEALTH)
                .getValue();

        double healthRate =
                player.getHealth() / maxHealth;

        int plausibility =
                data.getPlausibility();

        /*
         * 기본 평가
         */

        if (skill.getType() == SkillType.PASSIVE) {
            score += 20;
        }

        if (skill.getType() == SkillType.ACTIVE) {
            score += 15;
        }

        if (skill.getCategory() == SkillCategory.ATTACK) {
            score += 10;
        }

        /*
         * 현재 상황에 따른 평가
         */

        // 체력이 낮으면 생존형 스킬 우선
        if (healthRate <= 0.35) {

            switch (skill.getId()) {

                case FOURTH_WALL ->
                        score += 80;


                case VERMILION_MOVEMENT ->
                        score += 45;

                default -> {
                }
            }
        }

        // 체력이 충분하면 공격형 스킬 우선
        if (healthRate >= 0.70) {

            switch (skill.getId()) {

                case ELECTRIFICATION ->
                        score += 55;

                case SKY_BREAKING_SWORD ->
                        score += 65;

                case TIME_OF_JUDGMENT ->
                        score += 55;

                case HELLFLAME ->
                        score += 45;

                default -> {
                }
            }
        }

        // 개연성이 적으면 패시브 우선
        if (plausibility < 30) {

            if (skill.getType() == SkillType.PASSIVE) {
                score += 50;
            }

            score -= skill.getPlausibilityCost();
        }

        // 개연성이 충분하면 강한 액티브도 고려
        if (plausibility >= 70
                && skill.getType() == SkillType.ACTIVE) {

            score += 25;
        }

        /*
         * 스킬 자체 효율 평가
         */

        // 개연성 소모가 적을수록 가산점
        score += Math.max(
                0,
                30 - skill.getPlausibilityCost()
        ) * 0.5;

        // 쿨타임이 짧을수록 가산점
        if (skill.getCooldown() > 0) {

            score += Math.max(
                    0,
                    120 - skill.getCooldown()
            ) * 0.1;
        }

        /*
         * 스킬별 기본 선호도
         */

        switch (skill.getId()) {

            case FOURTH_WALL ->
                    score += 30;

            case ELECTRIFICATION ->
                    score += 25;

            case SKY_BREAKING_SWORD ->
                    score += 30;

            case VERMILION_MOVEMENT ->
                    score += 20;

            case HELLFLAME ->
                    score += 25;

            case TIME_OF_JUDGMENT ->
                    score += 20;

            default -> {
            }
        }

        return score;
    }
}