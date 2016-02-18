package com.gard.testbed.fsm;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by va922kg on 2/18/16.
 */
public class CompositeState implements IState {
    private final ArrayList<IState> childs;

    CompositeState(ArrayList<IState> childs){
        this.childs = childs;
    }
    @Override
    public boolean isComplete(IStateProgress progress) {
        for (IState state:childs) {
            if(!state.isComplete(progress))
                return false;
        }
        return true;
    }

    @Override
    public void onEnter(IStateProgress progress) {

    }
}
