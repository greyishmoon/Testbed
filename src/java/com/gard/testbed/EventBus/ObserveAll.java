package com.gard.testbed.EventBus;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by Chris on 06/03/2016..
 */
public class ObserveAll {

    private EventBus eventBus;
    private Subscription subscription;

    public ObserveAll(EventBus eventBus) {
        this.eventBus = eventBus;
        subscription = eventBus.observe().subscribe(new Action1<IEvent>() {
            @Override
            public void call(IEvent e) {
                eventHandler(e);
            }
        });
    }

    private void eventHandler(IEvent event) {
        System.out.println("ALL event handler: " + event.getName());
    }


}
