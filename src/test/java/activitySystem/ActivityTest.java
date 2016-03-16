package activitySystem;

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
        IActivity act = ActivityBuilder.activity("Omelette").addState("1").
                setInitialState("1").
                addTransition("1","2",(b -> b.query("eggs") > 3)).
                build();

        assertEquals("1",act.getInitialState().getId());
        assertEquals(1, act.get("1").getTransitions().size());

    }

    @Test
    public void testTriggerCondition() {
        IBlackboard player = new Subject("player");
        IActivity act = ActivityBuilder.activity("Omelette").
                addState("1").
                setInitialState("1").
                addTransition("1","2",(b -> b.query("eggs") > 3)).
                build();
        assertFalse(act.get("1").getTransitions().get(0).getTrigger().test(player));
        player.set("eggs",5);
        assertTrue("Didn't trigger eggs > 3",act.get("1").getTransitions().get(0).getTrigger().test(player));
    }

}