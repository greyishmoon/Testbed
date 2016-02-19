package com.gard.testbed.activities;

/**
 * Created by Chris on 14/02/2016.
 */

public interface TaskEntity {

    enum CompletedStatus{
        MARKEDCOMPLETED, ALREADYCOMPLETED, OUSTANDINGSUBTASKS, NOTASK
    }

    /* TODO : Split the interface - be consistent if addTask returns a TaskEntity why addItem is not?
    * TODO : remove the public - is an interface they are not needed
    * TODO : Rename the interface - why task entity and not Task?
    * */

  //  public TaskEntity addTask(String name);
  void addItem(String name);
    String getName();
    boolean hasTask(String taskName);                    // Finds if this (and only this) task has matching sub-task/item in its task list
    TaskEntity getTask(String taskName);                 // Returns sub-task/item from this objects task list (if present)
    // TODO getTaskEntity doesn't sound good to me
    TaskEntity getTaskEntity(String taskName);           // Iterate over all tasks / sub-tasks and items to return the object with matching key
    CompletedStatus complete(String taskName);           // Mark task/item as completed
    boolean isComplete();

    // TODO this is not the right place UI related code.
    void print(String buffer, String format);            // Prints task and all sub-tasks formatted for console

}
