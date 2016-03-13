package com.gard.testbed.engine.eventBusx.test;


import com.gard.testbed.engine.eventBusx.events.Event;
import com.gard.testbed.engine.eventBusx.events.MessageType;

/**
 * Created by Chris on 06/03/2016..
 */
public class EngagementEvent extends Event {

    public EngagementEvent(String target, MessageType type, String message) {
        super(target, type, message);
    }
}
