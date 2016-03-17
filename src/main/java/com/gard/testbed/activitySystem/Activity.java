package com.gard.testbed.activitySystem;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by va922kg on 3/15/16.
 */
public class Activity implements IActivity {

    private Map<String,IState> states = new HashMap<String, IState>();
    private String initialStateId;
    private String id;

    protected Activity(String id) {
        this.id = id;
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
    public void setInitialState(String initialStateId) {
        this.initialStateId = initialStateId;
    }

    @Override
    public IState getInitialState() {
        return get(this.initialStateId);
    }

    @Override
    public void getTransitionForState(String s) {

    }

    @Override
    public String getId() {
        return this.id;
    }


    @Override
    public void add(ITransition transition) {
        this.states.get(transition.Source()).add(transition);
    }
}
