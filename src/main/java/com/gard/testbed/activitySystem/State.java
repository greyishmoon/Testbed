package com.gard.testbed.activitySystem;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents activity task. Holds list of outgoing transitions modelling progression paths with test conditions.
 * Can be an activity of sub-tasks representing nested sub-tasks
 * Level denotes sub-task heirarchy - 1 = top level goal based tasks, 2> = nested sub-tasks
 */

public class State implements IState {

    // Name of State
    private final String id;
    // Outgoing transitions
    private List<ITransition> transitions;
    // Records if there is an Activity for nested sub-tasks of the task
    // NOTE - sub-tasks Activity must be named the same as this task
    private boolean subTasksActivity = false;

    public State(String stateId) {
        this.id = stateId;
        this.transitions = new ArrayList<>();
    }

//    @Override
//    public boolean isCompleted(IBlackboard player) {
//        return transitions.stream().allMatch(e -> e.isComplete(player));
//    }

    @Override
    public void onEnter() {
        // TODO - remove println
        System.out.println("Log: State " + id + " OnEnter fired");
    }

    @Override
    public void onExit() {
        // TODO - remove println
        System.out.println("Log: State " + id + " OnExit fired");
    }

    @Override
    public boolean isFinal() {
        return transitions.isEmpty();
    }

    @Override
    public List<ITransition> getTransitions() {
        return transitions;
    }

    @Override
    public void setSubTaskActivityTrue() {
        subTasksActivity = true;
    }

    @Override
    public boolean hasSubTaskActivity() {
        return subTasksActivity;
    }

    @Override
    public void add(ITransition transition) {
        this.transitions.add(transition);
    }

    @Override
    public String getId() {
        return id;
    }
}
