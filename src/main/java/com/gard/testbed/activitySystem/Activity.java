package com.gard.testbed.activitySystem;

import java.util.HashMap;
import java.util.Map;

/**
 * Map of activity consisting of consisting of map of states linked by transitions.
 */
public class Activity implements IActivity {

    // Activity name
    private String id;

    // Activity level - 1 denotes top level goal tasks, with 2 > representing nested subtasks
    private int level;

    // Map of states for this activity level
    private Map<String, IState> states = new HashMap<String, IState>();
    // Starting state for activity
    private String initialStateId;

    protected Activity(String id, int level) {
        this.id = id;
        this.level = level;
    }

    @Override
    public IState get(String stateId) {
        return states.get(stateId);
    }

    @Override
    public void add(IState state) {
        this.states.put(state.getId(), state);
    }

    @Override
    public void add(ITransition transition) {
        this.states.get(transition.Source()).add(transition);
    }

    @Override
    public void setInitialState(String initialStateId) {
        this.initialStateId = initialStateId;
    }

    @Override
    public IState getInitialState() {
        return get(this.initialStateId);
    }

    @Override
    public void setSubTaskActivityTrue(String stateName) {
        this.states.get(stateName).setSubTaskActivityTrue();
    }

    @Override
    public void getTransitionForState(String s) {
    }

    @Override
    public String getId() {
        return this.id;
    }

    public int getLevel() {
        return level;
    }
}
