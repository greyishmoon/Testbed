package com.gard.testbed;

import com.gard.testbed.engine.ActivityManager;
import com.gard.testbed.engine.Kernel;
import com.gard.testbed.engine.fsm.ActivityBuilder;
import com.gard.testbed.ui.TestSimpleFsmUi;
import com.gard.testbed.ui.TestUi;

public class Main {

    public static void main(String[] args) {
        // Main kernel
        Kernel kernel = Kernel.getInstance();

        // Load test activity
        kernel.getActivityFsm().loadActivity(ActivityBuilder.SimpleTestActivity());

        // Start loop service
        kernel.start();

        TestSimpleFsmUi testUi = new TestSimpleFsmUi();
        testUi.run();





//        // Test console interaction
//        TestUi testUI = new TestUi(kernel);
//        testUI.run();



    }
}
