package org.mdgmi.enginev1.engine.skill;

import java.util.*;

public final class SkillRegistry {

    private static final Map<SkillId, Skill> skills =
            new EnumMap<>(SkillId.class);

    private SkillRegistry() {
    }

    /**
     * 스킬 등록
     */
    public static void register(
            Skill skill
    ) {

        skills.put(
                skill.getId(),
                skill
        );

    }

    /**
     * ID로 찾기
     */
    public static Skill find(
            SkillId id
    ) {

        return skills.get(id);

    }

    /**
     * 기존 get()도 유지
     */
    public static Skill get(
            SkillId id
    ) {

        return find(id);

    }

    /**
     * 모든 스킬
     */
    public static Collection<Skill> getSkills() {

        return skills.values();

    }

    /**
     * 기존 코드 호환
     */
    public static Collection<Skill> getAll() {

        return getSkills();

    }

}