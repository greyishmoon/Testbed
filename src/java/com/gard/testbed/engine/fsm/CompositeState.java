package com.gard.testbed.engine.fsm;

import com.gard.testbed.abstractions.IState;
import com.gard.testbed.abstractions.IStateProgress;
import com.gard.testbed.abstractions.ITransition;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Created by Chris on 23/02/2016..
 * A state (a task) which has nested states to model sub-tasks
 * Holds list of transitions to manage state changes
 */

// TODO **** INCOMPLETE ****

// TODO >>> CLASS NOT USED IN INITIAL FSM IMPLEMENTATION - EITHER UPDATE WITH ALL State FUNCTIONALITY,
// TODO... CONSIDER INHERITANCE (Composition pattern) OR MAKE SINGLE State CLASS ABLE TO HOLD CHILDREN
public class CompositeState implements IState{

    // List of nested states (sub-tasks)
    private final List<IState> children;
    // Data associated with state (Name, instructions etc)
    private Property property;
    // List of all possible transitions out of this state


    // TEMP - bool to indicate activity complete
    // TODO - refactor / remove
    private boolean complete = false;

    public CompositeState(ArrayList<IState> children) {

        this.children = children;
    }

    public String getName() {
        return property.getName();
    }

    public String getInstructions() {
        return property.getInstructions();
    }

    @Override
    public void completeState() {

    }

    // Processed on entering state
    @Override
    public void onEnter(IStateProgress progress) {

    }

    @Override
    public void checkTransitions(IStateProgress progress) {

    }

    @Override
    public void addTransition(ITransition transition) {

    }

    // Marks current state complete TODO - possible refactor / remove
    public void complete() {
        // Currently marks CompsiteTask complete even if children are not
        // TODO - change to EITHER check children and only marl complete if all children completed
        // TODO... OR iterate over children and mark all complete automatically then complete this
        complete = true;
    }

    // checks if this is complete - if not checks children for completion...
    // IF all children complete then mark this completed
    @Override
    public boolean isComplete(IStateProgress progress) {
        for (IState state : children) {
            if (!state.isComplete(progress)) {
                return false;
            }
        }
        return true;
    }
}
