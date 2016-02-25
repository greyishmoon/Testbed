package com.gard.testbed.engine.fsm;

import com.gard.testbed.abstractions.IState;
import com.gard.testbed.abstractions.ITransition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris on 24/02/2016..
 */
public class ActivityBuilder {

    public static Activity SimpleTestActivity() {

        IState state1 = new State(new Property("Task 1", "Instructions 1"));
        IState state2 = new State(new Property("Task 2", "Instructions 2"));
        IState state3 = new State(new Property("Task 3", "Instructions 3"));
        IState state4 = new State(new Property("Task 4", "Instructions 4"));



        state1.addTransition(new Transition(state1, state2));
        state2.addTransition(new Transition(state2, state3));
        state3.addTransition(new Transition(state3, state4));

        ArrayList<IState> stateList = new ArrayList<>();

        stateList.add(state1);
        stateList.add(state2);
        stateList.add(state3);
        stateList.add(state4);

        return new Activity("Test activity", stateList);
    }


}
