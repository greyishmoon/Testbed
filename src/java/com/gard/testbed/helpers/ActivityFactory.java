package com.gard.testbed.helpers;

import com.gard.testbed.engine.activities.ActivityOLD;
import com.gard.testbed.abstractions.TaskEntity;

/**
 * Created by Chris on 15/02/2016..
 */

public class ActivityFactory {

    // Temp method to manually create activity for testing
    public static ActivityOLD newTestOmeletteActivity(){
        ActivityOLD testActivity = new ActivityOLD("Cook Omelette", "Instruction / overview text");

        // Add 2 tasks with items to collect
        TaskEntity ingredientsTask = testActivity.addTask("Collect Ingredients", "Collect 4 ingredients");
        ingredientsTask.addTask("Eggs", "Collect 3 eggs");
        ingredientsTask.addTask("Milk", "Collect milk");
        ingredientsTask.addTask("Butter", "Collect butter");
        ingredientsTask.addTask("Salt", "Collect salt");

        TaskEntity equipmentTask = testActivity.addTask("Collect Equipment", "Collect 5 items of equipment");
        equipmentTask.addTask("Bowl", "Get bowl");
        equipmentTask.addTask("Fork", "Get fork");
        equipmentTask.addTask("Frying pan", "Get frying pan");
        equipmentTask.addTask("Knife", "Get knife");
        equipmentTask.addTask("Spatula", "Get spatula");



//        Task task1 = new Task("Collect Ingredients");
//        task1.addTask("Eggs", new Item("Eggs"));
//        task1.addTask("Milk", new Item("Milk"));
//        task1.addTask("Butter", new Item("Butter"));
//        task1.addTask("Salt", new Item("Salt"));
//        testActivity.addTask("Collect Ingredients", task1);

//        Task task2 = new Task("Collect Equipment");
//        task2.addTask("Bowl", new Item("Bowl"));
//        task2.addTask("Fork", new Item("Fork"));
//        task2.addTask("Frying pan", new Item("Frying pan"));
//        task2.addTask(new Item("Knife"));
//        task2.addTask(new Item("Spatula"));
//        testActivity.addTask(task2);

        // TEST PRINT
//        testActivity.print();

        return testActivity;
    }
}
