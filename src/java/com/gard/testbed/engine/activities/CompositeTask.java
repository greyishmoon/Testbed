package com.gard.testbed.engine.activities;

import com.gard.testbed.abstractions.ICompletable;
import com.gard.testbed.abstractions.IStateProgress;
import com.gard.testbed.abstractions.ITask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris on 23/02/2016..
 */
public class CompositeTask implements ITask{

    private final String name;
    private final String instructions;
    private boolean complete = false;
    private List<ITask> taskList = new ArrayList<>();

    public CompositeTask(String name, String instructions) {
        this.name = name;
        this.instructions = instructions;
    }

    @Override
    public void complete() {
        for (ITask task : taskList) {
            task.complete();
        }
    }

    // Add sub-task to task
    public void addTask(String newTaskName, String newTaskInstructions) {
        ITask task = new Task(newTaskName, newTaskInstructions);
        taskList.add(task);
    }

    @Override
    public boolean isComplete(IStateProgress progress) {
        return false;
    }

    @Override
    public ITask getTask(String taskName) {
        if (this.name.equals(taskName)) {
            return this;
        }
        for (ITask task : taskList) {
            ITask result = task.getTask(taskName);
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
}
