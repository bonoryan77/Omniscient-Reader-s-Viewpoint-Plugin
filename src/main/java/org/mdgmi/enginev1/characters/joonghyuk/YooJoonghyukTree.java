package org.mdgmi.enginev1.characters.joonghyuk;

import org.mdgmi.enginev1.engine.charactertree.CharacterTree;
import org.mdgmi.enginev1.engine.skill.Skill;

import java.util.List;

public class YooJoonghyukTree implements CharacterTree {
    @Override
    public String getName() {
        return "유중혁";
    }

    @Override
    public List<Skill> getSkills() {
        return List.of(
                new SkyBreakingSwordSkill(),
                new VermilionStepSkill(),
                new GiantTransformationSkill()
        );
    }
}
