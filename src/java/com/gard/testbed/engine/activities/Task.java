package com.gard.testbed.engine.activities;

import com.gard.testbed.abstractions.ICompletable;
import com.gard.testbed.abstractions.IStateProgress;
import com.gard.testbed.abstractions.ITask;

/**
 * Created by Chris on 23/02/2016..
 */
public class Task implements ITask, ICompletable {

    private final String name;
    private final String instructions;
    private boolean complete = false;

    public Task(String name, String instructions) {
        this.name = name;
        this.instructions = instructions;
    }


    @Override
    public ITask getTask(String taskName) {
        if (this.name.equals(taskName)) {
            return this;
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
        return false;
    }

    @Override
    public void complete() {
        complete = true;
    }

    @Override
    public boolean isComplete(IStateProgress progress) {
        return complete;
    }
}
