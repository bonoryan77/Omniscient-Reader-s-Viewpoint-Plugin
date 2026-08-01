package org.mdgmi.enginev1.engine.skill.context;

import org.bukkit.entity.Player;
import org.mdgmi.enginev1.engine.player.PlayerData;
import org.mdgmi.enginev1.engine.player.progress.SkillProgress;
import org.mdgmi.enginev1.engine.skill.Skill;

public class SkillContext {

    private final Player player;
    private final PlayerData data;
    private final Skill skill;
    private final SkillProgress progress;

    public SkillContext(
            Player player,
            PlayerData data,
            Skill skill,
            SkillProgress progress
    ) {
        this.player = player;
        this.data = data;
        this.skill = skill;
        this.progress = progress;
    }

    public Player getPlayer() {
        return player;
    }

    public PlayerData getData() {
        return data;
    }

    public Skill getSkill() {
        return skill;
    }

    public SkillProgress getProgress() {
        return progress;
    }

    public int getLevel() {
        return progress.getLevel();
    }

    public int getMastery() {
        return progress.getMastery();
    }

}