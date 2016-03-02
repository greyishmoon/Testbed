package com.gard.testbed.petrinet.logic;

/**
 * Created by Chris on 01/03/2016.
 */
public class InhibitorArc
        extends Arc {


    protected InhibitorArc(String name, Place p, Transition t) {
        super(name, p, t);
    }

    /**
     * It may only be fired,
     * If less than token dsa inhibitor weight are on the spot
     */

    @Override
    public boolean canFire() {
        return (place.getTokens() < this.getWeight());
    }

    /**
     * beim feuern einer Inhibitor-Kante gehen keine Tokens die Kante entlang
     */
    @Override
    public void fire() {
        // do nothing
    }


}

