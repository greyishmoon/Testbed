package com.gard.testbed.engine.eventBus.test;

import com.gard.testbed.abstractions.IEvent;
import com.gard.testbed.engine.EventBus;
import com.gard.testbed.engine.eventBus.events.MessageType;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by Chris on 06/03/2016..
 *
 * Mock-up of EngagementAgentTest - manages monitoring of user engagement and source of task completed messages
 * (for ActivityManager to respond too)
 */
public class EngagementAgentTest {

    private EventBus<IEvent> eventBus;
    private Subscription subscription;

    public EngagementAgentTest(EventBus<IEvent> eventBus) {
        this.eventBus = eventBus;
        subscription = eventBus.observeEvents(EngagementEvent.class).subscribe(new Action1<IEvent>() {
            @Override
            public void call(IEvent e) {
                eventHandler((EngagementEvent)e);
            }
        });
    }

    private void eventHandler(EngagementEvent event) {
        System.out.println("EA event handler: " + event.getTarget() + " MSG: " + event.getMessage());
    }

    public void postEventTest(String name, String message) {
        eventBus.post(new EngagementEvent(name, MessageType.COMPLETE, message));
    }

    public void unsuscribe() {
        subscription.unsubscribe();
    }
}
