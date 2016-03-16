package com.gard.testbed.activitySystem;

/**
 * Created by va922kg on 3/15/16.
 */
public interface IActivity {
    String getId();

    void setInitialState(String stateId);
    IState getInitialState();

    void getTransitionForState(String s);
    IState get(String stateId);

    void add(IState state);
    void add(ITransition transition);
}
