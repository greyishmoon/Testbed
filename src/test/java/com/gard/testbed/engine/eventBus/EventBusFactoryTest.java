package com.gard.testbed.engine.eventBus;

import com.gard.testbed.engine.eventBus.events.ActivityEvent;
import com.gard.testbed.engine.eventBus.events.Event;
import com.gard.testbed.engine.eventBus.events.MessageType;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Chris on 24/03/2016..
 */
public class EventBusFactoryTest {

    @Test
    public void getActivityBus() throws Exception {

        EventBus activityBus = EventBusFactory.getActivityBus();

        assertEquals("Not singleton", activityBus, EventBusFactory.getActivityBus());

    }

    @Test
    public void eventPassing() throws Exception {

        // Test event handler
        class Handler {

            public String receivedString;
            public Event receivedEvent;

            // Gets notified of events of type String only
            @Subscribe
            public void listen(String message) {
                System.out.println("HANDLER String event received");
                receivedString = message;
            }

            // Gets notified of events of type Event AND its subclasses
            @Subscribe
            public void listen(ActivityEvent event) {
                System.out.println("HANDLER Event event received");
                receivedEvent = event;
            }
        }

        Handler handler = new Handler();
        // Register event handler object to listen to eventbus
        EventBusFactory.getActivityBus().register(handler);

        assertEquals("String should be null", null, handler.receivedString);
        assertEquals("Event should be null", null, handler.receivedEvent);

        EventBusFactory.getActivityBus().post("ABC");

        assertEquals("String event failure", "ABC", handler.receivedString);
        assertEquals("Event should be null", null, handler.receivedEvent);

        EventBusFactory.getActivityBus().post(new ActivityEvent("test", MessageType.COMPLETETASK, "DEF"));

        assertEquals("Event event failure", "DEF", handler.receivedEvent.getMessage());

    }

}