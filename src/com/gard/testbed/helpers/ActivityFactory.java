package com.gard.testbed.helpers;

import com.gard.testbed.activities.Activity;
import com.gard.testbed.activities.Item;
import com.gard.testbed.activities.Task;
import com.gard.testbed.activities.TaskEntity;

/**
 * Created by Chris on 15/02/2016.
 */
public class ActivityFactory {

    // Temp method to manually create activity for testing
    public static Activity newTestOmeletteActivity(){
        Activity testActivity= new Activity("Cook Omelette");

        // Add 2 tasks with items to collect
        TaskEntity ingredientsTask = testActivity.addTask("Collect Ingredients");
        ingredientsTask.addItem("Eggs");
        ingredientsTask.addItem("Milk");
        ingredientsTask.addItem("Butter");
        ingredientsTask.addItem("Salt");

        TaskEntity equipmentTask = testActivity.addTask("Collect Equipment");
        equipmentTask.addItem("Bowl");
        equipmentTask.addItem("Fork");
        equipmentTask.addItem("Frying pan");
        equipmentTask.addItem("Knife");
        equipmentTask.addItem("Spatula");



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
