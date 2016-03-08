package com.gard.testbed;

import com.gard.testbed.engine.ActivityManager;
import com.gard.testbed.ui.TestUI;

public class Main {

    public static void main(String[] args) {
        // Main kernel
        ActivityManager kernel = new ActivityManager();

        // Test console interaction
        TestUI testUI = new TestUI(kernel);
        testUI.run();



    }
}
