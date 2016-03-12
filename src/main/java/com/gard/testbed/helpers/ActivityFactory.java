package com.gard.testbed.helpers;

import com.gard.testbed.engine.petrinet.Petrinet;
import com.gard.testbed.engine.petrinet.Place;
import com.gard.testbed.engine.petrinet.Transition;

/**
 * Created by Chris on 15/02/2016..
 */

public class ActivityFactory {

    /** Creates a simple PN for testing
     * Structure =
     *                      Element 1
     *                    /           \
     *                 --              \
     *               /    \             \
     * Start - Task 1       Element 2 ------ Task 2 - End
     *               \                     /
     *                --------------------

     */
    public static Petrinet newTestSimplePetrinet() {
        Petrinet activity = new Petrinet("Simple");
        Place p1 = activity.place("Start", 1);
        Place p2 = activity.place("Task 1");
        Place p3 = activity.place("Element 1");
        Place p4 = activity.place("Element 2");
        Place p5 = activity.place("Task 2");
        Place p6 = activity.place("End");

        // Start to Task1
        activity.fullTransition("Start", p1, 1, p2, 1);

        // BRANCH 1 - Task1 direct to Task 2
        activity.fullTransition("Task 1", p2, 1, p5, 2);    // output 2 tokens to Task 2 to cater for synchronisation

        // BRANCH 2 - Task1 split to 2 subtasks
        Transition tr2 = activity.transition("Task 1 subtasks");
            // Arc Task 1 to Task 1 subtasks
            activity.arc("t1 to st", p2, tr2);
            // Arc Task 1 subtasks to Element 1
            activity.arc("st to e1", tr2, p3);
            // Arc Task 1 subtasks to Element 2
            activity.arc("st to e2", tr2, p4);

        // SYNCHRONISE - 2 subtasks to task 2 - (2 completed subtasks will result in 2 tokens in Task 2)
        // Element 1 to Task 2
        activity.fullTransition("Element 1", p3, 1, p5, 1);
        activity.fullTransition("Element 2", p4, 1, p5, 1);

        // Task 2 to End
        activity.fullTransition("Task 2", p5, 2, p6, 1);    // Task 2 transition only live with 2 tokens - caters for synchronisation

        return activity;
    }

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
