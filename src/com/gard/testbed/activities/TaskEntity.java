package com.gard.testbed.activities;

/**
 * Created by Chris on 14/02/2016.
 */
public interface TaskEntity {
    public enum CompletedStatus{
        MARKEDCOMPLETED, ALREADYCOMPLETED, OUSTANDINGSUBTASKS, NOTASK
    }

    public TaskEntity addTask(String name);
    public void addItem(String name);
    public String getName();
    public boolean hasTask(String taskName);                    // Finds if this (and only this) task has matching sub-task/item in its task list
    public TaskEntity getTask(String taskName);                 // Returns sub-task/item from this objects task list (if present)
    public TaskEntity getTaskEntity(String taskName);           // Iterate over all tasks / sub-tasks and items to return the object with matching key
    public CompletedStatus complete(String taskName);           // Mark task/item as completed
    public boolean isComplete();
    public void print(String buffer, String format);            // Prints task and all sub-tasks formatted for console

}
