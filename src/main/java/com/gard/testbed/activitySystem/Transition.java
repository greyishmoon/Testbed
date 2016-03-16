package com.gard.testbed.activitySystem;

import java.util.function.Predicate;

/**
 * Created by va922kg on 3/15/16.
 */
public class Transition implements ITransition {
    private final String src;
    private final String dst;
    private final Predicate<IBlackboard> trigger;

    public Transition(String src, String dst, Predicate<IBlackboard> trigger) {
        this.src = src;
        this.dst = dst;
        this.trigger= trigger;
    }

    @Override
    public String Src() {
        return src;
    }

    @Override
    public Predicate<IBlackboard> getTrigger() {
        return trigger;
    }

    @Override
    public String Dst() {
        return dst;
    }
}
