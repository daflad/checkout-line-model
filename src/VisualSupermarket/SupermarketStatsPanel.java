/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package VisualSupermarket;

import VisualCheckout.EllipseComponent;
import Supermarket.MultiCheckout;
import Supermarket.Supermarket;
import Supermarket.SupermarketStats;
import java.awt.*;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 *
 *
 * @author Stephen J Russell @date 02-Nov-2012 @name SupermarketStatsPanel
 * @version
 */
class SupermarketStatsPanel extends JPanel {

    private JLabel totalCust, totalItems, custPerMin, itemsPerMin, numMinsTotal,
            titleA, titleB, liveTotalITem, liveTotalCust, liveItemsToBag;
    private SupermarketStats ss;    

    /**
     * Construct a statistics panel.
     * @param ss
     */
    public SupermarketStatsPanel(SupermarketStats ss, Supermarket s, 
            SupermarketDisplayPanel d, MultiCheckoutStatPanel sp) {
        this.ss = ss;           
        setSize(20, 50);
        setBorder(new BevelBorder(BevelBorder.LOWERED));
        setLayout(new BorderLayout());
        setupLabels();
        add(makePanel(), BorderLayout.WEST);
        add(addLabels(), BorderLayout.EAST);        
        add(new SupermarketControlPanel(s, d, this, sp), BorderLayout.CENTER);
           
    }

    /**
     * Set the labels with the current data.
     * 
     * @param numMins
     * @param mCh
     */
    public void setLabels(float numMins, MultiCheckout mCh) {
        if (!ss.getHappyCustomers().isEmpty()) {
            totalCust.setText("Customers\t" + ss.getHappyCustomers().size());
            totalItems.setText("Items\t" + ss.getItems());
            custPerMin.setText("Customers per min\t" + String.format("%.2f", (float) ss.getHappyCustomers().size() / numMins));
            itemsPerMin.setText("Items per min\t" + String.format("%.2f", (float) ss.getItems() / numMins));
            numMinsTotal.setText("Hours Open\t" + String.format("%.2f", numMins / 60));
            liveTotalITem.setText("Items Queueing\t" + ss.getTotalItems(ss.getheaders(mCh)));
            liveTotalCust.setText("Customers Queuing\t" + ss.getTotalCustomers(ss.getheaders(mCh)));
            liveItemsToBag.setText("Items to Bag\t" + ss.getTotalItemsToBag(ss.getPackingQueues(mCh)));
        }
    }

    /**
     * Construct a JLabel with specified message and alignment.
     * 
     * @param message
     * @param alignRight
     * @return 
     */
    private JLabel makeLabel(String message, boolean alignRight, Font f) {
        JLabel label = new JLabel(message);  
        label.setFont(f);
        if (alignRight) {
            label.setHorizontalAlignment(JLabel.RIGHT);            
        } else {
            label.setHorizontalAlignment(JLabel.LEFT);
        }
        return label;
    }

    /**
     * Helper to make the labels.
     * 
     */
    private void setupLabels() {
        Font f2 = new Font("Gill Sans", Font.BOLD, 18);
        Font f1 = new Font("Gill Sans", Font.PLAIN, 14);
        titleA = makeLabel("Supermarket\tStats", true, f2);   
        totalCust = makeLabel("Customers\t#", true, f1);
        custPerMin = makeLabel("Customers per min\t#", true, f1);
        totalItems = makeLabel("Items\t#", true, f1);
        itemsPerMin = makeLabel("Items per min\t#", true, f1);
        numMinsTotal = makeLabel("Hours Open\t#", true, f1);
        titleB = makeLabel("Live\tStats", true, f2);        
        liveTotalITem = makeLabel("Items queuing\t#", true, f1);
        liveTotalCust = makeLabel("Customers queuing\t#", true, f1);
        liveItemsToBag = makeLabel("Items to bag\t#", true, f1);
    }

    /**
     * Helper to add labels.
     * 
     */
    private JPanel addLabels() {
        JPanel panel = new JPanel();
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);        
        panel.setLayout(layout);        
        panel.setBackground(Color.WHITE);          
        panel.add(makeRowPanel(titleA, titleB));                        
        panel.add(makeRowPanel(totalCust, liveTotalITem));        
        panel.add(makeRowPanel(custPerMin, liveTotalCust));        
        panel.add(makeRowPanel(totalItems, liveItemsToBag));
        panel.add(makeRowPanel(itemsPerMin, makeLabel("", true, new Font("", 0, 0)))); 
        panel.add(makeRowPanel(numMinsTotal, makeLabel("", true, new Font("", 0, 0)))); 
        JPanel newPan = new JPanel();
        newPan.setBackground(Color.WHITE);          
        newPan.setBorder(new BevelBorder(BevelBorder.LOWERED));
        newPan.add(panel);
        return newPan;
    }
    
    private JPanel makePanel() {
        JPanel panel = new JPanel(new GridLayout(0, 1));        
        panel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        panel.setBackground(Color.WHITE);
        panel.setAlignmentX(JPanel.RIGHT_ALIGNMENT);
        JLabel label = makeLabel("Supermarket Key", false, new Font("Gill Sans", Font.BOLD, 18));        
        label.setHorizontalAlignment(JLabel.CENTER);
        panel.add(label);  
        panel.add(addPanel(Color.getHSBColor(0, (float) 0.79, (float) 0.95), Color.WHITE, "Customer (Items)"));
        panel.add(addPanel(Color.getHSBColor(37, 100, (float) 0.95), Color.BLACK, "Current Customer"));
        panel.add(addPanel(Color.getHSBColor((float)Math.toRadians(150), (float) 0.5, (float) 0.98), Color.WHITE, "Checker (Items / Min)"));
        panel.add(addPanel(Color.getHSBColor((float)Math.toRadians(128), (float) 0.95, (float) 0.78), Color.WHITE, "Bagger (Items / Min)"));
        return panel;
    }
        
    private JPanel makeRowPanel(JLabel columnOne, JLabel columnTwo) {
        JPanel rowPanel = new JPanel(new GridLayout(1, 2));        
        rowPanel.setBackground(Color.WHITE);                 
        rowPanel.add(columnOne);
        rowPanel.add(columnTwo);        
        return rowPanel;
    }
    
    private JPanel addPanel(Color dotColour, Color textColour, String message) {
        JPanel panel = new JPanel();        
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT));        
        panel.setBackground(Color.WHITE);
        panel.add(makeLabel(message, true, new Font("Gill Sans", Font.PLAIN, 14)));
        panel.add(new EllipseComponent(dotColour, textColour));        
        return panel;
    }
}
