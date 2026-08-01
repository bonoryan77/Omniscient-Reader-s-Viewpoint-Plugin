package org.mdgmi.enginev1.engine.npc;

import net.citizensnpcs.api.npc.NPC;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class AvatarData {

    private final Player owner;

    private final NPC npc;

    private LivingEntity target;

    public AvatarData(
            Player owner,
            NPC npc,
            int level
    ){

        this.owner = owner;
        this.npc = npc;

    }

    public Player getOwner() {
        return owner;
    }

    public NPC getNpc() {
        return npc;
    }

    public LivingEntity getTarget() {
        return target;
    }

    public void setTarget(
            LivingEntity target
    ) {
        this.target = target;
    }
    private long lastAttack;

    public long getLastAttack() {
        return lastAttack;
    }

    public void setLastAttack(long lastAttack) {
        this.lastAttack = lastAttack;
    }

}