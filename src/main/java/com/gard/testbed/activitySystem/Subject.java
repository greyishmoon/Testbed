package com.gard.testbed.activitySystem;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by va922kg on 3/15/16.
 */
public class Subject implements IBlackboard {
    private Map<String,Integer> properties;

    public Subject(String id) {
        properties= new HashMap<>();
    }

    @Override
    public int query(String key) {
        if(!properties.containsKey(key))
            return -1;
        return properties.get(key);
    }

    @Override
    public void set(String key, int value) {
        properties.put(key,value);
    }
}
