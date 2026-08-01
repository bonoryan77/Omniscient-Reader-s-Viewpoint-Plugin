package org.mdgmi.enginev1.engine.status;

import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;

public class StatusContainer {

    private final Map<StatusType, StatusInstance> statuses =
            new EnumMap<>(StatusType.class);

    public void add(StatusInstance status) {
        statuses.put(status.getType(), status);
    }

    public StatusInstance get(StatusType type) {
        return statuses.get(type);
    }

    public boolean has(StatusType type) {
        return statuses.containsKey(type);
    }

    public void remove(StatusType type) {
        statuses.remove(type);
    }

    public Collection<StatusInstance> getAll() {
        return statuses.values();
    }

    public void tick() {

        statuses.values().removeIf(status -> {

            status.tick();
            return status.expired();

        });

    }
}