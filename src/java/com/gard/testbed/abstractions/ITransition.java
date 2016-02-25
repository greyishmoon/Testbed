package com.gard.testbed.abstractions;

import java.util.List;

/**
 * Created by Chris on 23/02/2016..
 */
public interface ITransition extends ICompletable{

    IState source();
    IState destination();

}
