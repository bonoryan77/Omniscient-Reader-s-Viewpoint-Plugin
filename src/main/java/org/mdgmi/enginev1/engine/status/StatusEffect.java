package org.mdgmi.enginev1.engine.status;

public class StatusEffect {

    private final StatusType type;

    private long endTime;

    private int amplifier;

    public StatusEffect(
            StatusType type,
            int seconds,
            int amplifier
    ) {

        this.type = type;
        this.endTime =
                System.currentTimeMillis()
                        + seconds * 1000L;

        this.amplifier = amplifier;

    }

    public StatusType getType() {

        return type;

    }

    public int getAmplifier() {

        return amplifier;

    }

    public void setAmplifier(
            int amplifier
    ) {

        this.amplifier = amplifier;

    }

    public boolean isExpired() {

        return System.currentTimeMillis()
                >= endTime;

    }

    public void extend(
            int seconds
    ) {

        endTime += seconds * 1000L;

    }

    public long getRemainingMillis() {

        return Math.max(
                0,
                endTime - System.currentTimeMillis()
        );

    }

}