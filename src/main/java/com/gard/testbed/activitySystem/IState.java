package com.gard.testbed.activitySystem;

import java.util.List;

public interface IState {

    /**
     * Returns true if all outgoing transition triggers test true against blackboard
     * @return
     */
    boolean isCompleted(IBlackboard player);

    /**
     * Returns true if
     * @return
     */
    boolean isActive();

    String getId();

    boolean isFinal();

    void add(ITransition transition);

    List<ITransition> getTransitions();
}