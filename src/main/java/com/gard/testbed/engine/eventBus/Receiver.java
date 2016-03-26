package com.gard.testbed.engine.eventBus;


import com.gard.testbed.engine.eventBus.events.ActivityEvent;
import com.gard.testbed.engine.eventBus.events.Event;
import com.google.common.eventbus.Subscribe;

/**
 * Created by Chris on 24/03/2016..
 */
public class Receiver {
    public String receivedString;
    public Event receivedEvent;

    public Receiver() {
        EventBusFactory.getActivityBus().register(this);
    }

    @Subscribe
    public void listen(ActivityEvent event) {
        System.out.println("LISTENER Event Message received: " + event.getMessage());
        receivedEvent = event;
    }

    @Subscribe
    public void listen(String message) {
        System.out.println("LISTENER String Message received: " + message);
        receivedString = message;
    }


}
