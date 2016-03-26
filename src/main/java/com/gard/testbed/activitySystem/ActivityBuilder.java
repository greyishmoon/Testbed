package com.gard.testbed.activitySystem;

import java.util.function.Predicate;

/**
 * Created by va922kg on 3/15/16.
 */
public class ActivityBuilder {
    private IActivity activity;

    public static ActivityBuilder activity(String id, int level) {
        return new ActivityBuilder(id, level);
    }

    private ActivityBuilder(String id, int level) {
        this.activity = new Activity(id, level);
    }

    public ActivityBuilder addState(String stateId) {
        activity.add(new State(stateId));
        return this;
    }

    public ActivityBuilder setInitialState(String stateId) {
        activity.setInitialState(stateId);
        return this;
    }

    public ActivityBuilder setSubTaskActivityTrue(String stateName) {
        activity.setSubTaskActivityTrue(stateName);
        return this;
    }

    public ActivityBuilder addTransition(String src, String dst, Predicate<IBlackboard> trigger) {
        activity.add(new Transition(src, dst, trigger));
        return this;
    }

    public IActivity build() {
        return activity;
    }

}
