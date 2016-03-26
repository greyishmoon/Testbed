package com.gard.testbed.activitySystem;

import java.util.HashMap;
import java.util.StringJoiner;

/**
 * Top level activity container
 * Holds all activities / sub-task activities for complete user activity
 * e.g. all activity data for 'Cook Omelette' activity
 */
public interface IActivityMap {

    // Adds activity to map
    void addActivity(IActivity activity);

    // Get name of activity
    String getId();

    // Set initial activity for this activity map
    IActivityMap setInitialActivity(String initialActivityId);

    // Get initial activity
    IActivity getInitialActivity();

    // Returns whole HashMap of activities
    HashMap<String, IActivity> getActivityHashMap();

    // Gets single activity by name
    IActivity getActivity(String activityName);
}
