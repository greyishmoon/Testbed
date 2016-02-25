package com.gard.testbed.engine.fsm;

import com.gard.testbed.abstractions.IState;
import com.gard.testbed.abstractions.IStateProgress;
import com.gard.testbed.abstractions.ITransition;
import com.gard.testbed.ui.UiMessage;

import java.util.ArrayList;
import java.util.function.Function;

import java.util.List;

/**
 * Created by Chris on 23/02/2016..
 * A single activity state - models finest level of task granularity or 'operation' (action not breakable down to smaller actions)
 * Holds list of transitions to manage state changes
 */
public class State implements IState {

    // Data associated with state (Name, instructions etc)
    private Property property;
    // List of all possible transitions out of this state
    private List<ITransition> outgoingTransitions = new ArrayList<>();
    // Function run on entering state
//    private final Function<IStateProgress, Integer> onEnterFunction;

    // TEMP - bool to indicate activity complete
    // TODO - refactor / remove
    private boolean complete = false;

    // TODO - restore parameters... 'Function<IStateProgress, Integer> onEnterFunction' and possibly 'List<ITransition> outgoingTransitions'
    public State(Property property) {
        this.property = property;
//        this.outgoingTransitions = outgoingTransitions;           // Cant add on State creation as needs States predefined in Transitions
//        this.onEnterFunction = onEnterFunction;
    }

    public String getName() {
        return property.getName();
    }

    public String getInstructions() {
        return property.getInstructions();
    }

    public void addTransition(ITransition transition) {
        outgoingTransitions.add(transition);
    }


    @Override
    public void onEnter(IStateProgress progress) {
        // TODO - implement onEnterFunction call
    }

    public void completeState() {
        complete = true;
        UiMessage.Print("Completed: " + getName());
    }

    @Override
    public boolean isComplete(IStateProgress progress) {
        return complete;
    }

    // Checks all transitions changes state if conditions fulfilled
    // TEMP - for now just simple check on state 'completed' status
    public void checkTransitions(IStateProgress progress){

        for (ITransition transition : outgoingTransitions) {
            if (transition.isComplete(progress)) {
                UiMessage.Print("TRANSITION TRIGGERED: from " + transition.source().getName() + " to " + transition.destination().getName());
//                System.out.println("TRANSITION TRIGGERED: from " + transition.source().getName() + " to " + transition.destination().getName());
                progress.setCurrentState(transition.destination());
            }
        }
    }
}
