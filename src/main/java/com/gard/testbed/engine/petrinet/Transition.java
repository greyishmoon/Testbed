package com.gard.testbed.engine.petrinet;

import com.gard.testbed.engine.Kernel;
import com.gard.testbed.engine.eventBus.events.ActivityEvent;
import com.gard.testbed.engine.eventBus.events.MessageType;

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

    private List<Arc> incoming = new ArrayList<>();
    private List<Arc> outgoing = new ArrayList<>();

    /**
     * True if transition can fire (requiring all its arcs to be fireable)
     */
    public boolean canFire() {
        boolean canFire;

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
    public boolean fire() {
        for (Arc arc : incoming) {
            arc.fire();
        }

        for (Arc arc : outgoing) {
            arc.fire();
        }

        // Post ActivityEvent indicating transition fired and action completed successfully
        postCompletedEvent();

        return true;
    }

    private void postEvent(String target, MessageType type, String message) {
        Kernel.getEventBus().post(new ActivityEvent(target, type, message));
    }

    // Post completed event with this.name as target
    private void postCompletedEvent() {
        postEvent(this.getName(), MessageType.TASKCOMPLETED, "Transition fired and completed");
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

