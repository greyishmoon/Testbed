package com.gard.testbed.EventBus;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by Chris on 06/03/2016..
 *
 * Mock-up of ActivityManager - manages PetriNets and recording progression through activity
 */
public class ActivityManager {

    private EventBus<IEvent> eventBus;
    private Subscription subscription;

    public ActivityManager(EventBus<IEvent> eventBus) {
        this.eventBus = eventBus;
        subscription = eventBus.observeEvents(ActivityEvent.class).subscribe(new Action1<IEvent>() {
            @Override
            public void call(IEvent e) {
                eventHandler((ActivityEvent)e);
            }
        });
    }


    private void eventHandler(ActivityEvent event) {
        System.out.println("AM event handler: " + event.getName() + " MSG: " + event.getActivityM());
    }

    public void postEventTest(String name, String message) {
        eventBus.post(new EngagementEvent(name, message));
    }

    public void unsuscribe() {
        subscription.unsubscribe();
    }
}
