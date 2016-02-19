package com.gard.testbed.statemachine;

import com.gard.testbed.engine.ActivityManager;

/**
 * Created by Chris on 14/02/2016..
 */
public class ActivityContext {

    // Reference to controlling ActivityManager (for access to activity data)
    private ActivityManager activityManager;
    // Current state
    private TaskState state;
    // Event stack (temp fix to solve loss of 'complete' message on state change - fixed by proper event stack)
    public String eventStack = "";

    public ActivityContext(ActivityManager activityManager) {
        this.activityManager = activityManager;
    }

    public ActivityManager getActivityManager() {
        return activityManager;
    }

    public void setState(TaskState state){
        this.state = state;
        System.out.println("(STATE CHANGE to: " + state.getTaskData().getName() + ")");
        processEventStack();
    }

    public void pushEventStack(String taskName) {
        eventStack = taskName;
    }

    public String popEventStack() {
        String eventName = eventStack;
        eventStack = "";
        return eventName;
    }

    private void processEventStack() {
        // Process any outstanding events (temp fix to solve loss of 'complete' message on state change - fixed by proper event stack)
        if (!eventStack.isEmpty()) {
//            activityManager.complete(eventStack);

//            // Only process if task is uncompleted
            if (!state.getTaskData().isCompleted()) {
                activityManager.complete(popEventStack());
            }
        }
    }

    public TaskState getState(){
        return state;
    }

    // Action event in current state
    public void complete(String taskEntityName){
        state.complete(this, taskEntityName);
    }
}
