package org.mdgmi.enginev1.engine.charactertree;

import org.mdgmi.enginev1.engine.skill.Skill;

import java.util.List;

public interface CharacterTree {

    String getName();

    List<Skill> getSkills();

}