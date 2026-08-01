package org.mdgmi.enginev1.characters.dokja;

import org.mdgmi.enginev1.engine.charactertree.CharacterTree;
import org.mdgmi.enginev1.engine.skill.Skill;

import java.util.List;

public class KimDokjaTree implements CharacterTree {

    @Override
    public String getName() {
        return "김독자";
    }

    @Override
    public List<Skill> getSkills() {
        return List.of(
                new ElectrificationSkill(),
                new FourthWallSkill(),
                new OmniscientViewpointSkill(),
                new BookmarkSkill()
        );
    }

}