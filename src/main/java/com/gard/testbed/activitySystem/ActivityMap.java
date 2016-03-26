package com.gard.testbed.activitySystem;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Created by Chris on 24/03/2016..
 */
public class ActivityMap implements IActivityMap {

    // Name of activity
    private String id;
    // List of all activities for this activity map
    private Map<String, IActivity> activities = new HashMap<>();
    // Starting activity for activityMap
    private String initialActivityId;


    public ActivityMap(String id) {
        this.id = id;
    }

    @Override
    public void addActivity(IActivity activity) {
        activities.put(activity.getId(), activity);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public IActivityMap setInitialActivity(String initialActivityId) {
        this.initialActivityId = initialActivityId;
        return this;
    }

    @Override
    public IActivity getInitialActivity() {
        return activities.get(initialActivityId);
    }

    @Override
    public HashMap<String, IActivity> getActivityHashMap() {
        return (HashMap<String, IActivity>)activities;
    }

    @Override
    public IActivity getActivity(String activityName) {
        if (activities.containsKey(activityName)) {
            return activities.get(activityName);
        }
        return null;
    }
}
