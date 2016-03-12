package com.gard.testbed.engine.petrinet;

import java.util.*;

/**
 * Created by Chris on 01/03/2016.
 * Original code from https://github.com/rmetzler/simple-java-petrinet.git
 */
public class Petrinet
        extends PetrinetObject {

    private static final String nl      = "\n";
    List<Place> places                  = new ArrayList<>();
    List<Transition> transitions        = new ArrayList<>();
    List<Arc> arcs                      = new ArrayList<>();
    List<InhibitorArc> inhibitors       = new ArrayList<>();
//    LinkedList<Token> activeTokens      = new LinkedList<>();    // All tokens active in Places in net
//    LinkedList<Token> tokenPool         = new LinkedList<>();    // Old tokens for recycling

    public Petrinet(String name) {
        super(name);
    }

    public void add(PetrinetObject o) {
        if (o instanceof InhibitorArc) {
            inhibitors.add((InhibitorArc) o);
        } else if (o instanceof Arc) {
            arcs.add((Arc) o);
        } else if (o instanceof Place) {
            places.add((Place) o);
        } else if (o instanceof Transition) {
            transitions.add((Transition) o);
        }
    }

    public List<Transition> getTransitionsAbleToFire() {
        ArrayList<Transition> list = new ArrayList<>();
        for (Transition t : transitions) {
            if (t.canFire()) {
                list.add(t);
            }
        }
        return list;
    }

    public Transition transition(String name) {
        Transition t = new Transition(name);
        transitions.add(t);
        return t;
    }

    public void fullTransition(String name, Place source, int sourceWeight,
                          Place destination, int destinationWeight) {
        Transition transition = this.transition(name);
        Arc a1 = this.arc(name + "_arc_in", source, transition, sourceWeight);
        Arc a2 = this.arc(name + "_arc_out", transition, destination, destinationWeight);

    }

    public Place place(String name) {
        Place p = new Place(name);
        places.add(p);
        return p;
    }

    public Place place(String name, int initial) {
        Place p = new Place(name, initial);
        places.add(p);
        return p;
    }

    public Arc arc(String name, Place p, Transition t) {
        Arc arc = new Arc(name, p, t);
        arcs.add(arc);
        return arc;
    }

    public Arc arc(String name, Place p, Transition t, int weight) {
        Arc arc = new Arc(name, p, t);
        arc.setWeight(weight);
        arcs.add(arc);
        return arc;
    }

    public Arc arc(String name, Transition t, Place p) {
        Arc arc = new Arc(name, t, p);
        arcs.add(arc);
        return arc;
    }

    public Arc arc(String name, Transition t, Place p, int weight) {
        Arc arc = new Arc(name, t, p);
        arc.setWeight(weight);
        arcs.add(arc);
        return arc;
    }

    public InhibitorArc inhibitor(String name, Place p, Transition t) {
        InhibitorArc i = new InhibitorArc(name, p, t);
        inhibitors.add(i);
        return i;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Petrinet ");
        sb.append(super.toString()).append(nl);
        sb.append("---Transitions---").append(nl);
        for (Transition t : transitions) {
            sb.append(t).append(nl);
        }
        sb.append("---Places---").append(nl);
        for (Place p : places) {
            sb.append(p).append(nl);
        }

        return sb.toString();
    }

    public String getInfo() {
        StringBuilder sb = new StringBuilder("Petrinet ");
        sb.append(super.toString()).append(nl);
        sb.append("---Transitions---").append(nl);
        for (Transition t : transitions) {
            sb.append(t.getInfo());
        }
        sb.append("---Places---").append(nl);
        for (Place p : places) {
            sb.append(p).append(nl);
        }

        return sb.toString();
    }

    public List<Place> getPlaces() {
        return places;
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    public List<Arc> getArcs() {
        return arcs;
    }

    public List<InhibitorArc> getInhibitors() {
        return inhibitors;
    }
}

