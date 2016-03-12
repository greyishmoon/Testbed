package com.gard.testbed;

import com.gard.testbed.engine.Kernel;

public class Main {

    private static Kernel kernel = Kernel.getInstance();

    public static void main(String[] args) {
        kernel.initialise();
        kernel.run();
    }

}
