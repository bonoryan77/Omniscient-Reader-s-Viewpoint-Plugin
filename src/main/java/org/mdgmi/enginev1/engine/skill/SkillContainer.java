package org.mdgmi.enginev1.engine.skill;

import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;

public class SkillContainer {

    private final Map<SkillId, Skill> skills =
            new EnumMap<>(SkillId.class);

    public void addSkill(
            Skill skill
    ) {

        skills.put(
                skill.getId(),
                skill
        );

    }

    public void removeSkill(
            SkillId id
    ) {

        skills.remove(id);

    }

    public boolean hasSkill(
            SkillId id
    ) {

        return skills.containsKey(id);

    }

    public Skill getSkill(
            SkillId id
    ) {

        return skills.get(id);

    }

    public Collection<Skill> getAllSkills() {

        return skills.values();

    }

    public void clear() {

        skills.clear();

    }

    public int size() {

        return skills.size();

    }

    public boolean isEmpty() {

        return skills.isEmpty();

    }

}