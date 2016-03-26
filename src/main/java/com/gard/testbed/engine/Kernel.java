package com.gard.testbed.engine;

import com.gard.testbed.engine.eventBus.events.ActivityEvent;
import com.google.common.eventbus.EventBus;

/**
 * Created by Chris on 12/03/2016..
 * Singleton core system kernel
 */
public class Kernel {

    private static Kernel kernelInstance = new Kernel();
    private static final EventBus eventBus = new EventBus();   // Guava eventbus
    private static final ActivityManager activityManager = new ActivityManager();

    public static Kernel getInstance() {
        return kernelInstance;
    }

    // Returns instance of eventBus - provides all event producers with access
    public static EventBus getEventBus(){
        return eventBus;}

    private Kernel() {
    }

    public void initialise() {
        // Subscribe activity manager to eventBus to listen for UI events
//        activityManager.subscribe(UiEvent.class);
        // Subscribe UI to eventBus to listen for Activity events
//        testUi.subscribe(ActivityEvent.class);
    }

    public void run() {
//        testUi.run();
    }

}
