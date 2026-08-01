package org.mdgmi.enginev1.engine.player.progress;

import org.mdgmi.enginev1.engine.skill.SkillId;

import java.util.EnumMap;
import java.util.Map;

public class SkillProgressContainer {

    private final Map<SkillId, SkillProgress> progress =
            new EnumMap<>(SkillId.class);

    public SkillProgress get(
            SkillId id
    ) {

        return progress.computeIfAbsent(
                id,
                key -> new SkillProgress()
        );

    }

    public boolean contains(
            SkillId id
    ) {

        return progress.containsKey(id);

    }

    public void clear() {

        progress.clear();

    }

}