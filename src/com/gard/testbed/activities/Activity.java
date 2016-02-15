package com.gard.testbed.activities;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Chris on 14/02/2016.
 */
public class Activity implements TaskEntity{

    private String name;
    boolean completed = false;
    // Map (LinkedHashMap) of sub-tasks <task name, Task>
    Map <String, TaskEntity> taskList = new LinkedHashMap<>();

    public Activity(String name) {
        this.name = name;
    }

    // Add task/item to list with key set as task name
    public TaskEntity addTask(String name){
        TaskEntity newTask = new Task(name);
        taskList.put(name, newTask);
        return newTask;
    }

    @Override
    public void addItem(String name) {                  //TODO Add exception - cant add item directly to activity - must go in a task

    }

    @Override
    public String getName() {
        return name;
    }

    // Finds if this (and only this) task has matching sub-task/item in its task list
    @Override
    public boolean hasTask(String taskName) {
        if (taskList.containsKey(taskName)) {
            return true;
        }
        return false;
    }

    // Returns sub-task/item from this objects task list (if present)
    @Override
    public TaskEntity getTask(String taskName) {
        return taskList.get(taskName);
    }

    // Iterate over all tasks and return TaskEntity whose name matches
    public TaskEntity getTaskEntity (String taskName){
        // If task in top level...
        if (taskList.containsKey(taskName)){
            return taskList.get(taskName);
        // Otherwise iterate over sub-tasks to find task
        }else{
            TaskEntity task = null;
            for (Map.Entry<String, TaskEntity> entry: taskList.entrySet()) {
                task = entry.getValue().getTaskEntity(taskName);
                if (task != null) {
                    return task;
                }
            }
        }
        // If not present in activity return null
        return null;
    }

    // If this activityName, check sub-tasks for completion (then set accordingly)
    // Otherwise pass complete message on to appropriate sub-task if present in taskList
    @Override
    public CompletedStatus complete(String taskName) {
        if (name.equals(taskName)) {
            if (completed) {
                return CompletedStatus.ALREADYCOMPLETED;
            }
            if (checkOutstandingTasks().isEmpty()) {
                completed = true;
                return CompletedStatus.MARKEDCOMPLETED;
            } else {
                return CompletedStatus.OUSTANDINGSUBTASKS;
            }
        } else {
            if (taskList.containsKey(taskName)) {
                return taskList.get(taskName).complete(taskName);
            }
        }
        return CompletedStatus.NOTASK;
    }

    // Forces check on sub-task completion status - this task only marked complete when all sub-tasks complete
    public boolean isComplete() {
        // If all tasks completed set activity to completed
        if (checkOutstandingTasks().isEmpty()) {
            completed = true;
        }
        return completed;
    }

    public Map<String, TaskEntity> checkOutstandingTasks() {
        Map<String, TaskEntity> oustantingTasks = new LinkedHashMap<>();
        for (Map.Entry<String, TaskEntity> entry: taskList.entrySet()) {
            if (!entry.getValue().isComplete()) {
                oustantingTasks.put(entry.getKey(), entry.getValue());
            }
        }
        return oustantingTasks;
    }

    // Print activity tasks and sub-tasks formatted for console
    public void print(){
        // Formatting for tabbing 'complete' result - 40 chars
        String format = "%-40s%s%n";

        System.out.printf(format, "Activity: " + name, "Completed:");

        // Print all tasks and sub-tasks
        for (Map.Entry<String, TaskEntity> entry: taskList.entrySet()) {
            entry.getValue().print("   ", format);
        }

    }

    @Override
    public void print(String buffer, String format) {
        print();
    }


}
