/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package VisualCheckout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author stephenjrussell
 */
public class EllipseComponent extends JPanel {

    private Color colour;
    private Color textColour;
    private int label;
    private Random gen;
    private Ellipse2D ellipse;

    public EllipseComponent(Color aColour, Color aTextColour) {
        setPreferredSize(new Dimension(30, 30));
        this.colour = aColour;
        this.textColour = aTextColour;
        gen = new Random();
        label = gen.nextInt(20) + 1;
        ellipse = new Ellipse2D.Double(0, 0, 20, 20);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(colour);
        g2.fill(ellipse);
        g2.setColor(textColour);
        g2.setFont(new Font("Gill Sans", Font.BOLD, 14));
        g2.drawString("" + String.format("%2d", label), 2, 15);
        
    }

    public Color getColour() {
        return colour;
    }

    public void setColour(Color colour) {
        this.colour = colour;
    }

    public Color getTextColour() {
        return textColour;
    }

    public void setTextColour(Color textColour) {
        this.textColour = textColour;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }
    
    
}
