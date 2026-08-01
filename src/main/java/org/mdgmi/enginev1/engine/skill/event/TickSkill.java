package org.mdgmi.enginev1.engine.skill.event;

import org.mdgmi.enginev1.engine.skill.context.SkillContext;

public interface TickSkill {

    void onTick(
            SkillContext context
    );

}