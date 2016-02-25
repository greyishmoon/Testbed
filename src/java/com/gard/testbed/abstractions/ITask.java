package com.gard.testbed.abstractions;

/**
 * Created by Chris on 23/02/2016..
 */
public interface ITask extends ICompletable {

    ITask getTask(String taskName);
    String getName();
    String getInstructions();
    boolean isComposite();
    void complete();

}
