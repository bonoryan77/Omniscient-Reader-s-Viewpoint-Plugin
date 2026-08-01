package org.mdgmi.enginev1.characters.sooyoung;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.mdgmi.enginev1.engine.skill.ActiveSkill;
import org.mdgmi.enginev1.engine.skill.SkillCategory;
import org.mdgmi.enginev1.engine.skill.SkillId;
import org.mdgmi.enginev1.engine.skill.SkillType;
import org.mdgmi.enginev1.engine.skill.context.SkillContext;

public class PlagiarismSkill
        implements ActiveSkill {

    @Override
    public SkillId getId() {
        return SkillId.PLAGIARISM;
    }

    @Override
    public String getName() {
        return "예상표절";
    }

    @Override
    public SkillType getType() {
        return SkillType.ACTIVE;
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
        return 50;
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



        boolean success =
                PlagiarismManager.copyBestSkill(
                        player
                );

        if (!success)
            return;

        context.getData()
                .consumePlausibility(
                        getPlausibilityCost()
                );
    }
}