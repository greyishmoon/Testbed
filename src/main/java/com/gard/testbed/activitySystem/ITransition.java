package com.gard.testbed.activitySystem;

import java.util.function.Predicate;

/**
 * Created by va922kg on 3/15/16.
 */
public interface ITransition {
    String Source();

    boolean isComplete(IBlackboard player);

    Predicate<IBlackboard> getTrigger();

    String Destination();
}
