package org.mdgmi.enginev1.engine.event;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class AttackEvent
        extends GameEvent{

    private final Player attacker;

    private final LivingEntity victim;

    public AttackEvent(
            Player attacker,
            LivingEntity victim
    ){

        this.attacker = attacker;
        this.victim = victim;

    }

    public Player getAttacker(){

        return attacker;

    }

    public LivingEntity getVictim(){

        return victim;

    }

}