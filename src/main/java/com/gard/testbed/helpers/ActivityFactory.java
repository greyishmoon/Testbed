package com.gard.testbed.helpers;

import com.gard.testbed.engine.petrinet.logic.Petrinet;
import com.gard.testbed.engine.petrinet.logic.Place;
import com.gard.testbed.engine.petrinet.logic.Transition;

/**
 * Created by Chris on 15/02/2016..
 */

public class ActivityFactory {

    public static Petrinet newTestOmelettePetrinet() {
        Petrinet activity = new Petrinet("Omelette");

        Place p1 = activity.place("Start", 1);
        Place p2 = activity.place("Collect ingredients");
        // Concurrent tasks - modelled using inhibitor arcs
        Place p21 = activity.place("Collect eggs");
        Place p22 = activity.place("Collect milk");
        Place p23 = activity.place("Collect salt");
        Place p24 = activity.place("Collect butter");
        Place p3 = activity.place("Prepare eggs");
        // Concurrent tasks - modelled using inhibitor arcs
        // sequential sub-tasks
        Place p31 = activity.place("   Break eggs");
        Place p32 = activity.place("   Add milk");
        Place p33 = activity.place("   Add salt");
        Place p34 = activity.place("   Beat eggs (because they've been naughty)");
        Place p4 = activity.place("Cook eggs");
        // sequential sub-tasks
        Place p41 = activity.place("   Put pan on hob");
        Place p42 = activity.place("   Turn on heat");
        Place p43 = activity.place("   Wait until hot");
        Place p44 = activity.place("   Add eggs");
        Place p45 = activity.place("   Wait until underside cooked");
        Place p46 = activity.place("   Turn omelette");
        Place p47 = activity.place("   Turn off heat");
        Place p48 = activity.place("   Serve omelette");
        Place p5 = activity.place("FINISHED - congrats");

        activity.fullTransition("start_activity", p1, 1, p2, 1);
        // Transition to split into 4 paths
        Transition t2 = activity.transition("collect_items");
        // Set arc into transition
        activity.arc("collect_items_arc", p2, t2);
        // Set arcs out to 4 collect tasks
        activity.arc("Collect eggs_arc", t2, p21);
        activity.arc("Collect milk_arc", t2, p22);
        activity.arc("Collect salt_arc", t2, p23);
        activity.arc("Collect butter_arc", t2, p24);
        // Transitions for concurrent tasks - inhibitor on
        activity.fullTransition("   eggs_collected", p21, 1, p3, 1);
        activity.fullTransition("   milk_collected", p22, 1, p3, 1);
        activity.fullTransition("   salt_collected", p23, 1, p3, 1);
        activity.fullTransition("   butter_collected", p24, 1, p3, 1);


        activity.fullTransition("eggs_prepared", p3, 4, p4, 1);
        activity.fullTransition("   detailed_prepare_eggs", p3, 4, p31, 1);
        activity.fullTransition("   eggs_broken", p31, 1, p32, 1);
        activity.fullTransition("   milk_added", p32, 1, p33, 1);
        activity.fullTransition("   salt_added", p33, 1, p34, 1);
        activity.fullTransition("   eggs_beaten", p34, 1, p4, 1);
        activity.fullTransition("eggs_cooked", p4, 1, p5, 1);
        activity.fullTransition("detailed_cok_eggs", p4, 1, p41, 1);
        activity.fullTransition("pan_on_hob", p41, 1, p42, 1);
        activity.fullTransition("heat_on", p42, 1, p43, 1);
        activity.fullTransition("pan_hot", p43, 1, p44, 1);
        activity.fullTransition("eggs_added", p44, 1, p45, 1);
        activity.fullTransition("underside_cooked", p45, 1, p45, 1);
        activity.fullTransition("pan_on_hob", p41, 1, p42, 1);
        activity.fullTransition("restart_activity", p5, 1, p1, 1);

        return activity;
    }



}