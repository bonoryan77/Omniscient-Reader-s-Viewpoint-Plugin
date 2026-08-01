package org.mdgmi.enginev1.engine.status;

public class StatusInstance {

    private final StatusType type;

    private int duration;

    private final int amplifier;

    public StatusInstance(
            StatusType type,
            int duration,
            int amplifier
    ) {
        this.type = type;
        this.duration = duration;
        this.amplifier = amplifier;
    }

    public StatusType getType() {
        return type;
    }

    public int getDuration() {
        return duration;
    }

    public int getAmplifier() {
        return amplifier;
    }

    public void tick() {
        duration--;
    }

    public boolean expired() {
        return duration <= 0;
    }


}