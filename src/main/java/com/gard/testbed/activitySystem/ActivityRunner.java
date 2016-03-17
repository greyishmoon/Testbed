package com.gard.testbed.activitySystem;

/**
 * Created by Chris on 16/03/2016..
 */
public class ActivityRunner implements IObserver {

    // Blackboard for current player progress
    IBlackboard player = null;
    // Activity map for current activity
    IActivity activity = null;
    // Subscription to Subjects blackboard
    private IObservable subject = null;

    // TODO - list of active States
    // ?? Maintain a list here or just rely on method (getActiveStates(player)) implemented in Activity ??

    public ActivityRunner() {

    }

    // TODO - get start state and put in activeStates

    // temp message method - replace with hook into message bus listening for update events
    public void updateBlackboard(String key, int value) {
        player.set(key, value);
    }

    @Override
    // Change in observed blackboard fires update()
    public void update() {
        System.out.println("BLACKBOARD CHANGED");
        // TODO - update progression state (check transitions for trigger conditions and get new active states list?
    }


    public void setPlayer(IBlackboard player) {
        this.player = player;
    }

    public void setActivity(IActivity activity) {
        this.activity = activity;
    }

    @Override
    public void setSubject(IObservable sub) {
        this.subject = sub;
        System.out.println("Activity runner subscribed to: " + sub.toString());

    }
}
