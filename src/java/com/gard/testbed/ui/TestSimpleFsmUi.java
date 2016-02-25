package com.gard.testbed.ui;

import com.gard.testbed.engine.Kernel;
import com.gard.testbed.engine.fsm.ActivityStateMachine;

import java.util.Scanner;

/**
 * Created by Chris on 25/02/2016..
 */
public class TestSimpleFsmUi {

    ActivityStateMachine activityFsm = ActivityStateMachine.getInstance();   // reference to FSM
    Scanner scanner = new Scanner(System.in);
    boolean retry = true;

    public TestSimpleFsmUi() {
    }

    public void run() {
        String options = "Options: 1..3 = 'Complete tasks 1 to 3 / x = exit\n>";
        System.out.println("Simple FSM test - 3 states then finish");
        System.out.println("Starting state = " + activityFsm.getCurrentState().getName());
        System.out.println("\nSelect event...\n" + options);

        while (retry) {
            String input = scanner.next();
            switch (input) {
                // Collect ingredients triggers
                case "1":
                    System.out.println("Event: Task 1 completed");
                    activityFsm.completeState("Task 1");
                    break;
                case "2":
                    System.out.println("Event: Task 2 completed");
                    activityFsm.completeState("Task 2");
                    break;
                case "3":
                    System.out.println("Event: Task 3 completed");
                    activityFsm.completeState("Task 3");
                    break;
                case "s":
                    System.out.println("State = " + activityFsm.getCurrentState().getName());
                    break;
                case "x":
                    System.out.println("Exit run - Goodbye");
                    Kernel.getInstance().stop();
                    retry = false;
                    break;
                default:
                    System.out.println("Invalid entry - " + options);
            }

        }
    }
}