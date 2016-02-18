package com.gard.testbed.fsm;
import java.util.Map;

/**
 * Created by va922kg on 2/18/16.
 */
public interface IState extends  ICompletable{
    void onEnter( IStateProgress progress);
}
