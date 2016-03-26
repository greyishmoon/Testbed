package com.gard.testbed.activitySystem;

import com.gard.testbed.engine.eventBus.EventBusFactory;
import com.gard.testbed.engine.eventBus.events.BlackboardEvent;
import com.gard.testbed.engine.eventBus.events.MessageType;

import java.util.HashMap;
import java.util.Map;

/**
 * Blackboard storing properties (facts) that represent occurrences in real world
 */

public class Subject implements IBlackboard {

    private String id;
    // Blackboard
    private Map<String, Integer> properties;

//    // Subscribed observers
//    private ArrayList<IObserver> observers = new ArrayList<>();

    public Subject(String id) {
        this.id = id;
        properties = new HashMap<>();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public int query(String key) {
        if (!properties.containsKey(key))
            return -1;
        return properties.get(key);
    }

    @Override
    public void set(String key, int value) {
        properties.put(key, value);
        System.out.println("Subject set - key: " + key + " Value: " + value);
//        // Notify observers Blackboard changed
        updated();
    }

    @Override
    public void updated() {
        EventBusFactory.getActivityBus().post(new BlackboardEvent(this.id, MessageType.UPDATED, "Blackboard state changed"));
    }


//    @Override
//    public void register(IObserver observer) {
//        if(observer == null) throw new NullPointerException("Null Observer");
//            if(!observers.contains(observer)) {
//                // Register new observer
//                observers.add(observer);
//                //
//                observer.setSubject(this);
//            }
//    }
//
//    @Override
//    public void unregister(IObserver observer) {
//        observers.remove(observer);
//    }
//
//    @Override
//    public void notifyObservers() {
//        observers.forEach(IObserver::update);
//    }

    @Override
    public String toString() {
        return "Subject{" +
                "id='" + id;
    }
}
