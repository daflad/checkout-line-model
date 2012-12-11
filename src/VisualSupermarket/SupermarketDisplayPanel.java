/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package VisualSupermarket;

import Supermarket.MultiCheckout;
import Supermarket.Supermarket;
import VisualCheckout.VisualCheckout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.LinkedList;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 * Class defining the visual supermarket display.
 *
 * @author  Stephen J Russell
 * @date    01-Nov-2012
 * @name    SupermarketDisplayPanel
 * @version 
 */
class SupermarketDisplayPanel extends JPanel {

    private LinkedList<JPanel> checkouts;    
    private int numCheckouts;
    
    /**
     * Construct your supermarket.
     * @param s 
     */
    public SupermarketDisplayPanel(Supermarket s) {
        checkouts = new LinkedList<>();        
        this.numCheckouts = s.getCheckouts().getNumCheckouts();  
        setLayout(new GridLayout(1, numCheckouts));        
        makeCheckouts(s.getCheckouts());
    }
    
    /**
     * Make the required number of checkouts, and add them to the panel.
     * 
     * @param mCh 
     */
    private void makeCheckouts(MultiCheckout mCh) {
        for (int i = 0; i < numCheckouts; i++) {
            JPanel panel = new JPanel();
            panel.setBorder(new BevelBorder(BevelBorder.LOWERED));            
            panel.setLayout(new BorderLayout());
            panel.add(new VisualCheckout(400, mCh.getCheckout(i), 50, i + 1), BorderLayout.CENTER);
            checkouts.add(panel);
        }
        for (JPanel p : checkouts) {
            add(p);
        }
    }
}
