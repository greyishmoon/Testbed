package com.gard.testbed.engine.eventBus.events;

/**
 * Created by Chris on 11/03/2016..
 * Contains all message types for all custom events
 */
public enum MessageType {

    // ActivityEvent types...
    COMPLETETASK,       // Requests a live transition to be completed (target transition to be fired)
    TASKCOMPLETED,      // Reports transition fired and completed
    LOADSUBTASK,        // Request to load sub-task activity of current state IF there is one present
    STATUS,             // Reports PN current status with list of active Transitions (ready to fire)

    // Blackboard types...
    UPDATED,            // Reports Blackboard's state has changed

    // UiEvent types...
    GETSTATUS,          // Requests status update

}
