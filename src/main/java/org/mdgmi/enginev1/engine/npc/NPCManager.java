package org.mdgmi.enginev1.engine.npc;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public final class NPCManager {

    private NPCManager() {
    }

    public static NPC createPlayerNPC(
            String name,
            Location location
    ) {

        NPC npc =
                CitizensAPI.getNPCRegistry()
                        .createNPC(
                                EntityType.PLAYER,
                                name
                        );

        npc.spawn(location);
        if (npc.getEntity() instanceof Player avatarPlayer) {

            npc.setProtected(false);

            avatarPlayer.setAllowFlight(false);
            avatarPlayer.setFlying(false);

            avatarPlayer.setInvulnerable(false);
            avatarPlayer.setCollidable(true);

            avatarPlayer.setWalkSpeed(0.45f);
        }
        return npc;
    }

    public static void remove(
            NPC npc
    ) {

        if (npc == null)
            return;

        /*
         * isSpawned 여부와 관계없이 destroy해야
         * Citizens 레지스트리에서도 완전히 삭제된다.
         */
        npc.destroy();
    }
}