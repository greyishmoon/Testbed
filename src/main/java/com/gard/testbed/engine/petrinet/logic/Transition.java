package com.gard.testbed.engine.petrinet.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris on 01/03/2016.
 */
public class Transition
        extends PetrinetObject{

    protected Transition(String name) {
        super(name);
    }

    private List<Arc> incoming = new ArrayList<Arc>();
    private List<Arc> outgoing = new ArrayList<Arc>();

    /**
     * @return if transition can fire
     */
    public boolean canFire() {
        boolean canFire = true;

        // detects if no edges
        canFire = ! this.isNotConnected();

        for (Arc arc : incoming) {
            canFire = canFire & arc.canFire();
        }

        for (Arc arc : outgoing) {
            canFire = canFire & arc.canFire();
        }
        return canFire;
    }

    /**
     * Triggers transition
     */
    public void fire() {
        for (Arc arc : incoming) {
            arc.fire();
        }

        for (Arc arc : outgoing) {
            arc.fire();
        }
    }

    /**
     * @param arc Add incoming edge
     */
    public void addIncoming(Arc arc) {
        this.incoming.add(arc);
    }

    /**
     * @param arc Add outgoing edge
     */
    public void addOutgoing(Arc arc) {
        this.outgoing.add(arc);
    }

    /**
     * @return if transition is associated with an edge
     */
    public boolean isNotConnected() {
        return incoming.isEmpty() && outgoing.isEmpty();
    }

    @Override
    public String toString() {
        return super.toString() +
                (isNotConnected() ? " IS NOT CONNECTED" : "" ) +
                (canFire()? " READY TO FIRE" : "");
    }

    public String getInfo() {
        String buf = "    ";
        String nl = "\n";
        StringBuilder sb = new StringBuilder(getName().toString()).append(nl);
        sb.append(buf).append("In arcs (source place - arc weight:").append(nl);
        for (Arc a : incoming) {
            sb.append(buf + buf).append(a.getPlace().getName())
                    .append(" - ").append(a.getWeight()).append(nl);
        }
        sb.append(buf).append("Out arcs (destination place - arc weight):").append(nl);
        for (Arc a : outgoing) {
            sb.append(buf + buf).append(a.getPlace().getName())
                    .append(" - ").append(a.getWeight()).append(nl);
        }
        return sb.toString();
    }

}

