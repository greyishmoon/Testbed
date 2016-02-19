package com.gard.testbed.activities;

/**
 * Created by Chris on 15/02/2016.
 */
public class Item implements TaskEntity {

    private final String name;
    private boolean completed = false;

    public Item(String name) {
        this.name = name;
    }

    /*@Override
    public TaskEntity addTask(String name) {
        return null;
    }*/
    //TODO Add exception - cant add task to entity * Do not create method like this.... if you see them- wrong interface

    @Override
    public void addItem(String name) {    }                   //TODO Add exception - cant add item to item

    @Override
    public String getName() {
        return name;
    }

    // As no task list to check returns true if this object matches taskName
    @Override
    public boolean hasTask(String taskName) {
        if (name.equals(taskName)) {
            return true;
        }
        return false;
    }

    // As no task list to check returns this object if taskName matches
    @Override
    public TaskEntity getTask(String taskName) {
        if (name.equals(taskName)) {
            return this;
        }
        return null;
    }

    // As no task list to check returns this object if taskName matches
    //@Override
    // TODO why this method here? I have a method to check the name
    public TaskEntity getTaskEntity(String taskName) {
        if (name.equals(taskName)) {
            return this;
        }
        return null;
    }

    @Override
    public CompletedStatus complete(String taskName) {
        if (name.equals(taskName)) {
            if (completed) {
                return CompletedStatus.ALREADYCOMPLETED;
            } else {
                completed = true;
                return CompletedStatus.MARKEDCOMPLETED;
            }
        } else {
            return CompletedStatus.NOTASK;
        }
    }

    @Override
    public boolean isComplete() {
        return completed;
    }



    @Override
    public void print(String buffer, String format) {
        System.out.printf(format, buffer + name, completed);
    }
}
