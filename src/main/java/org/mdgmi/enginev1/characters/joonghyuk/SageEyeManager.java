package org.mdgmi.enginev1.characters.joonghyuk;

import java.util.*;

public final class SageEyeManager {

    private static final Map<UUID, Set<UUID>> analyzed =
            new HashMap<>();

    private SageEyeManager() {
    }

    public static boolean hasAnalyzed(UUID viewer, UUID target) {

        return analyzed
                .getOrDefault(viewer, Collections.emptySet())
                .contains(target);

    }

    public static void analyze(UUID viewer, UUID target) {

        analyzed
                .computeIfAbsent(
                        viewer,
                        k -> new HashSet<>()
                )
                .add(target);

    }

    public static void clear(UUID viewer) {

        analyzed.remove(viewer);

    }

}