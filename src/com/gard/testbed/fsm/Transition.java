package com.gard.testbed.fsm;

import java.util.function.Predicate;

/**
 * Created by va922kg on 2/18/16.
 */
public class Transition implements ITransition {
    private final IState source;
    private final IState destination;
    private final Predicate<IStateProgress> completitionFun;

    Transition(IState source, IState destination, Predicate<IStateProgress> completitionFun){
        this.source = source;
        this.destination = destination;
        this.completitionFun =completitionFun;
    }

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
        return completitionFun.test(progress);
    }
}
