package com.gard.testbed.engine.eventBusx.events;

import com.gard.testbed.abstractions.IEvent;

/**
 * Created by Chris on 11/03/2016..
 * Basic event structure
 */
public class Event implements IEvent{

    private String target, message;
    private MessageType type;

    public Event(String target, MessageType type, String message) {
        this.message = message;
        this.type = type;
        this.target = target;
    }

    @Override
    public String getTarget() {
        return target;
    }

    @Override
    public MessageType getType() {
        return type;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return (this.getClass().getSimpleName() + " - " +
                " TARGET: " + getTarget() +
                " TYPE: " + getType() +
                " MSG: " + getMessage());
    }
}
