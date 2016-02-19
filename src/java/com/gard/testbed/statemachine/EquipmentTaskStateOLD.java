package com.gard.testbed.statemachine;

import com.gard.testbed.abstractions.TaskEntity;
import com.gard.testbed.engine.ActivityManager;

/**
 * Created by Chris on 14/02/2016..
 */

public class EquipmentTaskStateOLD implements TaskState {

    // Store data object for this task
    private TaskEntity taskData;

    public EquipmentTaskStateOLD(ActivityManager activityManager, ActivityContext context) {

        // hard code link to tasks data object
        taskData = activityManager.getActivity().getTask("Collect Equipment");
        System.out.println("(STATE CHANGE to: " + taskData.getName() + ")");
        // completed check to immediately swap back to other state if this one is already completed
        checkCompleted(context);
    }

    public TaskEntity getTaskData(){
        return taskData;
    }

    @Override
    public void complete(ActivityContext context, String taskEntityName) {

        // Check for task completion (guard)
        checkCompleted(context);


        // Try to complete sub-task in current task
        TaskEntity.CompletedStatus result =  taskData.complete(taskEntityName);

        switch (result) {
            case MARKEDCOMPLETED:
                System.out.println("Action: " + taskEntityName + " marked collected");
                break;
            case ALREADYCOMPLETED:
                System.out.println("Notification: " + taskEntityName + " already collected, we don't need any more");
                break;
            case NOTASK:
                // For now - assume sub-task called for is in other activity, so swap state
                // Add event to context event stack (temp fix to solve loss of 'complete' message on state change - fixed by proper event stack)
                context.pushEventStack(taskEntityName);
                stateSwap(context);     // STATE CHANGE to another task
                break;
        }

        // Check for task completion (guard)
        checkCompleted(context);
    }

    // Check for overall task completion
    // If this task complete notify and check other activities
    // If other activities outstanding, swap to that state
    // If no activities outstanding - activity complete
    private void checkCompleted(ActivityContext context) {
        if (taskData.isCompleted()) {
            System.out.println("Notification: We have collected all the equipment");
            // Clear eventStack in context
            context.popEventStack();
            // Check other tasks in activity ** TODO needs refining to just check other tasks at same level as this one
            if (context.getActivityManager().getActivity().isCompleted()) {
                // ACTIVITY COMPLETED!! notify and exit run
                System.out.println("\nACTIVITY COMPLETE - we've finished everything");
                System.exit(1);
            } else {
                // If this task completed but activity has outstanding tasks
                stateSwap(context);     // STATE CHANGE to another task
            }
        }
    }

    // HARD CODED state swap to equipment for now
    private void stateSwap(ActivityContext context){
        context.setState(new IngredientsTaskState(context.getActivityManager(), context));
    }

}