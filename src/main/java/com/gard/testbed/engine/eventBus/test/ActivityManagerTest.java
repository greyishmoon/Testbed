package com.gard.testbed.engine.eventBus.test;

import com.gard.testbed.abstractions.IEvent;
import com.gard.testbed.engine.EventBus;
import com.gard.testbed.engine.eventBus.events.ActivityEvent;
import com.gard.testbed.engine.eventBus.events.MessageType;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by Chris on 06/03/2016..
 *
 * Mock-up of ActivityManager - manages PetriNets and recording progression through activity
 */
public class ActivityManagerTest {

    private EventBus<IEvent> eventBus;
    private Subscription subscription;

    public ActivityManagerTest(EventBus<IEvent> eventBus) {
        this.eventBus = eventBus;
        subscription = eventBus.observeEvents(ActivityEvent.class).subscribe(new Action1<IEvent>() {
            @Override
            public void call(IEvent e) {
                eventHandler((ActivityEvent)e);
            }
        });
    }


    private void eventHandler(ActivityEvent event) {
        System.out.println("AM event handler: " + event.getTarget() + " MSG: " + event.getMessage());
    }

    public void postEvent(String name, MessageType type, String message) {
        eventBus.post(new ActivityEvent(name, type, message));
    }

    public void unsuscribe() {
        subscription.unsubscribe();
    }
}
