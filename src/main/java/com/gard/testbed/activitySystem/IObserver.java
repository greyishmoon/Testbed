package com.gard.testbed.activitySystem;

/**
 * Created by Chris on 16/03/2016..
 */
public interface IObserver {

    // Update the observer - used by Observable subject
    public void update();

    //Attach with subject to observe
    public void setSubject(IObservable sub);

}
