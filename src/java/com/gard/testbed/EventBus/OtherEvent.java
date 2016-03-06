package com.gard.testbed.EventBus;

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
