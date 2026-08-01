package org.mdgmi.enginev1.engine.cooldown;

import org.mdgmi.enginev1.engine.skill.SkillId;

import java.util.EnumMap;
import java.util.Map;

public class CooldownContainer {

    private final Map<
            SkillId,
            Cooldown
            > cooldowns =
            new EnumMap<>(SkillId.class);

    public void start(
            SkillId skill,
            int seconds
    ) {

        cooldowns.put(
                skill,
                new Cooldown(seconds)
        );

    }

    public boolean isCooling(
            SkillId skill
    ) {

        Cooldown cooldown =
                cooldowns.get(skill);

        if(cooldown == null)
            return false;

        if(cooldown.isFinished()){

            cooldowns.remove(skill);

            return false;

        }

        return true;

    }

    public long getRemaining(
            SkillId skill
    ) {

        if(!isCooling(skill))
            return 0;

        return cooldowns.get(skill)
                .getRemainingSeconds();

    }

    public void clear(){

        cooldowns.clear();

    }

}