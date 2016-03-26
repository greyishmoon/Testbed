package com.gard.testbed.activitySystem;

import java.util.List;

/**
 * Represents activity task. Holds list of outgoing transitions modelling progression paths with test conditions.
 */

public interface IState {

    // Returns state ID
    String getId();

    // Executes behaviours required when state becomes curernt
    void onEnter();

    // Executes behaviours required when state is left via one of its transitions
    void onExit();

    // Returns true if there are no transitions - indicates there is no progression so activity is completed
    boolean isFinal();

    // Adds new transition
    void add(ITransition transition);

    // Returns list of transitions
    List<ITransition> getTransitions();

    // Sets if this task has an associated activity representing sub-tasks of this task
    void setSubTaskActivityTrue();

    // Returns if task has  an associated activity representing sub-tasks of this task
    boolean hasSubTaskActivity();
}