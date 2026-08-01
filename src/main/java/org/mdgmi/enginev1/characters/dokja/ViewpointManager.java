package org.mdgmi.enginev1.characters.dokja;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class ViewpointManager {

    private static final Map<UUID, UUID> viewing = new HashMap<>();

    private ViewpointManager() {
    }

    public static void start(Player dokja, Player target) {
        viewing.put(
                dokja.getUniqueId(),
                target.getUniqueId()
        );
    }

    public static void stop(Player dokja) {
        viewing.remove(dokja.getUniqueId());
    }

    public static boolean isViewing(
            Player dokja,
            Player target
    ) {

        UUID uuid = viewing.get(
                dokja.getUniqueId()
        );

        if (uuid == null)
            return false;

        return uuid.equals(
                target.getUniqueId()
        );
    }

}