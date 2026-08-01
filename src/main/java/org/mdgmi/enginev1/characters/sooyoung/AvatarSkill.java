package org.mdgmi.enginev1.characters.sooyoung;

import org.bukkit.Material;
import org.mdgmi.enginev1.engine.skill.ActiveSkill;
import org.mdgmi.enginev1.engine.skill.SkillCategory;
import org.mdgmi.enginev1.engine.skill.SkillId;
import org.mdgmi.enginev1.engine.skill.SkillType;
import org.mdgmi.enginev1.engine.skill.context.SkillContext;

public class AvatarSkill implements ActiveSkill {

    @Override
    public SkillId getId() {
        return SkillId.AVATAR;
    }

    @Override
    public String getName() {
        return "아바타";
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
        return 80;
    }

    @Override
    public Material getTriggerItem() {
        return Material.BOOK;
    }

    @Override
    public void use(SkillContext context) {

        AvatarManager.spawnAvatar(
                context.getPlayer(),
                context.getProgress().getLevel()
        );

    }
}