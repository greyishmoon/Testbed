package com.gard.testbed.engine.EventBus;

import com.gard.testbed.abstractions.IEvent;

/**
 * Created by Chris on 06/03/2016..
 */
public class ActivityEvent implements IEvent {

    private String name, activityMessage;

    public ActivityEvent(String name, String activityMessage) {
        this.name = name;
        this.activityMessage = activityMessage;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getActivityM() {
        return activityMessage;
    }
}
