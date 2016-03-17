package com.gard.testbed.activitySystem;

import rx.Subscription;

import java.util.function.Predicate;

/**
 * Created by va922kg on 3/15/16.
 */
public class Transition implements ITransition {
    private final String source;
    private final String destination;
    private final Predicate<IBlackboard> trigger;

    public Transition(String source, String destination, Predicate<IBlackboard> trigger) {
        this.source = source;
        this.destination = destination;
        this.trigger= trigger;
    }

    @Override
    public boolean isComplete(IBlackboard player) {
        return trigger.test(player);
    }

    @Override
    public Predicate<IBlackboard> getTrigger() {
        return trigger;
    }

    @Override
    public String Source() {
        return source;
    }

    @Override
    public String Destination() {
        return destination;
    }


}
