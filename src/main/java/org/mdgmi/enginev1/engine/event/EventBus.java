package org.mdgmi.enginev1.engine.event;

import java.util.*;

public class EventBus {

    private static final Map<
            Class<? extends GameEvent>,
            List<EventListener<?>>
            > listeners =
            new HashMap<>();

    public static <
            T extends GameEvent
            > void register(
            Class<T> clazz,
            EventListener<T> listener
    ){

        listeners
                .computeIfAbsent(
                        clazz,
                        c->new ArrayList<>()
                )
                .add(listener);

    }

    @SuppressWarnings("unchecked")
    public static <
            T extends GameEvent
            > void publish(
            T event
    ){

        List<EventListener<?>> list =
                listeners.get(
                        event.getClass()
                );

        if(list==null)
            return;

        for(EventListener<?> listener:list){

            ((EventListener<T>)listener)
                    .handle(event);

        }

    }

}