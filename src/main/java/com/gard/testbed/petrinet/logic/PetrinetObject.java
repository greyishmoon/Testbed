package com.gard.testbed.petrinet.logic;

/**
 * Created by Chris on 01/03/2016.
 * Original code from https://github.com/rmetzler/simple-java-petrinet.git
 */
public class PetrinetObject {

    private String name;

    public PetrinetObject(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return /*getClass().getSimpleName() + " " + */ name;
    }
}
