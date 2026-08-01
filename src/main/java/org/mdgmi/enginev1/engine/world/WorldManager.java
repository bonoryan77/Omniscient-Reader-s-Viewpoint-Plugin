package org.mdgmi.enginev1.engine.world;

import org.bukkit.Bukkit;

public class WorldManager {

    private static int plausibility = 100;

    public static int getPlausibility() {
        return plausibility;
    }

    public static void add(int amount) {

        plausibility =
                Math.min(
                        100,
                        plausibility + amount
                );

    }

    public static void remove(int amount) {

        plausibility =
                Math.max(
                        0,
                        plausibility - amount
                );

        check();

    }

    private static void check() {

        if (plausibility <= 75) {

            Bukkit.broadcastMessage(
                    "§7세계의 개연성이 흔들립니다."
            );

        }

        if (plausibility <= 50) {

            Bukkit.broadcastMessage(
                    "§6성좌들이 세계를 주시합니다."
            );

        }

        if (plausibility <= 25) {

            Bukkit.broadcastMessage(
                    "§4재앙이 강림하려 합니다."
            );

        }

        if (plausibility == 0) {

            Bukkit.broadcastMessage(
                    "§c『세계 붕괴』"
            );

            // TODO
            // 히든 보스
            // 시나리오 강제 시작
            // 재앙
        }

    }

}