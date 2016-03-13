package com.gard.testbed.engine;

import com.gard.testbed.abstractions.IEvent;
import com.gard.testbed.engine.eventBusx.events.ActivityEvent;
import com.gard.testbed.engine.eventBusx.events.UiEvent;
import com.gard.testbed.ui.TestUI;

/**
 * Created by Chris on 12/03/2016..
 * Singleton core system kernel
 */
public class Kernel {

    private static Kernel kernelInstance = new Kernel();
    private static final EventBus<IEvent> eventBus = EventBus.createWithLatest();   //singleton bus
    private static final ActivityManager activityManager = new ActivityManager(eventBus);
    private static final TestUI testUi = new TestUI(eventBus);

    public static Kernel getInstance() {
        return kernelInstance;
    }

    // Returns instance of eventBusx - provides all event producers with access
    public static EventBus<IEvent> getEventBus(){
        return eventBus;}

    private Kernel() {
    }

    public void initialise() {
        // Subscribe activity manager to eventBusx to listen for UI events
        activityManager.subscribe(UiEvent.class);
        // Subscribe UI to eventBusx to listen for Activity events
        testUi.subscribe(ActivityEvent.class);
    }

    public void run() {
        testUi.run();
    }

}
