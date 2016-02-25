package com.gard.testbed.ui;

/**
 * Created by Chris on 25/02/2016..
 */
public class UiMessage {
    private static UiMessage ourInstance = new UiMessage();

    public static UiMessage getInstance() {
        return ourInstance;
    }

    private UiMessage() {
    }

    public static void Print(String message) {
        System.out.println(message);
    }
}
