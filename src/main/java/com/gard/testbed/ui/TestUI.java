package com.gard.testbed.ui;

import com.gard.testbed.engine.ActivityManager;

import java.util.Scanner;

/**
 * Created by Chris on 14/02/2016..
 */

public class TestUI {
    // Store activityManager to send events
    ActivityManager activityManager;
    Scanner scanner = new Scanner(System.in);
    boolean retry = true;

    public TestUI(ActivityManager activityManager) {
        this.activityManager = activityManager;
    }

    public void run(){
//        // Welcome and select activity
//        System.out.println("Welcome to GARD\nlease select an activity (options: 1 = Omelette activity)...\n>");
//        while (retry){
//        String input = scanner.next();
//            switch (input) {
//                case "1":
//                    System.out.println("Omelette activity selected...");
//                    System.out.println("This is what we will be doing:\n");
//                    activityManager.loadActivity();
//                    activityManager.printActivity();
//                    retry = false;
//                    break;
//                default:
//                    System.out.println("Please retry (only option is '1' for now)");
//            }
//        }
//
//        // Operations on activity
//        retry = true;
//        String options = "Options: 1..4 = 'Collect ingredient' 1-4 / 5..9 = 'Collect equipment' 1-5 / p = print activity / x = exit\n>";
//        System.out.println("\nSelect event...\n" + options);
//
//        while (retry){
//            String input = scanner.next();
//            switch (input) {
//                // Collect ingredients triggers
//                case "1":
//                    System.out.println("Event: Eggs collected");
//                    activityManager.complete("Eggs");
//                    break;
//                case "2":
//                    System.out.println("Event: Milk collected");
//                    activityManager.complete("Milk");
//                    break;
//                case "3":
//                    System.out.println("Event: Butter collected");
//                    activityManager.complete("Butter");
//                    break;
//                case "4":
//                    System.out.println("Event: Salt collected");
//                    activityManager.complete("Salt");
//                    break;
//
//                // Collect equipment triggers
//                case "5":
//                    System.out.println("Event: Bowl collected");
//                    activityManager.complete("Bowl");
//                    break;
//                case "6":
//                    System.out.println("Event: Fork collected");
//                    activityManager.complete("Fork");
//                    break;
//                case "7":
//                    System.out.println("Event: Frying pan collected");
//                    activityManager.complete("Frying pan");
//                    break;
//                case "8":
//                    System.out.println("Event: Knife collected");
//                    activityManager.complete("Knife");
//                    break;
//                case "9":
//                    System.out.println("Event: Spatula collected");
//                    activityManager.complete("Spatula");
//                    break;
//
//                // Print activity status
//                case "p":
//                    activityManager.printActivity();
//                    break;
//
//                case "x":
//                    System.out.println("Exit run - Goodbye");
//                    retry = false;
//                    break;
//                default:
//                    System.out.println("Invalid entry - " + options);
//            }
//        }

    }
}
