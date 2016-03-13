package com.gard.testbed.ui;

import com.gard.testbed.abstractions.IEvent;
import com.gard.testbed.engine.EventBus;
import com.gard.testbed.engine.eventBus.events.ActivityEvent;
import com.gard.testbed.engine.eventBus.events.MessageType;
import com.gard.testbed.engine.eventBus.events.UiEvent;
import com.gard.testbed.engine.petrinet.Transition;
import rx.Subscription;
import rx.functions.Action1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Chris on 14/02/2016..
 * Text based UI to navigate (PetriNet) activities
 */

public class TestUI {

    private EventBus<IEvent> eventBus;
    private List<Subscription> subscriptions = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    boolean retry = false;

    public TestUI(EventBus<IEvent> eventBus) {
        this.eventBus = eventBus;
    }

    public void run(){
        // Request initial status to set up initial options list
        postEvent("eventBus", MessageType.GETSTATUS, "Request status update from ActivityManager");
    }

    // Handles all event this object is subscribed to receive
    private void eventHandler(IEvent event) {

        try {
            switch (event.getType()) {

                case COMPLETED:
                    // TEMP
                    System.out.println("UI received >> " + event.getTarget() + " = " + event.getType());
                    // TEMP
                    break;
                case STATUS:
                    // On each change of activity status offer current options and process user input
                    processOptions((ActivityEvent)event);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Present textual list of options based on activeTransitions and fire selected transition based on user input
    private void processOptions(ActivityEvent event) {
        List<Transition> activeTransitions = event.getActiveTransitions();
        // Exit run if no active transitions
        if (activeTransitions.isEmpty()) {
            System.out.println("ACTIVITY COMPLETED");
            System.exit(0);
        }
        String optionsString = "";
        int optionCount = 1;

        // Construct options list based on fireable transitions
        optionsString += "Options:\n";
        for (Transition transition: activeTransitions) {
            optionsString += optionCount++ + ": fire:(";
            optionsString += transition.getName() + ") ";
        }
        System.out.println(optionsString);

        // Process input
        retry = true;
        while (retry) {
            int input;
            try {
                input = Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {
                input = 0;
            }
            if (input < 1 || input > activeTransitions.size()) {
                // Handle incorrect input
                System.out.println("Incorrect option - please try again");
                System.out.println(optionsString);
            } else {
                // Send event to fire transition
                postCompleteEvent(activeTransitions.get(input - 1));
                retry = false;
            }
        }
    }


    public void postEvent(String target, MessageType type, String message) {
        eventBus.post(new UiEvent(target, type, message));
    }

    // Post COMPLETED UiEvent for fired transition
    public void postCompleteEvent(Transition transition) {
        eventBus.post(new UiEvent(transition.getName(), MessageType.COMPLETE, "Fire this transition"));
    }

    // Subscribe to eventBus listening to specific event type
    public void subscribe(Class eventClass) {
        subscriptions.add(eventBus.observeEvents(eventClass).subscribe(new Action1<IEvent>() {
            @Override
            public void call(IEvent e) {
                eventHandler((IEvent)e);
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
