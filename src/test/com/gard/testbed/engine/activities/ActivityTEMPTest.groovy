package com.gard.testbed.engine.activities

import com.gard.testbed.abstractions.TaskEntity

/**
*  Created by Chris on 18/02/2016..
*/
class ActivityTEMPTest extends GroovyTestCase {

    def activity = new ActivityOLD("ActivityOLD name", "Test instructions")
    def task1 = activity.addTask("Task 1", "")
    def task2 = activity.addTask("Task 2", "")
    def task3 = activity.addTask("Task 3", "")
    def task4 = activity.addTask("Task 4", "")
    def task11 = task1.addTask("Task 11", "")
    def task12 = task1.addTask("Task 12", "")
    def task21 = task2.addTask("Task 21", "")
    def task22 = task2.addTask("Task 22", "")
    def task23 = task2.addTask("Task 23", "")
    def task41 = task4.addTask("Task 41", "")
    def task42 = task4.addTask("Task 42", "")
    def task43 = task4.addTask("Task 43", "")
    def task421 = task42.addTask("Task 421", "")
    def task422 = task42.addTask("Task 422", "")
    def task423 = task42.addTask("Task 423", "")


    void testGetName() {

        assertEquals("Wrong activity name", "ActivityOLD name", activity.getName())
    }

    void testIsCompleted() {

    }

    void testGetParentTask() {
        assertEquals("GetParentTask should return null", null, activity.getParentTask())
    }

    void testComplete() {

    }

    void testHasTaskFromActivity1() {
        assertEquals("Not finding task from root 0 to level 1", true, activity.hasTask("Task 2"))
    }

    void testHasTaskFromActivity2() {
        assertEquals("Not finding task from root 0 to level 2", true, activity.hasTask("Task 11"))
    }

    void testHasTaskFromActivity3() {
        assertEquals("Not finding task from root 0 to level 3", true, activity.hasTask("Task 422"))
    }

    void testHasTaskFromTask1() {
        assertEquals("Not finding task from level 1 activity to level 2", true, activity.getTask("Task 2").hasTask("Task 21"))
    }

    void testHasTaskFromTask2() {
        assertEquals("Not finding task from level 1 activity to level 3", true, activity.getTask("Task 4").hasTask("Task 421"))
    }

    void testGetTaskFromActivity1() {
        assertEquals("Not getting task from root to get level 1", task2, activity.getTask("Task 2"))
    }

    void testGetTaskFromActivity2() {
        assertEquals("Not getting task from root to get level 2", task11, activity.getTask("Task 11"))
    }

    void testGetTaskFromActivity3() {
        assertEquals("Not getting task from root to get level 3", task422, activity.getTask("Task 422"))
    }

    void testGetTaskFromTask1() {
        assertEquals("Not getting task from level 1 task to get level 2", task11, activity.getTask("Task 1").getTask("Task 11"))
    }

    void testGetTaskFromTask2() {
        assertEquals("Not getting task from level 1 task to get level 3", task422, activity.getTask("Task 4").getTask("Task 422"))
    }


    void testGetTaskLevel0() {
        assertEquals("ActivityOLD task level should be 0", 0, activity.getTaskLevel())
    }

    void testGetTaskLevel1() {
        assertEquals("ActivityOLD task level should be 1", 1, activity.getTask("Task 2").getTaskLevel())
    }

    void testGetTaskLevel2() {
        assertEquals("ActivityOLD task level should be 2", 2, activity.getTask("Task 11").getTaskLevel())
    }

    void testGetTaskLevel3() {
        assertEquals("ActivityOLD task level should be 3", 3, activity.getTask("Task 422").getTaskLevel())
    }

    void testIsCompletedFail() {
        assertEquals("Should return false", false, activity.getTask("Task 3").isCompleted())
    }

    void testCompleteTask() {
        assertEquals("Should complete level 1 childless task", TaskEntity.CompletedStatus.MARKEDCOMPLETED, activity.getTask("Task 3").complete("Task 3"))
    }

    void testCompleteNoTask() {
        assertEquals("Should show NOTASK", TaskEntity.CompletedStatus.NOTASK, activity.complete("Task 999"))
    }

    void testCompleteActivityLevel1() {
        assertEquals("Should complete level 1 childless task", TaskEntity.CompletedStatus.MARKEDCOMPLETED, activity.complete("Task 3"))
    }

    void testCompleteActivityLevel2() {
        assertEquals("Should complete level 2 childless task", TaskEntity.CompletedStatus.MARKEDCOMPLETED, activity.complete("Task 11"))
    }

    void testCompleteActivityLevel3() {
        assertEquals("Should complete level 3 childless task", TaskEntity.CompletedStatus.MARKEDCOMPLETED, activity.complete("Task 421"))
    }

    void testCompleteActivityOutstanding() {
        assertEquals("Should show OUSTANDINGSUBTASKS", TaskEntity.CompletedStatus.OUSTANDINGSUBTASKS, activity.complete("Task 42"))
    }

    void testIsCompletedLevel1() {
        activity.complete("Task 3")
        assertEquals("Should return true", true, activity.getTask("Task 3").isCompleted())
    }

    void testIsCompletedLavel2() {
        activity.complete("Task 3")
        assertEquals("Should return true", true, activity.getTask("Task 3").isCompleted())
    }

}
