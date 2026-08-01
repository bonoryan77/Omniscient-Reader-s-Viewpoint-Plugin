package org.mdgmi.enginev1.engine.event;



public interface EventListener<T extends GameEvent>{

    void handle(T event);

}