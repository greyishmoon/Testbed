package com.gard.testbed.petrinet.gui;


import com.gard.testbed.helpers.ActivityFactory;
import com.gard.testbed.petrinet.logic.Petrinet;
import com.gard.testbed.petrinet.logic.Place;

/**
 * Created by Chris on 01/03/2016.
 */
public class OmeletteActivity {

    public static void main(String[] args) {

        Petrinet activity = ActivityFactory.newTestOmelettePetrinet();


//        Petrinet activity = new Petrinet("Omelette");
//
//        Place p1 = activity.place("Start", 1);
//        Place p2 = activity.place("Collect ingredients");
//        // Concurrent tasks - modelled using inhibitor arcs
//            Place p21 = activity.place("Collect eggs");
//            Place p22 = activity.place("Collect milk");
//            Place p23 = activity.place("Collect salt");
//            Place p24 = activity.place("Collect butter");
//        Place p3 = activity.place("Prepare eggs");
//        // Concurrent tasks - modelled using inhibitor arcs
//        // sequential sub-tasks
//            Place p31 = activity.place("   Break eggs");
//            Place p32 = activity.place("   Add milk");
//            Place p33 = activity.place("   Add salt");
//            Place p34 = activity.place("   Beat eggs (because they've been naughty)");
//        Place p4 = activity.place("Cook eggs");
//        // sequential sub-tasks
//            Place p41 = activity.place("   Put pan on hob");
//            Place p42 = activity.place("   Turn on heat");
//            Place p43 = activity.place("   Wait until hot");
//            Place p44 = activity.place("   Add eggs");
//            Place p45 = activity.place("   Wait until underside cooked");
//            Place p46 = activity.place("   Turn omelette");
//            Place p47 = activity.place("   Turn off heat");
//            Place p48 = activity.place("   Serve omelette");
//        Place p5 = activity.place("FINISHED - congrats");
//
//        activity.fullTransition("start_activity", p1, 1, p2, 4);
//        // Transitions for concurrent tasks - inhibitor on
//            activity.fullTransition("   eggs_collected", p2, 1, p3, 1);
//            activity.fullTransition("   milk_collected", p2, 1, p3, 1);
//            activity.fullTransition("   salt_collected", p2, 1, p3, 1);
//            activity.fullTransition("   butter_collected", p2, 1, p3, 1);
//        activity.fullTransition("eggs_prepared", p3, 4, p4, 1);
//            activity.fullTransition("   detailed_prepare_eggs", p3, 4, p31, 1);
//            activity.fullTransition("   eggs_broken", p31, 1, p32, 1);
//            activity.fullTransition("   milk_added", p32, 1, p33, 1);
//            activity.fullTransition("   salt_added", p33, 1, p34, 1);
//            activity.fullTransition("   eggs_beaten", p34, 1, p4, 1);
//        activity.fullTransition("eggs_cooked", p4, 1, p5, 1);
//            activity.fullTransition("detailed_cok_eggs", p4, 1, p41, 1);
//            activity.fullTransition("pan_on_hob", p41, 1, p42, 1);
//            activity.fullTransition("heat_on", p42, 1, p43, 1);
//            activity.fullTransition("pan_hot", p43, 1, p44, 1);
//            activity.fullTransition("eggs_added", p44, 1, p45, 1);
//            activity.fullTransition("underside_cooked", p45, 1, p45, 1);
//            activity.fullTransition("pan_on_hob", p41, 1, p42, 1);
//        activity.fullTransition("restart_activity", p5, 1, p1, 1);
//


        System.out.println(activity.toString());
//
//        Transition t1 = activity.transition("start_activity");
//        Transition t2 = activity.transition("ingredients_collected");
//        Transition t3 = activity.transition("eggs_prepared");
//        Transition t4 = activity.transition("eggs_cooked");
//
//        Arc a1 = activity.arc("a1", p1, t1);
//        Arc a2 = activity.arc("a2", t1, p2);
//        Arc a3 = activity.arc("a3", p2, t2);
//        Arc a4 = activity.arc("a4", t2, p3);
//        a4.setWeight(4);
//        Arc a5 = activity.arc("a5", p3, t3);
//        Arc a6 = activity.arc("a6", t3, p4);
//        Arc a7 = activity.arc("a7", p4, t4);
//        Arc a8 = activity.arc("a8", t4, p1);

        PetrinetGUI.displayPetrinet(activity);

    }

}
