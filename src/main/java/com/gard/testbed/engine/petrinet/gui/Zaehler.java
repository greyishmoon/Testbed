package com.gard.testbed.engine.petrinet.gui;

import com.gard.testbed.engine.petrinet.logic.Arc;
import com.gard.testbed.engine.petrinet.logic.Petrinet;
import com.gard.testbed.engine.petrinet.logic.Place;
import com.gard.testbed.engine.petrinet.logic.Transition;

public class Zaehler {

    
    /**
     * Z�hlt einmal von 0 bis 7.
     * 
     * jeweilige (Z�hler-) Stelle wird markiert
     * 
     * @param args
     */
    
    public static void main(String[] args) {
        
        Petrinet pn = new Petrinet("Z�hler");
        
        Place vorgaenger = null;
        
        Transition t0 = null;
        
        for (int i = 0; i < 8 ; i++) {
            Transition t = pn.transition("Transition #" + i);
            if (null != vorgaenger) {
                Arc a = pn.arc("Arc", vorgaenger, t);
            }
            if (0 == i) {
                t0 = t;
            }
            
            Place p = pn.place("Place #" + i);
            p.setMaxTokens(1);
            Arc a = pn.arc("Arc", t, p);
            pn.inhibitor("inhibitor", p, t0);

            vorgaenger = p;
        }
        
        PetrinetGUI.displayPetrinet(pn);
    }
}
