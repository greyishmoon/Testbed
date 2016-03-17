package com.gard.testbed.activitySystem;

import java.util.function.Predicate;

/**
 * Created by va922kg on 3/15/16.
 */
public class ActivityBuilder {
    private IActivity activity;

    public static ActivityBuilder activity(String id){
        return new ActivityBuilder(id);
    }
    private ActivityBuilder(String id) {
        this.activity = new Activity(id);
    }

    public ActivityBuilder addState(String stateId) {
        activity.add(new State(stateId));
        return this;
    }

    public ActivityBuilder setInitialState(String stateId) {
        activity.setInitialState( stateId);
        return this;
    }

    public ActivityBuilder addTransition(String src, String dst, Predicate<IBlackboard> trigger) {
        activity.add( new Transition(src,  dst,  trigger));
        return this;
    }

    public IActivity build() {
        return activity;
    }

    public static IActivity generateLinearTestActivity() {
        return ActivityBuilder.activity("Omelette").
                addState("Task 1").
                setInitialState("Task 1").
                addTransition("Task 1", "Task 2", (b -> b.query("Task 1") >= 1)).   // 1 = task completed
                addState("Task 2").
                addTransition("Task 2", "Task 3", (b -> b.query("Task 2") >= 1)).
                addState("Task 3").
                addTransition("Task 3", "End", (b -> b.query("Task 3") >= 1)).
                addState("End").                                                    // no transitions so isFinal
                build();
    }
}
