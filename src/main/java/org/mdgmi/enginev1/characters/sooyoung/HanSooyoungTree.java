package org.mdgmi.enginev1.characters.sooyoung;

import org.mdgmi.enginev1.engine.charactertree.CharacterTree;
import org.mdgmi.enginev1.engine.skill.Skill;

import java.util.List;

public class HanSooyoungTree implements CharacterTree {
    @Override
    public String getName() {
        return "한수영";
    }

    @Override
    public List<Skill> getSkills() {
        return List.of(
                new AvatarSkill(),
                new PlagiarismSkill()
        );
    }
}
