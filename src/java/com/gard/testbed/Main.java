package com.gard.testbed;

import com.gard.testbed.engine.ActivityManager;
import com.gard.testbed.ui.TestUi;

public class Main {

    public static void main(String[] args) {
        // Main kernel
        ActivityManager kernel = new ActivityManager();

        // Test console interaction
        TestUi testUI = new TestUi(kernel);
        testUI.run();



    }
}
