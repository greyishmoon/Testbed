package com.gard.testbed.engine.EventBus;

import com.gard.testbed.abstractions.IEvent;

/**
 * Created by Chris on 06/03/2016..
 */
public class OtherEvent implements IEvent {

    String name;

    public OtherEvent(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
