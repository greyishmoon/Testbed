package com.gard.testbed.engine.activities

/**
 * Created by Chris on 23/02/2016..
 */
class ActivityTest extends GroovyTestCase {

    Activity activity = new Activity("Test activity", "Test instructions")
    void testAddTask() {

    }

    void testGetTask() {

    }

    void testGetName() {
        assertEquals("Wrong name", "Test activity", activity.getName())
    }

    void testGetInstructions() {
        assertEquals("Wrong instructions", "Test instructions", activity.getInstructions())
    }

    void testIsComposite() {
        assertEquals("Should be composite", true, activity.isComposite())
    }

    void testComplete() {

    }

    void testIsComplete() {

    }
}
