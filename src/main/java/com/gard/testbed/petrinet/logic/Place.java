package com.gard.testbed.petrinet.logic;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Chris on 01/03/2016.
 */
public class Place
        extends PetrinetObject {

    // it's a magic number....
    public static final int UNLIMITED = -1;

    private Petrinet parentPN;

//    private int tokens = 0;
    private LinkedList<Token> tokens = new LinkedList<>();
    private int maxTokens = UNLIMITED;


    protected Place(String name, Petrinet pn) {
        super(name);
        parentPN = pn;
    }

    protected Place(String name, int initial, Petrinet pn) {
        this(name, pn);
        parentPN = pn;
        // Add initial tokens
        addTokens(initial);
//        this.tokens = initial;
    }

    /**
     * Checks if place holds required number of tokens
     */
    public boolean hasAtLeastTokens(int threshold) {
        return (tokens.size() >= threshold);
    }

    /**
     * Manages maximum number of tokens
     */
    public boolean maxTokensReached(int newTokens) {
        if (hasUnlimitedMaxTokens()) {
            return false;
        }

        return (tokens.size() + newTokens > maxTokens);
    }

    private boolean hasUnlimitedMaxTokens() {
        return maxTokens == UNLIMITED;
    }


    public List<Token> getTokens() {
        return tokens;
    }

    public int getTokenCount() { return tokens.size(); }

    public void setTokens(int newTokens) {
        // Clear tokens list
        removeTokens(tokens.size());
        // Add new tokens
        addTokens(newTokens);

//        this.tokens = tokens;
    }

    public void setMaxTokens(int max) {
        this.maxTokens = max;
    }

    public void addTokens(int weight) {
        for (int i = 0; i < weight; i++) {
            tokens.add(parentPN.getActiveToken(this));
        }
//        this.tokens += weight;
        System.out.println("ADDTOKENS in: " + getName());
    }

    public void removeTokens(int weight) {
        parentPN.removeActiveToken(tokens.pop());
//        this.tokens -= weight;
        System.out.println("REMOVETOKENS in: " + getName());
    }


    @Override
    public String toString() {
        return super.toString() +
                " Tokens=" + this.getTokenCount() +
                " max=" + (hasUnlimitedMaxTokens() ? "unlimited" : this.maxTokens);
    }
}
