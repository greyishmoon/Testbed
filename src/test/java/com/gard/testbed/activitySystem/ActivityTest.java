package com.gard.testbed.activitySystem;

import com.gard.testbed.activitySystem.ActivityBuilder;

import com.gard.testbed.activitySystem.IActivity;
import com.gard.testbed.activitySystem.IBlackboard;
import com.gard.testbed.activitySystem.Subject;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by va922kg on 3/15/16.
 */
public class ActivityTest {

    @Test
    public void createAnActivity() {
        // using builder to create an activity
        IActivity act = ActivityBuilder.activity("Omelette", 1).addState("1").
                setInitialState("1").
                addTransition("1","2",(b -> b.query("eggs") > 3)).
                build();

        assertEquals("1",act.getInitialState().getId());
        assertEquals(1, act.get("1").getTransitions().size());

    }

    @Test
    public void testTriggerCondition() {
        IBlackboard player = new Subject("player");
        IActivity act = ActivityBuilder.activity("Omelette", 1).
                addState("1").
                setInitialState("1").
                addTransition("1","2",(b -> b.query("eggs") > 3)).
                build();
        assertFalse(act.get("1").getTransitions().get(0).getTrigger().test(player));
        player.set("eggs",5);
        assertTrue("Didn't trigger eggs > 3",act.get("1").getTransitions().get(0).getTrigger().test(player));
    }

    @Test
    public void testSequentialTasks() {
        IBlackboard player = new Subject("player");
        IActivity act = ActivityBuilder.activity("Omelette", 1).
                addState("Task 1").
                setInitialState("Task 1").
                addTransition("Task 1","Task 2",(b -> b.query("Task 1") >= 1)).         // 1 = task completed
                addState("Task 2").
                addTransition("Task 2","Task 3",(b -> b.query("Task 2") >= 1)).
                // For sequentially dependent tasks this should not trigger until task 2 is complete
                addState("Task 3").
                addTransition("Task 3","End",(b -> b.query("Task 3") >= 1)).
                addState("End").                                                        // no transitions so isFinal
                build();

        assertFalse("Shouldn't trigger Task 1", act.get("Task 1").getTransitions().get(0).isComplete(player));
        assertFalse("Shouldn't trigger Task 2", act.get("Task 2").getTransitions().get(0).getTrigger().test(player));
        assertFalse("Shouldn't trigger Task 3", act.get("Task 3").getTransitions().get(0).getTrigger().test(player));

        player.set("Task 1",1);
        assertTrue("Didn't trigger Task 1",act.get("Task 1").getTransitions().get(0).getTrigger().test(player));
        assertFalse("Shouldn't trigger Task 2", act.get("Task 2").getTransitions().get(0).getTrigger().test(player));
        assertFalse("Shouldn't trigger Task 3", act.get("Task 3").getTransitions().get(0).getTrigger().test(player));

        player.set("Task 3",1);
        assertTrue("Didn't trigger Task 1",act.get("Task 1").getTransitions().get(0).getTrigger().test(player));
        assertFalse("Shouldn't trigger Task 2", act.get("Task 2").getTransitions().get(0).getTrigger().test(player));
        // For sequentially dependent tasks this should not trigger until task 2 is complete
        assertTrue("Didn't trigger Task 3", act.get("Task 3").getTransitions().get(0).getTrigger().test(player));
    }

    @Test
    public void testConcurrentTasks() {
        IBlackboard player = new Subject("player");
        IActivity act = ActivityBuilder.activity("Omelette", 1).
                addState("Task 1").
                setInitialState("Task 1").
                // Task 1 branches to both Concurrent 1 and Concurrent 2
                addTransition("Task 1","Conc 1",(b -> b.query("Task 1") >= 1)).         // 1 = task completed
                addTransition("Task 1","Conc 2",(b -> b.query("Task 1") >= 1)).
                // Synchronise 2 concurrent tasks to task 2
                addState("Conc 1").
                addTransition("Conc 1","Task 2",(b -> b.query("Conc 1") >= 1)).
                addState("Conc 2").
                addTransition("Conc 2","Task 2",(b -> b.query("Conc 2") >= 1)).
                // Task 2 transition should only be active when both Conc 1 and 2 completed
                addState("Task 2").
                // query hack to only activate task 2 when both Conc tasks completed - create automatic transition
                addTransition("Task 2","End",(b -> (b.query("Conc 1") + b.query("Conc 2")) >= 2)).
                addState("End").                                                        // no transitions so isFinal
                build();

        assertFalse("Shouldn't trigger Task 1", act.get("Task 1").getTransitions().get(0).getTrigger().test(player));
        assertFalse("Shouldn't trigger Conc 1", act.get("Conc 1").getTransitions().get(0).getTrigger().test(player));
        assertFalse("Shouldn't trigger Conc 2", act.get("Conc 2").getTransitions().get(0).getTrigger().test(player));
        assertFalse("Shouldn't trigger Task 2", act.get("Task 2").getTransitions().get(0).getTrigger().test(player));

        player.set("Task 1",1);
        assertTrue("Didn't trigger Task 1",act.get("Task 1").getTransitions().get(0).getTrigger().test(player));
        assertFalse("Shouldn't trigger Conc 1", act.get("Conc 1").getTransitions().get(0).getTrigger().test(player));
        assertFalse("Shouldn't trigger Conc 2", act.get("Conc 2").getTransitions().get(0).getTrigger().test(player));
        assertFalse("Shouldn't trigger Task 2", act.get("Task 2").getTransitions().get(0).getTrigger().test(player));

        player.set("Conc 1",1);
        assertTrue("Didn't trigger Task 1",act.get("Task 1").getTransitions().get(0).getTrigger().test(player));
        assertTrue("Didn't trigger Conc 1",act.get("Conc 1").getTransitions().get(0).getTrigger().test(player));
        assertFalse("Shouldn't trigger Conc 2", act.get("Conc 2").getTransitions().get(0).getTrigger().test(player));
        assertFalse("Shouldn't trigger Task 2", act.get("Task 2").getTransitions().get(0).getTrigger().test(player));

        player.set("Conc 2",1);
        assertTrue("Didn't trigger Task 1",act.get("Task 1").getTransitions().get(0).getTrigger().test(player));
        assertTrue("Didn't trigger Conc 1",act.get("Conc 1").getTransitions().get(0).getTrigger().test(player));
        assertTrue("Didn't trigger Conc 2",act.get("Conc 2").getTransitions().get(0).getTrigger().test(player));
        assertTrue("Didn't trigger Task 2",act.get("Task 2").getTransitions().get(0).getTrigger().test(player));
    }

}