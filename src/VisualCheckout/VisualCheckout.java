/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package VisualCheckout;

import Checkout.Checkout;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JComponent;

/**
 * Class defining a visual checkout.
 *
 * @author Stephen J Russell
 * @date 01-Nov-2012
 * @name VisualCheckout
 * @version
 */
public class VisualCheckout extends JComponent {

    private Checkout checkout;
    private int checkoutHeight, checkoutWidth, number;

    public VisualCheckout(int panelHeight, Checkout checkout, int checkoutWidth, int number) {
        this.number = number;
        this.checkoutHeight = panelHeight;
        this.checkoutWidth = checkoutWidth;
        this.checkout = checkout;
    }

    private RoundRectangle2D setupCounter(int xCor, int yCor) {
        int arch = 20;
        int x = xCor - (checkoutWidth / 2);
        int y = yCor - (checkoutHeight / 2);
        return new RoundRectangle2D.Double(x, y, checkoutWidth, checkoutHeight, arch, arch);
    }

    private Ellipse2D setupShopper(int xCor, int yCor) {
        int radius = 26;
        int x = xCor - (checkoutWidth / 2) - 27;
        int y = yCor + ((checkoutHeight / 2) - 30);
        return new Ellipse2D.Double(x, y, radius, radius);
    }

    private int[] shopperLabelCoords(int xCor, int yCor) {
        int[] xy = {xCor - (checkoutWidth / 2) - 27, yCor + ((checkoutHeight / 2) - 30)};
        return xy;
    }

    private Ellipse2D setupBagger(int xCor, int yCor) {
        int radius = 26;
        int x = xCor + (checkoutWidth / 2);
        int y = yCor + (checkoutHeight / 2) - 25;
        return new Ellipse2D.Double(x, y, radius, radius);
    }

    private Ellipse2D setupChecker(int xCor, int yCor) {
        int radius = 26;
        int x = xCor + (checkoutWidth / 2);
        int y = yCor + (checkoutHeight / 3);
        return new Ellipse2D.Double(x, y, radius, radius);
    }

    /**
     * Helper to set label coordinates.
     *
     * @param xCor
     * @param yCor
     * @return
     */
    private int[] baggerLabelCoords(int xCor, int yCor) {
        int[] xy = {xCor + (checkoutWidth / 2), yCor + (checkoutHeight / 2) - 25};
        return xy;
    }

    /**
     * Helper to set label coordinates.
     *
     * @param xCor
     * @param yCor
     * @return
     */
    private int[] checkerLabelCoords(int xCor, int yCor) {
        int[] xy = {xCor + (checkoutWidth / 2), yCor + (checkoutHeight / 3)};
        return xy;
    }

    /**
     * Helper to draw checkout.
     *
     * @param g2
     * @param xCor
     * @param yCor
     */
    private void drawCheckout(Graphics2D g2, int xCor, int yCor) {
        int spacer = 0;
        for (int i = 0; i < checkout.getQueue().size(); i++) {
            if (i == 0) {
                g2.setColor(Color.getHSBColor(37, 100, (float) 0.95));
            } else {
                g2.setColor(Color.getHSBColor(0, (float) 0.79, (float) 0.95));
            }
            g2.fill(setupShopper(xCor, yCor - spacer));
            if (i == 0) {
                g2.setColor(Color.BLACK);
            } else {
                g2.setColor(Color.WHITE);
            }
            String totalItems = String.format("%2d", checkout.getQueue().get(i).getShoppingBasket().getNumItems());
            int[] xy = shopperLabelCoords(xCor + 4, yCor + 18);
            g2.drawString(totalItems, xy[0], xy[1] - spacer);
            if (i == 0) {
                String itemsRemaining = String.format("%2d", checkout.getQueue().get(i).getShoppingBasket().getBasket().size());
                int[] x1y1 = shopperLabelCoords(xCor - 24, yCor + 18);
                g2.drawString(itemsRemaining, x1y1[0], x1y1[1] - spacer);
            }
            spacer += 27;
        }
    }

    /**
     * Helper to draw bagger.
     *
     * @param g2
     * @param xCor
     * @param yCor
     */
    private void drawBagger(Graphics2D g2, int xCor, int yCor) {
        if (checkout.getBagger() != null) {
            g2.setColor(Color.getHSBColor((float) Math.toRadians(128), (float) 0.95, (float) 0.78));
            g2.fill(setupBagger(xCor, yCor));
            g2.setColor(Color.WHITE);
            String s = String.format("%2d", checkout.getBagger().getPackingSpeed());
            int[] xy = baggerLabelCoords(xCor + 4, yCor + 18);
            g2.drawString(s, xy[0], xy[1]);

        }
    }

    /**
     * Helper to draw checker.
     *
     * @param g2
     * @param xCor
     * @param yCor
     */
    private void drawChecker(Graphics2D g2, int xCor, int yCor) {
        g2.setColor(Color.getHSBColor((float) Math.toRadians(150), (float) 0.5, (float) 0.98));
        g2.fill(setupChecker(xCor, yCor));
        g2.setColor(Color.WHITE);
        String s = String.format("%2d", checkout.getChecker().getCheckingSpeed());
        int[] xy = checkerLabelCoords(xCor + 4, yCor + 18);
        g2.drawString(s, xy[0], xy[1]);
    }

    /**
     * Helper for paintComponent.
     *
     * @param g2
     * @param xCor
     * @param yCor
     */
    private void draw(Graphics2D g2, int xCor, int yCor) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.getHSBColor((float) 0.595, (float) 0.206, (float) 0.827));
        g2.fill(setupCounter(xCor, yCor));
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Gill Sans", Font.BOLD, 14));
        g2.drawString("" + number, xCor - 5, yCor);
        drawCheckout(g2, xCor, yCor);
        drawBagger(g2, xCor, yCor);
        drawChecker(g2, xCor, yCor);
    }

    /**
     *
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        draw(g2, getWidth() / 2, getHeight() / 2);
    }

    /**
     *
     * @return
     */
    public int getCheckoutHeight() {
        return checkoutHeight;
    }

    /**
     *
     * @param checkoutHeight
     */
    public void setCheckoutHeight(int checkoutHeight) {
        this.checkoutHeight = checkoutHeight;
    }

    /**
     *
     * @return
     */
    public int getCheckoutWidth() {
        return checkoutWidth;
    }

    /**
     *
     * @param checkoutWidth
     */
    public void setCheckoutWidth(int checkoutWidth) {
        this.checkoutWidth = checkoutWidth;
    }
}
