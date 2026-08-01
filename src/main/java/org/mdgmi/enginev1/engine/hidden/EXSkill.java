package org.mdgmi.enginev1.engine.hidden;

import org.mdgmi.enginev1.engine.hidden.HiddenId;
import org.mdgmi.enginev1.engine.skill.Skill;

public interface EXSkill extends Skill {

    /**
     * 이 EX 스킬을 해금하기 위한 히든 조건
     */
    HiddenId getHiddenId();

}