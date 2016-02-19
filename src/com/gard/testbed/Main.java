package com.gard.testbed;

import com.gard.testbed.engine.ActivityManager;
import com.gard.testbed.testui.TestUI;

public class Main {

    public static void main(String[] args) {
        //TODO keep divided in an application the 3 phase of a lifecycle - initialization - run - shutdown
        // TODO find an abstraction for the whole app. Is useful if you want to test it.
        // Main kernel
        ActivityManager kernel = new ActivityManager();

        // Test console interaction
        TestUI testUI = new TestUI(kernel);
        testUI.run();
    }
}
