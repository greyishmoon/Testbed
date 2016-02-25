package com.gard.testbed.engine.fsm;

import com.gard.testbed.abstractions.IState;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris on 24/02/2016..
 * Holds all activity states of current activity
 */
public class Activity {

    // List of states
    private List<IState> stateList;
    // Record of starting state for activity (initially defaults to first state in stateList)
    private IState startState;
    private String name;

    public Activity(String name, ArrayList<IState> states) {
        this.name = name;
        stateList = states;
        startState = stateList.get(0);
    }

    public String getName() {
        return name;
    }

    public IState getStartState() {
        return startState;
    }

    public IState getState(String stateName) {
        // TODO - only works on top level states for now - not implemented for nested states
        for (IState state : stateList) {
            if (state.getName().equals(stateName)) {
                return state;
            }
        }
        return null;
    }

    public List<IState> getStateList() {
        return stateList;
    }
}
