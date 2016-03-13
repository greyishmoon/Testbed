package com.gard.testbed.engine.eventBusx.events;

/**
 * Created by Chris on 11/03/2016..
 * Contains all message types for all custom events
 */
public enum MessageType {

    // ActivityEvent types...
    COMPLETED,          // Reports transition fired and completed
    STATUS,             // Reports PN current status with list of active Transitions (ready to fire)

    // UiEvent types...
    COMPLETE,           // Requests a live transition to be completed (target transition to be fired)
    GETSTATUS,          // Requests status update

}
