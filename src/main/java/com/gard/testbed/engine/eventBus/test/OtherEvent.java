package com.gard.testbed.engine.eventBus.test;

import com.gard.testbed.engine.eventBus.events.Event;
import com.gard.testbed.engine.eventBus.events.MessageType;

/**
 * Created by Chris on 06/03/2016..
 */
public class OtherEvent extends Event {

    public OtherEvent(String target, MessageType type, String message) {
        super(target, type, message);
    }


}
