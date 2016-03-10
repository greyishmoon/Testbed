package com.gard.testbed.engine.petrinet.logic;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Chris on 01/03/2016.
 */
public class Place
        extends PetrinetObject {

    /** Sets no limits on number of tokens this place can hold - default setting */
    public static final int UNLIMITED = -1;

    private int tokens = 0;
    private int maxTokens = UNLIMITED;


    protected Place(String name) {
        super(name);
    }

    protected Place(String name, int initial) {
        this(name);
        this.tokens = initial;
    }

    /** Checks if place holds required number of tokens */
    public boolean hasAtLeastTokens(int threshold) {
        return (tokens >= threshold);
    }

    /** Manages maximum number of tokens */
    public boolean maxTokensReached(int newTokens) {
        if (hasUnlimitedMaxTokens()) {
            return false;
        }
        return (tokens + newTokens > maxTokens);
    }

    private boolean hasUnlimitedMaxTokens() {
        return maxTokens == UNLIMITED;
    }

    public int getTokens() {
        return tokens;
    }

    public void setTokens(int tokens) {
        this.tokens = tokens;
    }

    public void setMaxTokens(int max) {
        this.maxTokens = max;
    }

    public void addTokens(int weight) {
        this.tokens += weight;
    }

    public void removeTokens(int weight) {
        this.tokens -= weight;
    }


    @Override
    public String toString() {
        return super.toString() +
                " Tokens=" + this.tokens +
                " max=" + (hasUnlimitedMaxTokens() ? "unlimited" : this.maxTokens);
    }
}
