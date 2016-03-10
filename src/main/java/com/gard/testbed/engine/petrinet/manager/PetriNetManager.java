package com.gard.testbed.engine.petrinet.manager;

import com.gard.testbed.helpers.ActivityFactory;
import com.gard.testbed.engine.petrinet.logic.Petrinet;
import com.gard.testbed.engine.petrinet.logic.Token;
import com.gard.testbed.engine.petrinet.logic.Transition;

import java.util.List;


/**
 * Created by Chris on 02/03/2016.
 */
public class PetriNetManager {

    private static Petrinet activity;        // Petri Net of activity

    public static void main(String[] args) {
        activity = ActivityFactory.newTestOmelettePetrinet();
//        System.out.println(activity.toString());

        List<Transition> transitions = activity.getTransitionsAbleToFire();

        for (int i = 0; i < transitions.size(); i++) {
            Transition transition =  transitions.get(i);
            System.out.println(transition);
        }

        List<Token> tokens = activity.getActiveTokenList();

        for (int i = 0; i < tokens.size(); i++) {
            Token token =  tokens.get(i);
            System.out.println(token);
        }

        transitions.get(0).fire();

        transitions = activity.getTransitionsAbleToFire();

        for (int i = 0; i < transitions.size(); i++) {
            Transition transition =  transitions.get(i);
            System.out.println(transition);
        }

        tokens = activity.getActiveTokenList();

        for (int i = 0; i < tokens.size(); i++) {
            Token token =  tokens.get(i);
            System.out.println(token);
        }


    }



}
