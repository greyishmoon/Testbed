package com.gard.testbed.abstractions;

import java.util.Map;

/**
 * Created by Chris on 18/02/2016..
 */

public interface TaskEntity{

    enum CompletedStatus{
        MARKEDCOMPLETED, ALREADYCOMPLETED, OUSTANDINGSUBTASKS, NOTASK
    }

    TaskEntity addTask(String name, String instructions);
    String getName();
    String getInstructions();
    int getTaskLevel();
    boolean isCompleted();
    Map<String, TaskEntity> getTaskList();
    TaskEntity getParentTask();
    // Attempts to complete this task
    // If no children -> MARKEDCOMPLETED
    // If children and non outstanding -> MARKEDCOMPLETED
    // If children with outstanding -> OUSTANDINGSUBTASKS
    CompletedStatus complete(String taskName);
    // Checks if task is child of this task (reflects through all sub-task levels)
    boolean hasTask(String taskName);
    TaskEntity getTask(String taskName);
    Map<String, TaskEntity> getOutstandingTasks();
    void print(String buffer, String format);            // Prints task and all sub-tasks formatted for console

}
