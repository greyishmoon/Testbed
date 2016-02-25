package com.gard.testbed.engine.fsm;

import com.gard.testbed.abstractions.IState;
import com.gard.testbed.abstractions.IStateProgress;
import com.gard.testbed.abstractions.ITransition;

/**
 * Created by Chris on 24/02/2016..
 */
public class Transition implements ITransition {

    private final IState source;
    private final IState destination;

    public Transition(IState source, IState destination) {
        this.source = source;
        this.destination = destination;
    }
    // TODO - Implement predicates / functions
//    private final Predicate<???> completionFunction;

    @Override
    public IState source() {
        return source;
    }

    @Override
    public IState destination() {
        return destination;
    }

    @Override
    public boolean isComplete(IStateProgress progress) {
        // TEMP - simple transition check for completion in current task
        if (progress.getCurrentState().isComplete(progress)) {
            return true;
        }

        // TODO - Implement predicates / functions
//        return completitionFun.test(progress);
        return false;
    }
}
