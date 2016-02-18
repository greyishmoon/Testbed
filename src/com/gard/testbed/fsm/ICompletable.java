package com.gard.testbed.fsm;

/**
 * Created by va922kg on 2/18/16.
 */
public interface ICompletable {
    boolean isComplete(final IStateProgress progress);
}
