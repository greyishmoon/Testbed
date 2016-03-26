package com.gard.testbed.activitySystem;

/**
 * Created by Chris on 16/03/2016..
 */
public interface IObservable {

    // Register and unregister observers
    public void register(IObserver observer);
    public void unregister(IObserver observer);

    // Notify observers of change
    public void notifyObservers();

    // Get updates from subject
//    public Object getUpdate(IObserver obj);

}
