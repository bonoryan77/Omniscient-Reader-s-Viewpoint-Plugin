package org.mdgmi.enginev1.characters.heewon;

import org.mdgmi.enginev1.engine.charactertree.CharacterTree;
import org.mdgmi.enginev1.engine.skill.Skill;

import java.util.List;

public class JungHeewonTree implements CharacterTree {
    @Override
    public String getName() {
        return "정희원";
    }

    @Override
    public List<Skill> getSkills() {
        return List.of(
                new TimeOfJudgmentSkill(),
                new ShinsalSkill()
        );
    }
}
