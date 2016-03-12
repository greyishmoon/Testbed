package com.gard.testbed.engine.petrinet;

/**
 * Created by Chris on 12/03/2016..
 */
public class PetrinetException extends Exception {

    public PetrinetException()
    {
    }

    public PetrinetException(String message) {
        super(message);
    }

    public PetrinetException(String message, Throwable cause) {
        super(message, cause);
    }

    public PetrinetException(Throwable cause) {
        super(cause);
    }

    public PetrinetException(String message, Throwable cause,
                           boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
