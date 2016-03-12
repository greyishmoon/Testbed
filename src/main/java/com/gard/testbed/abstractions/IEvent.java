package com.gard.testbed.abstractions;

import com.gard.testbed.engine.eventBus.events.MessageType;

/**
 * Created by Chris on 06/03/2016..
 */
public interface IEvent {

    String getTarget();
    MessageType getType();
    String getMessage();
}
