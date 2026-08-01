package org.mdgmi.enginev1.engine.cooldown;

public class Cooldown {

    private long endTime;

    public Cooldown(int seconds) {

        this.endTime =
                System.currentTimeMillis()
                        + seconds * 1000L;

    }

    public boolean isFinished() {

        return System.currentTimeMillis()
                >= endTime;

    }

    public long getRemainingMillis() {

        return Math.max(
                0,
                endTime
                        - System.currentTimeMillis()
        );

    }

    public long getRemainingSeconds() {

        return (long)Math.ceil(
                getRemainingMillis() / 1000.0
        );

    }

}