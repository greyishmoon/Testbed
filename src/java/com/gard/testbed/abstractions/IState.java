package com.gard.testbed.abstractions;

import com.gard.testbed.engine.fsm.Progress;

import java.util.List;

/**
 * Created by Chris on 23/02/2016..
 */
public interface IState extends ICompletable {

    void onEnter(IStateProgress progress);
    void checkTransitions(IStateProgress progress);
    void addTransition(ITransition transition);
    String getName();
    String getInstructions();
    void completeState();

}
