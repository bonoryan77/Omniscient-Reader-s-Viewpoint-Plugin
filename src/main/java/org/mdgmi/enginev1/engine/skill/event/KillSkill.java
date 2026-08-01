package org.mdgmi.enginev1.engine.skill.event;

import org.bukkit.entity.LivingEntity;
import org.mdgmi.enginev1.engine.skill.context.SkillContext;

public interface KillSkill {

    void onKill(
            SkillContext context,
            LivingEntity victim
    );

}