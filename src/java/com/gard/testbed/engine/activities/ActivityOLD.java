package com.gard.testbed.engine.activities;

import com.gard.testbed.abstractions.TaskEntity;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Chris on 18/02/2016..
 */
public class ActivityOLD implements TaskEntity {

    private String name;
    private String instructions = "";
    private int	taskLevel = 0;                  // ActivityOLD is root level 0
    private boolean completed = false;
    private Map<String, TaskEntity> taskList = new LinkedHashMap<>();       // Holds nested sub-tasks

    public ActivityOLD(String name, String instructions) {
        this.name = name;
        this.instructions = instructions;
    }

    @Override
    public TaskEntity addTask(String name, String instructions) {
        TaskEntity newTask = new TaskElementOLD(this, name, instructions, this);
        taskList.put(name, newTask);
        return newTask;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getInstructions() {
        return instructions;
    }

    @Override
    public int getTaskLevel() {
        return 0;
    }

    // Checks tasks for completed and automatically completes this task if all children completed
    @Override
    public boolean isCompleted() {
        if (getOutstandingTasks().isEmpty()) {
            completed = true;
        }
        return completed;
    }

    @Override
    public Map<String, TaskEntity> getTaskList() {
        return taskList;
    }

    @Override
    public TaskEntity getParentTask() {
        return null;
    }

    @Override
    public CompletedStatus complete(String taskName) {
        if (name.equals(taskName)) {
            if (isCompleted()) {
                return TaskEntity.CompletedStatus.ALREADYCOMPLETED;
            }
            if (getOutstandingTasks().isEmpty()) {
                completed = true;
                return TaskEntity.CompletedStatus.MARKEDCOMPLETED;
            } else {
                return TaskEntity.CompletedStatus.OUSTANDINGSUBTASKS;
            }
        } else {
            TaskEntity taskFound;
            if ((taskFound = getTask(taskName)) != null) {
                return taskFound.complete(taskName);
            }
        }
        return TaskEntity.CompletedStatus.NOTASK;
    }

    @Override
    public boolean hasTask(String taskName) {
        // Use getTask for iteration code
        return getTask(taskName) != null;
    }

    @Override
    public TaskEntity getTask(String taskName) {
        // If task in top level...
        if (taskList.containsKey(taskName)){
            return taskList.get(taskName);
            // Otherwise iterate over sub-tasks to find task
        }else{
            TaskEntity task;
            for (Map.Entry<String, TaskEntity> entry: taskList.entrySet()) {
                task = entry.getValue().getTask(taskName);
                if (task != null) {
                    return task;
                }
            }
        }
        // If not present in activity return null
        return null;
    }

    @Override
    public Map<String, TaskEntity> getOutstandingTasks() {
        Map<String, TaskEntity> outstandingTasks = new LinkedHashMap<>();
        for (Map.Entry<String, TaskEntity> entry: taskList.entrySet()) {
            if (!entry.getValue().isCompleted()) {
                outstandingTasks.put(entry.getKey(), entry.getValue());
            }
        }
        return outstandingTasks;
    }

    // Set formatting for console print()
    public void print() {
        // Formatting for tabbing 'complete' result - 40 chars
        String format = "%-40s%s%n";
        String buffer = "   ";
        print(buffer, format);
    }

    // Print activity and all sub-tasks
    public void print(String buffer, String format){
        System.out.printf(format, "ActivityOLD: " + name, "Completed:");

        // Print all tasks and sub-tasks
        for (Map.Entry<String, TaskEntity> entry: taskList.entrySet()) {
            entry.getValue().print(buffer, format);
        }
    }
}
