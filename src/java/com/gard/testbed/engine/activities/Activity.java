package com.gard.testbed.engine.activities;

import com.gard.testbed.abstractions.IStateProgress;
import com.gard.testbed.abstractions.ITask;

import java.util.*;

/**
 * Created by Chris on 23/02/2016..
 */
public class Activity implements ITask {

    private final String name;
    private final String instructions;
    private List<ITask> taskList = new ArrayList<>();

    public Activity(String name, String instructions) {
        this.name = name;
        this.instructions = instructions;
    }

    public void addTask(ITask task) {
        taskList.add(task);
    }

    @Override
    public ITask getTask(String taskName) {
        for (ITask task : taskList) {
            ITask result;
            result = task.getTask(taskName);
            if (result != null) {
                return result;
            }
        }
        return null;
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
    public boolean isComposite() {
        return true;
    }

    @Override
    public void complete() {

    }

    @Override
    public boolean isComplete(IStateProgress progress) {
        return false;
    }


    // Add task to root activity
    public void addTask(String newTaskName, String newTaskInstructions) {
        ITask task = new Task(newTaskName, newTaskInstructions);
        taskList.add(task);
    }

    // Add task to named task - if named task is not Composite already1, convert to CompositeTask then add new task
    public void addTaskTo(String addToTask, String newTaskName, String newTaskInstructions) {
        CompositeTask parentTask;
        ITask testTask = getTask(addToTask);
        if (!testTask.isComposite()) {
            parentTask = new CompositeTask(testTask.getName(), testTask.getInstructions());
            taskList.set(taskList.indexOf(testTask), parentTask);                               //TODO - solve change to composite in sub-tasks
        } else {
            System.out.println(">> IN ADD TO TASK - got: " + testTask.getName());
            parentTask = (CompositeTask) getTask(addToTask);
            System.out.println(">> IN ADD TO TASK - got: " + parentTask.getName());
        }

        parentTask.addTask(newTaskName, newTaskInstructions);
    }



}
