package com.gard.testbed.petrinet.manager;

import com.gard.testbed.petrinet.logic.Petrinet;
import com.gard.testbed.petrinet.logic.ProgressToken;

import java.util.List;

/**
 * Created by Chris on 02/03/2016.
 */
public class PetriNetManager {

    private static Petrinet activityMap;        // Petri Net of activity
    List<ProgressToken> progressTokens;         // All tokens active in Places in net
    List<ProgressToken> tokenPool;              // Old tokens for recycling



}
