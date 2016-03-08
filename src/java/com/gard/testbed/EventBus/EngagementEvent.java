package com.gard.testbed.EventBus;


/**
 * Created by Chris on 06/03/2016..
 */
public class EngagementEvent implements IEvent{

    private String name, engagementMessage;

    public EngagementEvent(String name, String engagementMessage) {
        this.name = name;
        this.engagementMessage = engagementMessage;
    }

    public String getName() {
        return name;
    }

    public String getEngagementM() {
        return engagementMessage;
    }
}
