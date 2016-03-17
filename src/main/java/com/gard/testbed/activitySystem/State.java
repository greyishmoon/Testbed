package com.gard.testbed.activitySystem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by va922kg on 3/15/16.
 */
public class State implements IState {
    private final String id;
    private List<ITransition> transitions;

    public State(String stateId) {
        this.id = stateId;
        this.transitions = new ArrayList<>();
    }

    @Override
    public boolean isCompleted(IBlackboard player) {
        return transitions.stream().allMatch(e -> e.isComplete(player));
    }

    @Override
    public boolean isActive() {
        return false;
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
    public void add(ITransition transition) {
        this.transitions.add(transition);
    }

    @Override
    public String getId() {
        return id;
    }
}
