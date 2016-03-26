package com.gard.testbed.activitySystem;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Chris on 25/03/2016..
 */
public class ActivityMapTest {







    @Test
    public void getId() throws Exception {
        IActivityMap map = new ActivityMap("Activity map").setInitialActivity("Activity 1");
        assertEquals("Wrong ID", "Activity map", map.getId());
    }

    @Test
    public void getInitialActivity() throws Exception {
        IActivityMap map = new ActivityMap("Activity map").setInitialActivity("Activity 1");

        IActivity activity1 = ActivityBuilder.activity("Activity 1", 1).
                addState("Task 1").
                setInitialState("Task 1").
                addTransition("Task 1","Task 2",(b -> b.query("Task 1") >= 1)).         // 1 = task completed
                addState("Task 2").
                addTransition("Task 2","Task 3",(b -> b.query("Task 2") >= 1)).build();
        map.addActivity(activity1);

        assertEquals("Wrong InitialActivity", "Activity 1", map.getInitialActivity().getId());
    }

    @Test
    public void getActivityHashMap() throws Exception {

        IActivity activity1 = ActivityBuilder.activity("Activity 1", 1).
                addState("Task 1").
                setInitialState("Task 1").
                addTransition("Task 1","Task 2",(b -> b.query("Task 1") >= 1)).         // 1 = task completed
                addState("Task 2").
                addTransition("Task 2","Task 3",(b -> b.query("Task 2") >= 1)).build();

        IActivity activity2 = ActivityBuilder.activity("Activity 2", 1).
                addState("Task 1").
                setInitialState("Task 1").
                addTransition("Task 1","Task 2",(b -> b.query("Task 1") >= 1)).         // 1 = task completed
                addState("Task 2").
                addTransition("Task 2","Task 3",(b -> b.query("Task 2") >= 1)).build();

        IActivityMap map = new ActivityMap("Activity map").setInitialActivity("Activity 1");

        assertEquals("Wrong map size", 0, map.getActivityHashMap().size());
        map.addActivity(activity1);
        assertEquals("Wrong map size", 1, map.getActivityHashMap().size());
        assertEquals("Incorrect activity", activity1, map.getActivityHashMap().get("Activity 1"));
        map.addActivity(activity2);
        assertEquals("Wrong map size", 2, map.getActivityHashMap().size());
        assertEquals("Incorrect activity", activity2, map.getActivityHashMap().get("Activity 2"));


    }

    @Test
    public void getActivity() throws Exception {
        IActivity activity1 = ActivityBuilder.activity("Activity 1", 1).
                addState("Task 1").
                setInitialState("Task 1").
                addTransition("Task 1","Task 2",(b -> b.query("Task 1") >= 1)).         // 1 = task completed
                addState("Task 2").
                addTransition("Task 2","Task 3",(b -> b.query("Task 2") >= 1)).build();

        IActivity activity2 = ActivityBuilder.activity("Activity 2", 1).
                addState("Task 1").
                setInitialState("Task 1").
                addTransition("Task 1","Task 2",(b -> b.query("Task 1") >= 1)).         // 1 = task completed
                addState("Task 2").
                addTransition("Task 2","Task 3",(b -> b.query("Task 2") >= 1)).build();

        IActivityMap map = new ActivityMap("Activity map").setInitialActivity("Activity 1");

        assertEquals("Wrong map size", 0, map.getActivityHashMap().size());
        map.addActivity(activity1);
        assertEquals("Wrong map size", 1, map.getActivityHashMap().size());
        assertEquals("Incorrect activity", activity1, map.getActivity("Activity 1"));
        assertEquals("Incorrect activity", null, map.getActivity("Activity 2"));
        map.addActivity(activity2);
        assertEquals("Incorrect activity", activity2, map.getActivity("Activity 2"));
    }
}