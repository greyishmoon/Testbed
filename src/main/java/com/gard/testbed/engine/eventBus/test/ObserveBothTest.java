package com.gard.testbed.engine.eventBus.test;

import com.gard.testbed.abstractions.IEvent;
import com.gard.testbed.engine.EventBus;
import com.gard.testbed.engine.eventBus.events.ActivityEvent;
import com.gard.testbed.engine.eventBus.events.MessageType;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by Chris on 06/03/2016..
 */
public class ObserveBothTest {

    private EventBus<IEvent> eventBus;
    private Subscription subscription;

    public ObserveBothTest(EventBus<IEvent> eventBus) {
        this.eventBus = eventBus;

        // subscribe to Activity events
        subscription = eventBus.observeEvents(ActivityEvent.class).subscribe(new Action1<IEvent>() {
            @Override
            public void call(IEvent e) {
                activityEventHandler((ActivityEvent)e);
            }
        });

        // subscribe to Engagement events
        subscription = eventBus.observeEvents(EngagementEvent.class).subscribe(new Action1<IEvent>() {
            @Override
            public void call(IEvent e) {
                engagementEventHandler((EngagementEvent)e);
            }
        });
    }


    private void activityEventHandler(ActivityEvent event) {
        System.out.println("BOTH event handler: " + event.getTarget() + " MSG: " + event.getMessage());
    }

    private void engagementEventHandler(EngagementEvent event) {
        System.out.println("BOTH event handler: " + event.getTarget() + " MSG: " + event.getMessage());
    }


    public void postEventTest(String name, String message) {
        eventBus.post(new EngagementEvent(name, MessageType.COMPLETE, message));
    }

}
