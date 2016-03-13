package com.gard.testbed.engine;

import com.gard.testbed.abstractions.IEvent;
import com.gard.testbed.engine.eventBus.events.ActivityEvent;
import com.gard.testbed.engine.eventBus.events.MessageType;
import com.gard.testbed.engine.petrinet.Petrinet;
import com.gard.testbed.engine.petrinet.PetrinetException;
import com.gard.testbed.engine.petrinet.Transition;
import com.gard.testbed.helpers.ActivityFactory;
import rx.Subscription;
import rx.functions.Action1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris on 06/03/2016..
 * ActivityManager - manages PetriNets and recording progression through activity
 */
public class ActivityManager {

    private EventBus<IEvent> eventBus;
    private List<Subscription> subscriptions = new ArrayList<>();
    // Active Petri Net activity (convert to list of activities later?)
    private Petrinet activity = ActivityFactory.newTestSimplePetrinet();

    public ActivityManager(EventBus<IEvent> eventBus) {
        this.eventBus = eventBus;
    }

    // Handles all event this object is subscribed to receive
    private void eventHandler(IEvent event) {

        try {
            switch (event.getType()) {

                case COMPLETE:
                    // TEMP
                    System.out.println("AM received >> " + event.getTarget() + " = " + event.getType());
                    // TEMP
                    fireTransition(event);
                    // Broadcast updated status with new active transitions list
                    postStatusEvent();
                    break;
                case GETSTATUS:
                    // Respond to request for status
                    postStatusEvent();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Fire transition with same name as event.target (synchronised to avoid being called prior to completion by immediate second event)
    private synchronized void fireTransition(IEvent event) throws PetrinetException {

        // If target present in fireable transitions, fire it, if not throw exception
        // TODO - find out if this can be simplified - ?? return value from transition.fire is bool, so how to use that as condition for if statement
        if (activity.getTransitionsAbleToFire().stream().anyMatch(transition -> transition.getName() == event.getTarget())) {

            activity.getTransitionsAbleToFire().stream().filter(transition -> transition.getName() == event.getTarget())
                    .forEach(Transition::fire);

        } else {
            throw new PetrinetException(event.getTarget() + ": transition not fireable");
        }
    }

    // Post current status with list of active transitions
    private void postStatusEvent() {
        eventBus.post(new ActivityEvent(activity.getName(), MessageType.STATUS, "Current list of fireable transitions",
                activity.getTransitionsAbleToFire()));
    }

    // Subscribe to eventBus listening to specific event type
    public void subscribe(Class eventClass) {
        subscriptions.add(eventBus.observeEvents(eventClass).subscribe(new Action1<IEvent>() {
            @Override
            public void call(IEvent e) {
                eventHandler(e);
            }
        }));
    }

    // Unsubscribe from eventBus for specified subscription
    // TODO needs fixing - cant identify subscription using eventClass
    public void unsubscribe(Class eventClass) {
//        for (Iterator<Subscription> iterator = subscriptions.iterator(); iterator.hasNext();) {
//            Subscription subscription = iterator.next();
//            System.out.println(subscription.);
//            if (subscription.getClass() == eventClass) {
//                subscription.unsubscribe();
//                iterator.remove();
//            }
//        }
    }


}
