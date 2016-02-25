package com.gard.testbed.engine.fsm;

import com.gard.testbed.abstractions.IState;
import com.gard.testbed.abstractions.IUpdatable;

/**
 * Created by Chris on 24/02/2016..
 * Holds current activity states and manages transition checking / state changes
 */
public class ActivityStateMachine implements IUpdatable{

    // Singleton
    private static ActivityStateMachine ourInstance = new ActivityStateMachine();
    public static ActivityStateMachine getInstance() {
        return ourInstance;
    }

    // FSM components
    private Activity activity;
    private Progress progress;


    private ActivityStateMachine() {
    }

    public void loadActivity(Activity activity) {
        this.activity = activity;
        progress = new Progress(activity.getStartState());
    }

    public void completeState(String stateName) {
        activity.getState(stateName).completeState();
    }

    public IState getCurrentState() {
        return progress.getCurrentState();
    }

    // Update state machine through progress checking current state status
    @Override
    public void update() {
        progress.update();
    }
}
