package com.gard.testbed.engine.eventBus.events;

/**
 * Created by Chris on 24/03/2016..
 */
public class BlackboardEvent extends Event {
    public BlackboardEvent(String target, MessageType type, String message) {
        super(target, type, message);
    }
}
