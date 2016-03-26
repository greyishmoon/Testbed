package com.gard.testbed.activitySystem;

/**
 * Blackboard storing properties (facts) that represent occurrences in real world - implemented by Subject
 */
//public interface IBlackboard extends IObservable{
public interface IBlackboard {

    // Returns blackboards ID
    String getId();

    // Sets a new property (representing an occurrence in real world
    void set(String key, int value);

    // Returns property value if present - if not present returns -1
    int query(String key);

    // Sends updated event to event bus notifying observers that blackboard has changed
    void updated();

}
