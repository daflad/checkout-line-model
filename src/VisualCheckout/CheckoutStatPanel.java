/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package VisualCheckout;

import Checkout.Checkout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 *
 *
 * @author Stephen J Russell @date 07-Nov-2012 @name CheckoutStatPanel
 * @version
 */
public class CheckoutStatPanel extends JPanel {

    private JLabel liveItems;
    private JLabel liveCust;
    private JLabel livePackItems;
    private Checkout ch;

    /**
     * Construct a checkout statistic panel.
     * 
     * @param ch
     */
    public CheckoutStatPanel(Checkout ch) {
        this.ch = ch;
        setLayout(new GridLayout(0, 2));
        setBorder(new BevelBorder(BevelBorder.LOWERED));
        setBackground(Color.getHSBColor((float)0.595,(float)0.206,(float)0.827));
        liveItems = makeLabel("\t#", JLabel.LEFT);
        liveCust = makeLabel("\t#", JLabel.LEFT);
        livePackItems = makeLabel("\t#", JLabel.LEFT);
        add(makeLabel("Items",  JLabel.RIGHT));
        add(liveItems);
        add(makeLabel("Customers", JLabel.RIGHT));
        add(liveCust);
        add(makeLabel("Packables", JLabel.RIGHT));
        add(livePackItems);
    }
    
    /**
     * Make labels and set colour of text & Font.
     * 
     * @param s
     * @param a
     * @return 
     */
    private JLabel makeLabel(String s, int a) {
        JLabel label = new JLabel(s, a);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Gill Sans", Font.BOLD, 14));
        return label;
    }

    /**
     * Update checkout statistic labels.
     * 
     */
    public void setCheckoutStats() {
        liveItems.setText("\t" + ch.getHeader().getTotalItems());
        liveCust.setText("\t" + ch.getHeader().getTotalCustomers());
        livePackItems.setText("\t" + ch.getPackingQueue().getPackables().size());
    }
}
