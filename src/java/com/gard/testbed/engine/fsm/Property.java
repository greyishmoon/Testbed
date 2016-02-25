package com.gard.testbed.engine.fsm;

/**
 * Created by Chris on 24/02/2016..
 */
public class Property {

    // Static data for state
    private final String name;
    private final String instructions;

    public Property(String name, String instructions) {
        this.name = name;
        this.instructions = instructions;
    }

    public String getName() {
        return name;
    }

    public String getInstructions() {
        return instructions;
    }
}
