package org.mdgmi.enginev1.engine.ex;

import org.mdgmi.enginev1.engine.hidden.HiddenId;
import org.mdgmi.enginev1.engine.skill.ActiveSkill;

public interface EXSkill extends ActiveSkill {

    HiddenId getHiddenId();

}