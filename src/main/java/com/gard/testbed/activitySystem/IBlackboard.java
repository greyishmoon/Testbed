package com.gard.testbed.activitySystem;

/**
 * Created by va922kg on 3/15/16.
 */
public interface IBlackboard {
    int query(String key);

    void set(String key, int value);
}
