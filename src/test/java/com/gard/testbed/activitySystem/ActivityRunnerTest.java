package com.gard.testbed.activitySystem;

import com.gard.testbed.engine.eventBus.EventBusFactory;
import com.gard.testbed.engine.eventBus.events.ActivityEvent;
import com.gard.testbed.engine.eventBus.events.MessageType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Chris on 25/03/2016..
 */
public class ActivityRunnerTest {

    /** NOTE - RUN TESTS SEPARATELY - problems with multiple subscriptions to event bus if event bus not GC'd
     *
     */

    private ActivityRunner runner;
    private IActivityMap map;
    private IBlackboard player;
    private IActivity activity1;
    private IActivity activity2;
    private EventBusFactory eventBusFactory;
    /**
     * Sets up the test fixture.
     * (Called before every test case method.)
     */
    @Before
    public void setUp() {

        // **** For some reason using setUp to initialise registers ActivityRunner twice with EventBus!!! *****

//        eventBusFactory = new EventBusFactory();

//        runner = new ActivityRunner();
//        map = new ActivityMap("Activity map").setInitialActivity("Activity 1");
//        player = new Subject("player");
//
//        activity1 = ActivityBuilder.activity("Activity 1", 1).
//                addState("Task 1").
//                setInitialState("Task 1").
//                addTransition("Task 1", "Task 2", (b -> b.query("Task 1") >= 1)).         // 1 = task completed
//                addState("Task 2").
//                addTransition("Task 2", "Task 3", (b -> b.query("Task 2") >= 1)).
//                addState("Task 3").
//                addTransition("Task 3", "Task 2", (b -> b.query("Task 2") >= 1))
//                .build();
//        map.addActivity(activity1);
//
//        activity2 = ActivityBuilder.activity("Activity 2", 1).
//                addState("Task 1").
//                setInitialState("Task 1").
//                addTransition("Task 1", "Task 2", (b -> b.query("Task 1") >= 1)).         // 1 = task completed
//                addState("Task 2").
//                addTransition("Task 2", "Task 3", (b -> b.query("Task 2") >= 1)).build();
//        map.addActivity(activity2);
//
//        runner.setPlayer(player);
//        runner.setActivityMap(map);
    }

    /**
     * Tears down the test fixture.
     * (Called after every test case method.)
     */
    @After
    public void tearDown() {
        ActivityRunner runner = null;
        IActivityMap map = null;
        IBlackboard player = null;
        IActivity activity1 = null;
        IActivity activity2 = null;
    }

    @Test
    public void getCurrentActivity() throws Exception {

        runner = new ActivityRunner();
        map = new ActivityMap("Activity map").setInitialActivity("Activity 1");
        player = new Subject("player");

        activity1 = ActivityBuilder.activity("Activity 1", 1).
                addState("Task 1").
                setInitialState("Task 1").
                build();
        map.addActivity(activity1);

        runner.setPlayer(player);
        runner.setActivityMap(map);

        assertEquals("Wrong current activity", activity1, runner.getCurrentActivity());
    }

    @Test
    public void getCurrentState() throws Exception {
        runner = new ActivityRunner();
        map = new ActivityMap("Activity map").setInitialActivity("Activity 1");
        player = new Subject("player");

        activity1 = ActivityBuilder.activity("Activity 1", 1).
                addState("Task 1").
                setInitialState("Task 1").
                build();
        map.addActivity(activity1);

        runner.setPlayer(player);
        runner.setActivityMap(map);

        assertEquals("Wrong current state", "Task 1", runner.getCurrentState().getId());
    }

