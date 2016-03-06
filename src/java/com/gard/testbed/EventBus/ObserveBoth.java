package com.gard.testbed.EventBus;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by Chris on 06/03/2016..
 */
public class ObserveBoth {

    private EventBus<IEvent> eventBus;
    private Subscription subscription;

    public ObserveBoth(EventBus<IEvent> eventBus) {
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
        System.out.println("BOTH event handler: " + event.getName() + " MSG: " + event.getActivityM());
    }

    private void engagementEventHandler(EngagementEvent event) {
        System.out.println("BOTH event handler: " + event.getName() + " MSG: " + event.getEngagementM());
    }


    public void postEventTest(String name, String message) {
        eventBus.post(new EngagementEvent(name, message));
    }

}
