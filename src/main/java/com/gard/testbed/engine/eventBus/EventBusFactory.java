package com.gard.testbed.engine.eventBus;

import com.google.common.eventbus.EventBus;

/**
 * Created by Chris on 24/03/2016..
 * Lazy instantiation of event buses
 */
public class EventBusFactory {

    private static EventBus activityBus;
    private static EventBus uiBus;

    public EventBusFactory() {}

    public synchronized static EventBus getActivityBus() {
        if (activityBus == null) {
            activityBus = new EventBus("Activity");
        }
        return activityBus;

    }public synchronized static EventBus getUiBus() {
        if (activityBus == null) {
            activityBus = new EventBus("UI");
        }
        return uiBus;
    }

}
