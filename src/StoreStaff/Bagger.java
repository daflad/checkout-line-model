/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package StoreStaff;

import Checkout.CheckoutHeader;
import Checkout.PackingQueue;
import java.util.Random;

/**
 * Class defining a supermarket bagger.
 * They have a bagging speed in number of items per minute, 20 to 40,
 * generated randomly on construction. 
 *
 * @author  Stephen J Russell
 * @date    01-Nov-2012
 * @name    Bagger
 * @version 
 */
public class Bagger {
    
    private Random gen;
    //number of items packed per minute.
    private int packingSpeed;

    /**
     * Construct your bagger.
     * 
     */
    public Bagger() {
        setupBagger();
    }

    /**
     * Setup a bagger with a random bagging speed.
     * 
     */
    private void setupBagger() {
        gen = new Random();
        setPackingSpeed(gen.nextInt(20) + 20);
    }
    
    /**
     * Action of packing bags for customer.
     * 
     * @param p
     * @param h  
     */
    public void packBags(PackingQueue p, CheckoutHeader h) {
        for (int i = 0; i < packingSpeed; i++) {
            //if no bags bagger can't pack!
            if (p.getPackables().size() != 0) {
                p.removeItem();
                h.removeItem();
            }
        }        
    }
    
    /**
     * 
     * @return
     */
    public int getPackingSpeed() {
        return packingSpeed;
    }

    /**
     * 
     * @param packingSpeed
     */
    public void setPackingSpeed(int packingSpeed) {
        this.packingSpeed = packingSpeed;
    }
    
}
