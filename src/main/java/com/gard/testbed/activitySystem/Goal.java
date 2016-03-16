package com.gard.testbed.activitySystem;

import java.util.function.Predicate;

/**
 * Created by va922kg on 3/15/16.
 */
public class Goal {
    private String desc;
    private Predicate<IBlackboard> completeCond;

    public Goal(String desc, Predicate<IBlackboard> completeCond) {
        this.desc=desc;
        this.completeCond=completeCond;
    }
}
