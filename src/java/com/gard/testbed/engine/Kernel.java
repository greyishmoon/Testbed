package com.gard.testbed.engine;

import com.gard.testbed.engine.fsm.ActivityStateMachine;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Chris on 24/02/2016..
 */
public class Kernel {

    // Singleton
    private static Kernel ourInstance = new Kernel();
    public static Kernel getInstance() {
        return ourInstance;
    }

    // Basic loop
    ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
    Runnable runnable = () -> {updateKernel();};

    // Engine components
    ActivityStateMachine activityFsm = ActivityStateMachine.getInstance();


    private Kernel() {
    }

    public ActivityStateMachine getActivityFsm() {
        return activityFsm;
    }


    // Start loop service @ 500ms
    public void start() {
        service.scheduleAtFixedRate(runnable, 0, 500, TimeUnit.MILLISECONDS);
    }

    // Stop loop service
    public void stop(){
        service.shutdown();
    }

    public void updateKernel() {

        processEvents();
        update();
        updateUi();

    }

    // Process event stack
    private void processEvents() {

    }

    // Update engine components
    public void update(){
        activityFsm.update();
    }

    // Update UI
    private void updateUi() {

    }
}
