package com.gard.testbed.activitySystem;

import java.util.function.Predicate;

/**
 * Links two states and holds goal test (trigger) for transition to fire
 */

public interface ITransition {

    // Returns transitions test predicate - its goal
    Predicate<IBlackboard> getTrigger();

    // Source state of transition (likely to be 'currentState' in ActivityRunner when this transition being tested
    String Source();

    // Source state of transition
    String Destination();

    // Returns true if goal condition tests true against blackboard facts
    boolean isComplete(IBlackboard player);

    // Executes behaviours required when transition fires
    void onFire();

}
