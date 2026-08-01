package org.mdgmi.enginev1.characters.joonghyuk;

import org.bukkit.Material;
import org.mdgmi.enginev1.engine.ex.EXSkill;
import org.mdgmi.enginev1.engine.hidden.HiddenId;
import org.mdgmi.enginev1.engine.skill.SkillCategory;
import org.mdgmi.enginev1.engine.skill.SkillId;
import org.mdgmi.enginev1.engine.skill.SkillType;
import org.mdgmi.enginev1.engine.skill.context.SkillContext;

public class TranscedentSkill implements EXSkill {
    @Override
    public HiddenId getHiddenId() {
        return HiddenId.TRANSCENDENT;
    }

    @Override
    public SkillId getId() {
        return SkillId.TRANSCENDENT;
    }

    @Override
    public String getName() {
        return "초월좌";
    }

    @Override
    public SkillType getType() {
        return null;
    }

    @Override
    public SkillCategory getCategory() {
        return null;
    }

    @Override
    public Material getTriggerItem() {  return Material.PAPER;  }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public int getPlausibilityCost() {
        return 80;
    }

    @Override
    public void use(SkillContext context) {

    }
}
