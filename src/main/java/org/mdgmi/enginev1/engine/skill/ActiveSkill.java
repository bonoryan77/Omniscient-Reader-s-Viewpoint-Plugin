package org.mdgmi.enginev1.engine.skill;

import org.mdgmi.enginev1.engine.skill.context.SkillContext;

public interface ActiveSkill extends Skill {

    void use(SkillContext context);

}