/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package VisualSupermarket;

import Supermarket.Supermarket;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.plaf.basic.BasicBorders;
import javax.swing.plaf.basic.BasicBorders.ButtonBorder;
import javax.swing.plaf.metal.MetalBorders;

/**
 *
 *
 * @author Stephen J Russell @date 01-Nov-2012 @name SupermarketControlPanel
 * @version
 */
class SupermarketControlPanel extends JPanel {

    private static JTextField numCust;
    private static JTextField numHours;
    private static JButton addBagger;
    private static JTextField checkoutIndex;
    private static JButton removeBagger;
    private static JButton runSupermarket;

    public SupermarketControlPanel(Supermarket s, SupermarketDisplayPanel d,
            SupermarketStatsPanel ss, MultiCheckoutStatPanel sp) {

        setLayout(new GridLayout(3, 2));                
        setBorder(new BevelBorder(BevelBorder.LOWERED));
        SupermarketActionListener listen = new SupermarketActionListener(s, d, ss, sp);

        numCust = makeTextField("#");
        addBagger = makeButton("Add Bagger", listen);
        addBagger.setIcon(setupIcon("AddBagger.png"));
        numHours = makeTextField("#");
        //(Color.getHSBColor((float)Math.toRadians(128), (float) 0.95, (float) 0.78));       
        
        removeBagger = makeButton("Remove Bagger", listen);
        removeBagger.setIcon(setupIcon("DeleteBagger.png"));
        checkoutIndex = makeTextField("#");
        runSupermarket = makeButton("Run Supermarket", listen);
        runSupermarket.setIcon(setupIcon("shopping.png"));
        
        add(makePanel("Number of new customers per minute :", numCust, JLabel.CENTER));
        add(makePanel("Checkout Number", checkoutIndex, JLabel.CENTER));
        add(makePanel("Number of minutes to run supermarket for :", numHours, JLabel.CENTER));
        add(addBagger);                
        add(runSupermarket);        
        add(removeBagger); 
        
               
    }
    
    private ImageIcon setupIcon(String fileName) {
        //file path of icon
        String fp = "//Users/stephenjrussell/University/JAVA/Java/"
                + "SupermarketCheckoutModel/src/";
        //icon to overwrite default java icon
        ImageIcon i = new ImageIcon(fp + fileName);
        return i;
    }
    
    private JPanel makePanel(String message, JTextField tf, int alignment) {
        JPanel panel = new JPanel(new GridLayout(2, 1));        
        JLabel label = new JLabel(message);
        label.setHorizontalAlignment(alignment);
        tf.setHorizontalAlignment(alignment);
        panel.add(label);
        panel.add(tf); 
        return panel;
    }

    private JButton makeButton(String title, SupermarketActionListener listen) {
        JButton button = new JButton(title);
        button.addActionListener(listen);
        button.setFont(new Font("Gill Sans", Font.PLAIN, 14));
        return button;
    }

    private JTextField makeTextField(String str) {
        JTextField field = new JTextField(str);
        return field;
    }

    private static class SupermarketActionListener implements ActionListener {

        private Supermarket s;
        private SupermarketDisplayPanel d;
        private SupermarketStatsPanel ss;
        private MultiCheckoutStatPanel sp;
        private int numMins;

        public SupermarketActionListener(Supermarket s, SupermarketDisplayPanel d,
                SupermarketStatsPanel ss, MultiCheckoutStatPanel sp) {
            this.s = s;
            this.d = d;
            this.ss = ss;
            this.sp = sp;
            this.numMins = 1;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == addBagger) {
                if (isInputOk(checkoutIndex.getText())) {
                    s.addBagger(Integer.parseInt(checkoutIndex.getText()));
                } else {
                    s.addBagger();
                }
            } else if (e.getSource() == removeBagger) {
                if (isInputOk(checkoutIndex.getText())) {
                    s.removeBagger(Integer.parseInt(checkoutIndex.getText()));
                } else {
                    JOptionPane.showMessageDialog(d.getParent(), "please enter a checkout number");
                }
            } else if (e.getSource() == runSupermarket) {
                if (isInputOk(numCust.getText()) && isInputOk(numHours.getText())) {
                    for (int i = 0; i < Integer.parseInt(numHours.getText()); i++) {
                        for (int j = 0; j < Integer.parseInt(numCust.getText()); j++) {
                            waitNow(100);
                            s.addCustomer();
                            ss.setLabels(numMins, s.getCheckouts());
                            sp.updateLables();
                            d.paintImmediately(new Rectangle(d.getWidth(), d.getHeight()));
                            ss.paintImmediately(new Rectangle(ss.getWidth(), ss.getHeight()));
                            sp.paintImmediately(new Rectangle(sp.getWidth(), sp.getHeight()));
                        }
                        s.runSuperMarketForMin();
                        d.paintImmediately(new Rectangle(d.getWidth(), d.getHeight()));
                        ss.paintImmediately(new Rectangle(d.getWidth(), d.getHeight()));
                        ss.setLabels(numMins, s.getCheckouts());
                        sp.updateLables();
                        sp.paintImmediately(new Rectangle(sp.getWidth(), sp.getHeight()));
                        numMins++;
                        waitNow(100);
                    }
                } else {
                    JOptionPane.showMessageDialog(d.getParent(), "Please enter a number of new customers per minute "
                            + "& a number of minutes to run for");
                }
            }
            d.repaint();
        }

        private void waitNow(int t) {
            try {
                synchronized (this) {
                    wait(t);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(SupermarketControlPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        private boolean isInputOk(String text) {
            try {
                Integer.parseInt(text);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }
}
