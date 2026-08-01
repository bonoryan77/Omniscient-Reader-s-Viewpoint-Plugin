package org.mdgmi.enginev1.characters.dokja;

import org.bukkit.Material;
import org.mdgmi.enginev1.engine.skill.PassiveSkill;
import org.mdgmi.enginev1.engine.skill.SkillCategory;
import org.mdgmi.enginev1.engine.skill.SkillId;
import org.mdgmi.enginev1.engine.skill.SkillType;

public class FourthWallSkill implements PassiveSkill {

    @Override
    public SkillId getId() {
        return SkillId.FOURTH_WALL;
    }

    @Override
    public String getName() {
        return "제4의 벽";
    }

    @Override
    public SkillType getType() {
        return SkillType.PASSIVE;
    }

    @Override
    public SkillCategory getCategory() {
        return SkillCategory.DEFAULT;
    }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public int getPlausibilityCost() {
        return 0;
    }

    @Override
    public Material getTriggerItem() {
        return Material.AIR;
    }

}