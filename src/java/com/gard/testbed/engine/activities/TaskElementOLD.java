package com.gard.testbed.engine.activities;

import com.gard.testbed.abstractions.TaskEntity;

import java.util.Map;
import java.util.LinkedHashMap;

/**
 * Created by Chris on 18/02/2016..
 */
public class TaskElementOLD implements TaskEntity {

    // This task fields
    private String name;
    private String instructions = "";
    private int	taskLevel;
    private boolean completed = false;
    // Indicates if all previous tasks AT THIS LEVEL need to be completed before entering this task // TODO - Remove if not used
    private boolean taskProgressionGate = true;
    // Task tree fields
    private ActivityOLD rootActivity;                          // Access to root of task tree (level 0)
    private Map<String, TaskEntity> taskList = new LinkedHashMap<>();       // Holds nested sub-tasks
    private boolean hasSubTasks = false;                    // Set to true as soon as a task added to taskList
    private TaskEntity parentTask;                          // Holds reference to parent of this task


    public TaskElementOLD(ActivityOLD activity, String name, String instructions, TaskEntity parentTask) {
        this.rootActivity = activity;
        this.name = name;
        this.instructions = instructions;
        this.parentTask = parentTask;
        taskLevel = parentTask.getTaskLevel() + 1;          // Sets task level to one higher than parent
    }

    @Override
    public TaskEntity addTask(String name, String instructions) {
        hasSubTasks = true;
        TaskEntity newTask = new TaskElementOLD(rootActivity, name, instructions, this);
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
        return taskLevel;
    }


    @Override
    public Map<String, TaskEntity> getTaskList() {
        return taskList;
    }

    @Override
    public TaskEntity getParentTask() {
        return parentTask;
    }

    // Attempts to complete this task
    // If no children -> MARKEDCOMPLETED or ALREADYCOMPLETED
    // If children , get task and try to complete
    @Override
    public CompletedStatus complete(String taskName) {
        if (name.equals(taskName)) {
            if (isCompleted()) {
                return TaskEntity.CompletedStatus.ALREADYCOMPLETED;
            }
            if (!hasSubTasks) {
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

    // Checks sub-tasks for completed and automatically completes this task if all children completed
    @Override
    public boolean isCompleted() {
        if (hasSubTasks) {
            if (getOutstandingTasks().isEmpty()) {
                completed = true;
            }
        }
        return completed;
    }

    // Checks if task is child of this task (reflects through all sub-task levels)
    @Override
    public boolean hasTask(String taskName) {
        // Use getTask for iteration code
        return getTask(taskName) != null;
    }

    // Iterate over all tasks and sub-tasks and return TaskEntity whose name matches
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

    @Override
    public void print(String buffer, String format) {
        // Print task
        System.out.printf(format, buffer + name, completed);
        // Print any sub-tasks
        if (taskList.size()>0){
            for (Map.Entry<String, TaskEntity> entry: taskList.entrySet()) {
                entry.getValue().print(buffer + "   ", format);
            }
        }
    }
}
