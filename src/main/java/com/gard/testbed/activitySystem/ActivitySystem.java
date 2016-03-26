package com.gard.testbed.activitySystem;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by va922kg on 3/15/16.
 * <p>
 * Defines context and connections for all entities comprising the Activity System
 * ?? Named ActivityExecution in Activity System diagram ??
 */
public class ActivitySystem {

    // Holds all Activity Maps currently loaded into system
    private Map<String, IActivityMap> activityMapLibrary = new HashMap<String, IActivityMap>();

    public void addActivityMap(IActivityMap newMap) {
        activityMapLibrary.put(newMap.getId(), newMap);
    }

}
