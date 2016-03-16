package com.gard.testbed.activitySystem;

import java.util.List;

public interface IState {
    String getId();

    boolean isFinal();

    void add(ITransition transition);

    List<ITransition> getTransitions();
}