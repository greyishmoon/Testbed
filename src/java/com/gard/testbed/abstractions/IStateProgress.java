package com.gard.testbed.abstractions;

/**
 * Created by Chris on 23/02/2016..
 */
public interface IStateProgress {

    IState getCurrentState();
    void setCurrentState(IState newState);

}
