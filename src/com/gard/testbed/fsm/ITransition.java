package com.gard.testbed.fsm;

/**
 * Created by va922kg on 2/18/16.
 */
public interface ITransition extends ICompletable{
    IState source();
    IState destination();
}
