/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package VisualSupermarket;

import Supermarket.Supermarket;
import VisualCheckout.CheckoutStatPanel;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 *
 * @author  Stephen J Russell
 * @date    07-Nov-2012
 * @name    MultiCheckoutStatPanel
 * @version 
 */
class MultiCheckoutStatPanel extends JPanel {
    
    ArrayList<CheckoutStatPanel> statPanels;

    public MultiCheckoutStatPanel(Supermarket s) {
        statPanels = new ArrayList<>();
        //setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        setLayout(new GridLayout(1, s.getCheckouts().getNumCheckouts(), 0, 0));
        setupPanels(s);
        for (CheckoutStatPanel e : statPanels) {
            add(e);
        }  
    }
    
    public void updateLables() {
        for (CheckoutStatPanel e : statPanels) {
            e.setCheckoutStats();
        }
    }

    private void setupPanels(Supermarket s) {
        for (int i = 0; i < s.getCheckouts().getNumCheckouts(); i++) {
            statPanels.add(new CheckoutStatPanel(s.getCheckouts().getCheckout(i)));            
        }        
    }
}
