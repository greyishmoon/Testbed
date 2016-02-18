package com.gard.testbed.fsm;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by va922kg on 2/18/16.
 */
public class State implements IState{
    private final List<ITransition> outgoingTransitions;
    private final Function<IStateProgress, Void> onEnterFunction;

    State(Function<IStateProgress, Void> onEnter, List<ITransition> outgoingTransitions) {
        this.outgoingTransitions = outgoingTransitions;
        this.onEnterFunction = onEnter;
    }

    @Override
    public boolean isComplete( IStateProgress progress) {
        for ( ITransition transition : outgoingTransitions) {
            if ( transition.isComplete(progress))
                return true;
        }
        return false;
    }

    @Override
    public void onEnter(IStateProgress progress) {
        this.onEnterFunction.apply(progress);
    }
}
