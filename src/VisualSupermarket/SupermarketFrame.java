/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package VisualSupermarket;

import Supermarket.Supermarket;
import Supermarket.SupermarketStats;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Class extending JFrame to hold supermarket panels,
 * will hold:
 *          §
 *
 * @author  Stephen J Russell
 * @date    01-Nov-2012
 * @name    SupermarketFrame
 * @version 
 */
public class SupermarketFrame extends JFrame {

    private final int FRAME_WIDTH = 1400;
    private final int FRAME_HEIGHT = 800;    
    
    /**
     * 
     * @param title
     * @throws HeadlessException
     */
    public SupermarketFrame(String title) throws HeadlessException {
        super(title);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        SupermarketStats ss = new SupermarketStats();
        Supermarket s = new Supermarket(8, ss);
        
        MultiCheckoutStatPanel sp = new MultiCheckoutStatPanel(s);
        SupermarketDisplayPanel d = new SupermarketDisplayPanel(s);
        SupermarketStatsPanel ssp = new SupermarketStatsPanel(ss, s, d, sp);             
        
        setJMenuBar(menuBar());
        
        add(sp, BorderLayout.NORTH);        
        add(d, BorderLayout.CENTER);
        add(ssp, BorderLayout.SOUTH);        
    }
    
    private JMenuBar menuBar() {
        JMenuBar mBar = new JMenuBar();        
        JMenu menu = new JMenu("Menu");
        JMenuItem item = new JMenuItem("Exit");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {System.exit(0);}
        });
        menu.add(item);
        mBar.add(menu);        
        return mBar;       
    }
}