    @Test
    public void singleActivity() throws Exception {

        runner = new ActivityRunner();
        map = new ActivityMap("Activity map").setInitialActivity("Activity 1");
        player = new Subject("player");

        activity1 = ActivityBuilder.activity("Activity 1", 1).
                addState("Task 1").
                setInitialState("Task 1").
                addTransition("Task 1", "Task 2", (b -> b.query("Task 1") >= 1)).         // 1 = task completed
                addState("Task 2").
                addTransition("Task 2", "Task 3", (b -> b.query("Task 2") >= 1)).
                addState("Task 3")
                .build();
        map.addActivity(activity1);

        runner.setPlayer(player);
        runner.setActivityMap(map);

        assertEquals("Wrong current activity", activity1, runner.getCurrentActivity());
        assertEquals("Wrong current state", "Task 1", runner.getCurrentState().getId());
        System.out.println(runner.getCurrentState().getId());

        EventBusFactory.getActivityBus().post(new ActivityEvent("Task 1", MessageType.COMPLETETASK, "Update fact on BB"));
        assertEquals("Wrong current state", "Task 2", runner.getCurrentState().getId());
        System.out.println(runner.getCurrentState().getId());

        EventBusFactory.getActivityBus().post(new ActivityEvent("Task 2", MessageType.COMPLETETASK, "Update fact on BB"));
    }

    @Test
    public void nestedActivity() throws Exception {

        runner = new ActivityRunner();
        map = new ActivityMap("Activity map").setInitialActivity("Activity 1");
        player = new Subject("player");

        activity1 = ActivityBuilder.activity("Activity 1", 1).
                addState("Task 1").
                setInitialState("Task 1").
                addTransition("Task 1", "Task 2 has ST", (b -> b.query("Task 1") >= 1)).         // 1 = task completed
                addState("Task 2 has ST").
                setSubTaskActivityTrue("Task 2 has ST").
                addTransition("Task 2 has ST", "Task 3", (b -> b.query("Task 2 has ST") >= 1)).
                addState("Task 3")
                .build();
        map.addActivity(activity1);

        activity2 = ActivityBuilder.activity("Task 2 has ST", 1).
                addState("Task 2.1").
                setInitialState("Task 2.1").
                addTransition("Task 2.1", "Task 2.2", (b -> b.query("Task 2.1") >= 1)).         // 1 = task completed
                addState("Task 2.2").
                addTransition("Task 2.2", "Task 2.3", (b -> b.query("Task 2.2") >= 1)).
                addState("Task 2.3").
                build();
        map.addActivity(activity2);

        runner.setPlayer(player);
        runner.setActivityMap(map);

        assertEquals("Wrong current activity", activity1, runner.getCurrentActivity());
        assertEquals("Wrong current state", "Task 1", runner.getCurrentState().getId());
        System.out.println(runner.getCurrentState().getId());

        assertFalse("Shouldnt have subtasks", runner.getCurrentState().hasSubTaskActivity());

        EventBusFactory.getActivityBus().post(new ActivityEvent("Task 1", MessageType.COMPLETETASK, "Update fact on BB"));
        assertEquals("Wrong current state", "Task 2 has ST", runner.getCurrentState().getId());
        System.out.println(runner.getCurrentState().getId());

        assertTrue("Should have subtasks", runner.getCurrentState().hasSubTaskActivity());

        EventBusFactory.getActivityBus().post(new ActivityEvent("Task 2 has ST", MessageType.LOADSUBTASK, "Request load sub-task activity"));
        assertEquals("Wrong current state", "Task 2.1", runner.getCurrentState().getId());
        System.out.println(runner.getCurrentState().getId());

        EventBusFactory.getActivityBus().post(new ActivityEvent("Task 2.1", MessageType.COMPLETETASK, "Update fact on BB"));
        assertEquals("Wrong current state", "Task 2.2", runner.getCurrentState().getId());
        System.out.println(runner.getCurrentState().getId());

        EventBusFactory.getActivityBus().post(new ActivityEvent("Task 2.2", MessageType.COMPLETETASK, "Update fact on BB"));
        assertEquals("Wrong current state", "Task 3", runner.getCurrentState().getId());
        System.out.println(runner.getCurrentState().getId());
    }


}