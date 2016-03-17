package com.gard.testbed.activitySystem;

/**
 * Created by va922kg on 3/15/16.
 *
 * Defines context and connections for all entities comprising the Activity System
 * ?? Named ActivityExecution in Activity System diagram ??
 */
public class ActivitySystem {

    // Activity runner - executes activity, updates blackboard and monitors blackboard
    ActivityRunner activityRunner = null;
    // Blackboard for player progress
    IBlackboard player = null;
    // Activity map
    IActivity activity = null;


    public void initialise(){
        player = new Subject("Player 1");
        activity = ActivityBuilder.generateLinearTestActivity();
        activityRunner = new ActivityRunner();
        player.register(activityRunner);
    }


}
