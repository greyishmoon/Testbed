package com.gard.testbed.activitySystem;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Chris on 17/03/2016..
 */
public class StateTest {

    @Test
    public void testIsCompletedSingleTransition() throws Exception {
        IBlackboard player = new Subject("player");
        IActivity activity = ActivityBuilder.activity("Omelette").
                addState("Task 1").
                setInitialState("Task 1").
                addTransition("Task 1", "Task 2", (b -> b.query("Task 1") >= 1)).         // 1 = task completed
                addState("Task 2").
                addTransition("Task 2", "Task 3", (b -> b.query("Task 2") >= 1)).
                addState("Task 3").
                addTransition("Task 3", "End", (b -> b.query("Task 3") >= 1)).
                addState("End").                                                        // no transitions so isFinal
                build();

        assertFalse("Shouldn't be completed", activity.get("Task 1").isCompleted(player));
        assertFalse("Shouldn't be completed", activity.get("Task 2").isCompleted(player));
        assertFalse("Shouldn't be completed", activity.get("Task 3").isCompleted(player));
        player.set("Task 1", 1);
        assertTrue("Should be completed", activity.get("Task 1").isCompleted(player));
        assertFalse("Shouldn't be completed", activity.get("Task 2").isCompleted(player));
        assertFalse("Shouldn't be completed", activity.get("Task 3").isCompleted(player));
        player.set("Task 2", 1);
        assertTrue("Should be completed", activity.get("Task 1").isCompleted(player));
        assertTrue("Should be completed", activity.get("Task 2").isCompleted(player));
        assertFalse("Shouldn't be completed", activity.get("Task 3").isCompleted(player));
    }

    @Test
    public void testIsCompletedMultipleTransitions() throws Exception {
        IBlackboard player = new Subject("player");
        IActivity activity = ActivityBuilder.activity("Omelette").
                addState("Task 1").
                setInitialState("Task 1").
                // Task 1 has 3 transitions - all must be true for Task 1 to be 'completed'
                        addTransition("Task 1", "Task 2", (b -> b.query("Trans 1") >= 1)).         // 1 = task completed
                addTransition("Task 1", "Task 3", (b -> b.query("Trans 2") >= 1)).
                        addTransition("Task 1", "Task 4", (b -> b.query("Trans 3") >= 1)).
                        addState("Task 2").
                        addState("Task 3").
                        addState("Task 4").
                        addState("End").                                                        // no transitions so isFinal
                build();

        assertFalse("Shouldn't be completed", activity.get("Task 1").isCompleted(player));
        // check each transition specifically
        assertFalse("Shouldn't be completed", activity.get("Task 1").getTransitions().stream().
                filter(e -> e.Destination() == "Task 2").findFirst().get().isComplete(player));
        assertFalse("Shouldn't be completed", activity.get("Task 1").getTransitions().stream().
                filter(e -> e.Destination() == "Task 3").findFirst().get().isComplete(player));
        assertFalse("Shouldn't be completed", activity.get("Task 1").getTransitions().stream().
                filter(e -> e.Destination() == "Task 4").findFirst().get().isComplete(player));

        player.set("Trans 1", 1);
        assertFalse("Shouldn't be completed", activity.get("Task 1").isCompleted(player));
        // check each transition specifically
        assertTrue("Should be completed", activity.get("Task 1").getTransitions().stream().
                filter(e -> e.Destination() == "Task 2").findFirst().get().isComplete(player));
        assertFalse("Shouldn't be completed", activity.get("Task 1").getTransitions().stream().
                filter(e -> e.Destination() == "Task 3").findFirst().get().isComplete(player));
        assertFalse("Shouldn't be completed", activity.get("Task 1").getTransitions().stream().
                filter(e -> e.Destination() == "Task 4").findFirst().get().isComplete(player));

        player.set("Trans 2", 1);
        assertFalse("Shouldn't be completed", activity.get("Task 1").isCompleted(player));
        // check each transition specifically
        assertTrue("Should be completed", activity.get("Task 1").getTransitions().stream().
                filter(e -> e.Destination() == "Task 2").findFirst().get().isComplete(player));
        assertTrue("Should be completed", activity.get("Task 1").getTransitions().stream().
                filter(e -> e.Destination() == "Task 3").findFirst().get().isComplete(player));
        assertFalse("Shouldn't be completed", activity.get("Task 1").getTransitions().stream().
                filter(e -> e.Destination() == "Task 4").findFirst().get().isComplete(player));

        player.set("Trans 3", 1);
        // At this point all outgoing transitions true so Task 1 = completed
        assertTrue("Should be completed", activity.get("Task 1").isCompleted(player));
        // check each transition specifically
        assertTrue("Should be completed", activity.get("Task 1").getTransitions().stream().
                filter(e -> e.Destination() == "Task 2").findFirst().get().isComplete(player));
        assertTrue("Should be completed", activity.get("Task 1").getTransitions().stream().
                filter(e -> e.Destination() == "Task 3").findFirst().get().isComplete(player));
        assertTrue("Should be completed", activity.get("Task 1").getTransitions().stream().
                filter(e -> e.Destination() == "Task 4").findFirst().get().isComplete(player));

    }

    @Test
    public void testIsActive() throws Exception {

    }

    @Test
    public void testIsFinal() throws Exception {
        IBlackboard player = new Subject("player");
        IActivity activity = ActivityBuilder.activity("Omelette").
                addState("Task 1").
                setInitialState("Task 1").
                addTransition("Task 1", "Task 2", (b -> b.query("Task 1") >= 1)).         // 1 = task completed
                addState("Task 2").                 // no transitions so isFinal
                build();

        assertFalse("Shouldn't be final", activity.get("Task 1").isFinal());
        assertTrue("Should be final", activity.get("Task 2").isFinal());


    }
}