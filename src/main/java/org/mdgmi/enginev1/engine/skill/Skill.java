package org.mdgmi.enginev1.engine.skill;

import org.bukkit.Material;

public interface Skill {

    SkillId getId();

    String getName();

    SkillType getType();

    SkillCategory getCategory();

    int getCooldown();

    int getPlausibilityCost();

    Material getTriggerItem();

    default String getDescription() {
        return "";
    }

}