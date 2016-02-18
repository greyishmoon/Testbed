package com.gard.testbed.fsm;

/**
 * Created by va922kg on 2/18/16.
 */
public interface IStateProgress {
    IState getCurrentState();
    void setCurrentState(IState current);
    //
}
