package com.gard.testbed.engine.fsm;

import com.gard.testbed.abstractions.IState;
import com.gard.testbed.abstractions.IStateProgress;
import com.gard.testbed.abstractions.IUpdatable;

/**
 * Created by Chris on 24/02/2016..
 */
public class Progress implements IStateProgress, IUpdatable {

    // Current state FSM is in
    private IState currentState;

    public Progress(IState startState) {
        this.currentState = startState;
    }

    @Override
    public IState getCurrentState() {
        return currentState;
    }

    @Override
    public void setCurrentState(IState newState) {
        currentState = newState;
    }

    @Override
    public void update() {
        currentState.checkTransitions(this);
    }
}
