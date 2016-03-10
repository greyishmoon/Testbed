package com.gard.testbed.engine.petrinet.gui;



import com.gard.testbed.engine.petrinet.logic.Petrinet;
import com.gard.testbed.engine.petrinet.logic.Place;
import com.gard.testbed.engine.petrinet.logic.Transition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Chris on 01/03/2016.
 */
public class PetrinetGUI
        extends JFrame {

    public class PlaceLabel
            extends JLabel {

        private Place place;

        public PlaceLabel(Place p) {
            super(p.toString());
            this.place = p;
        }

        @Override
        public String getText() {
            if (place == null) {
                return null;
            }
            return place.toString();
        }

    }


    public class TransitionButton extends JButton {

        private Transition transition;

        public TransitionButton(final Transition t) {
            super(t.getName());
            this.transition = t;

            this.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    if (transition.canFire()) {
                        transition.fire();
                        System.out.println(pn);
                        fireStateChanged();
                    }
                }});

        }

        public boolean isEnabled() {
            if (transition == null) {
                return false;
            }
            return transition.canFire();
        }

        public String getText() {
            if (null==transition) {
                return null;
            }
            return transition.toString();
        }

    }


    Petrinet pn;

    public PetrinetGUI(Petrinet pn) {
        super(pn.getName());
        this.pn = pn;
//        this.setLayout(new FlowLayout());
        this.setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        for (Transition t : pn.getTransitions()) {
            TransitionButton button = new TransitionButton(t);
            button.setPreferredSize(new Dimension(100, 40));
            add(button);
            button.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    repaint();
                }});
        }
        for (Place p : pn.getPlaces()) {
            add(new PlaceLabel(p));
        }
    }



    public static void displayPetrinet(final Petrinet pn) {

        Runnable guiCreator = new Runnable() {
            public void run() {
                JFrame fenster = new PetrinetGUI(pn);

                // Swing directs program shutdown on window close
                fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // Displays the window
                fenster.setSize(500, 500);
                fenster.setVisible(true);
            }
        };

        // Ferry to the above source code in event dispatch thread from
        SwingUtilities.invokeLater(guiCreator);
    }
}

