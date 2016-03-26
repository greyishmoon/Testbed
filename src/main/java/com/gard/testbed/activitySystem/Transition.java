package com.gard.testbed.activitySystem;


import java.util.function.Predicate;

/**
 * Links two states and holds goal test (trigger) for transition to fire
 */

public class Transition implements ITransition {
    private final String source;
    private final String destination;
    private final Predicate<IBlackboard> trigger;

    public Transition(String source, String destination, Predicate<IBlackboard> trigger) {
        this.source = source;
        this.destination = destination;
        this.trigger = trigger;
    }

    @Override
    public boolean isComplete(IBlackboard player) {
        return trigger.test(player);
    }

    @Override
    public void onFire() {
        // Add any behaviours required when transition fires
        // TODO - remove println
        System.out.println("Log: Transition " + source + " OnFire fired");
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
