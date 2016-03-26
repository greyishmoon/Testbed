package com.gard.testbed.activitySystem;


import com.gard.testbed.engine.eventBus.EventBusFactory;
import com.gard.testbed.engine.eventBus.events.*;
import com.google.common.eventbus.Subscribe;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * Manages execution of activities
 */

public class ActivityRunner {

    // Blackboard for current player progress
    private IBlackboard player = null;
    // Activity map for current activity
    private IActivityMap activityMap = null;
    // Records current activity player is progressing through
    private IActivity currentActivity = null;
    // Records current state representing task player is currently engaged in
    private IState currentState = null;
    // Stack of suspended activities - stores suspended parent activities when nested activity is loaded
    private Stack<IActivity> suspendedActivityStack = new Stack<>();
    // Stack of suspended current states - records progression state of suspended activity
    // when activity resumes top of states stack become current state, but is then checked for progression (using update()
    // as it SHOULD fire immediately as its nested activity has just been completed - not guaranteed though
    private Stack<IState> suspendedStateStack = new Stack<>();


    public ActivityRunner() {
        // register ActivityRunner on Activity Message Bus
        EventBusFactory.getActivityBus().register(this);
    }

    // Handle Blackboard events on Activity Bus
    @Subscribe
    public void eventHandler(BlackboardEvent event) {

        switch (event.getType()) {
            // Blackboard has been updated - initiate activity progress check
            case UPDATED:
                System.out.println("Update triggered");
                update();
                break;

            default:
                break;
        }
    }

    // Handle Activity events on Activity Bus
    @Subscribe
    public void eventHandler(ActivityEvent event) {

        switch (event.getType()) {
            // Add new property to blackboard
            case COMPLETETASK:
                System.out.println("Runner revceived COMPLETETASK event");
                player.set(event.getTarget(), 1);
                break;
            // TODO - add INCREMENT case to handle properties that have values higher than 1 (true) - e.g. number of eggs
            // Load sub-task activity of current state IF there is one present
            case LOADSUBTASK:
                System.out.println("Runner revceived LOADSUBTASK event");
                loadActivity(event.getTarget());
                break;

            default:
                break;
        }
    }

    // Add main activity map and set up initial activity / state
    public void setActivityMap(IActivityMap map) {
        this.activityMap = map;
        // Initialise
        currentActivity = map.getInitialActivity();
        currentState = currentActivity.getInitialState();
    }

    // Load activity to current activity and set initial state
    private void loadActivity(String name) {
        if (currentState.hasSubTaskActivity()) {
            // push current progression to stacks (currentActivity and currentState) and set new current
            suspendedActivityStack.push(currentActivity);
            suspendedStateStack.push(currentState);
            IActivity loadActivity = activityMap.getActivity(name);
            currentActivity = loadActivity;
            currentState = loadActivity.getInitialState();
        }
    }

    // Unload currentActivity and retrieve suspended activity from stack - returns false if no suspended activities
    private boolean retrieveActivity() {
        if (!suspendedActivityStack.isEmpty()) {
            // replace currentActivity with parent activity stored on stack and reload parent activity's stored progression state into currentState
            currentActivity = suspendedActivityStack.pop();
            currentState = suspendedStateStack.pop();
            return true;
        }
        return false;
    }

    // Checks activity progress and handles state changes required
    private void update() {

        // Check on transitions of currentState
        // NOTE - current implementation assumes only one will be able to fire if more than one.
        // If this changes to multiple possible live transitions then refactor
        List<ITransition> liveTransitions = currentState.getTransitions().stream().filter(t -> t.isComplete(player))
                .collect(Collectors.toList());

        // If transitions in list fire first one
        if (!liveTransitions.isEmpty()) {
            ITransition fireTransition = liveTransitions.get(0);
            // Trigger all actions in order
            currentState.onExit();
            fireTransition.onFire();
            // Set new current state
            currentState = currentActivity.get(fireTransition.Destination());
            // Trigger new state actions
            currentState.onEnter();
            liveTransitions.clear();
        } else {
            // No live transitions so no action required
            return;
        }

        // Check if new current state is end of current activity
        if (currentState.getTransitions().isEmpty()) {
            // End of activity
            // Automatically fire onExit of final task (a placeholder task for activity end point)
            currentState.onExit();

            // TODO - ??? use this spot to update blackboard with current activity completed ???
            EventBusFactory.getActivityBus().post(new ActivityEvent(currentActivity.getId(), MessageType.COMPLETETASK, "Mark this activity completed on blackboard"));
            // TODO - remove println
            System.out.println("Log: Activity " + currentActivity.getId() + " completed - try to load parent...");

            // Try to retrieve suspended parent activity - if present reload it / if not ActivityMap completed
            if (!retrieveActivity()) {
                // TODO - handle end of activity map
                // TODO - remove println
                System.out.println("Log: ActivityMap " + activityMap.getId() + " completed");
                return;
            }
        }

        // run update on new current state to ensure no further progression
        // Note - if activity just completed and swapped out, then then restored current state SHOULD fire
        // as its child activity has just been completed - not guaranteed though
        update();

    }

    public void setPlayer(IBlackboard player) {
        this.player = player;
    }

    public IActivity getCurrentActivity() {
        return currentActivity;
    }

    public IState getCurrentState() {
        return currentState;
    }

}
