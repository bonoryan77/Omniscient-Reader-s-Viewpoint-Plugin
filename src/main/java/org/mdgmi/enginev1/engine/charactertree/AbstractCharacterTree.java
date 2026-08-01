package org.mdgmi.enginev1.engine.charactertree;

import org.mdgmi.enginev1.engine.skill.Skill;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCharacterTree
        implements CharacterTree {

    protected final List<Skill> skills =
            new ArrayList<>();

    private final String name;

    protected AbstractCharacterTree(
            String name
    ) {

        this.name = name;

    }

    @Override
    public String getName() {

        return name;

    }

    @Override
    public List<Skill> getSkills() {

        return skills;

    }

}