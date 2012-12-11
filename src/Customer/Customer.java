/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Customer;

import Checkout.CheckoutHeader;
import Checkout.PackingQueue;
import java.util.Random;

/**
 * Class defining customers with:
 *      §ShoppingBasket
 *      §QueueingType
 *      §PackingHelp
 *      §PaclkingSpeed
 *      §TotalItems
 *
 * @author  Stephen J Russell
 * @date    01-Nov-2012
 * @name    Customer
 * @version 
 */
public class Customer {
    private Random gen;
    private ShoppingBasket shoppingBasket;
    private QueueingType queuingType;
    private boolean packingHelp;
    private int packingSpeed;
    private int totalItems;

    /**
     * Supermarket Customer with:
     * @param shoppingBasket
     * @param queuingType 
     * packingSpeed
     * packingHelp
     * totalItems
     *  
     */
    public Customer(ShoppingBasket shoppingBasket, QueueingType queuingType) {
        this.shoppingBasket = shoppingBasket;
        this.totalItems = shoppingBasket.getBasket().size();
        this.queuingType = queuingType;
        setupPackingSpeedAndHelp();
    }
    
    /**
     * Give the customer a random packing speed, 
     * if the packing speed is below 7 they need help.
     * 
     */
    private void setupPackingSpeedAndHelp() {
        gen = new Random();
        setPackingSpeed(gen.nextInt(20) + 5);
        //If slow packers then need Bagger.
        if (packingSpeed < 7) {
            setPackingHelp(true);
        } else {
            setPackingHelp(false);
        }
    }

    /**
     * Action of packing bags by customer.
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
    public int getTotalItems() {
        return totalItems;
    }

    /**
     * 
     * @param totalItems
     */
    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }
    
    /**
     * 
     * @return
     */
    public boolean isPackingHelp() {
        return packingHelp;
    }

    /**
     * 
     * @param packingHelp
     */
    public void setPackingHelp(boolean packingHelp) {
        this.packingHelp = packingHelp;
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

    /**
     * 
     * @return
     */
    public ShoppingBasket getShoppingBasket() {
        return shoppingBasket;
    }

    /**
     * 
     * @param shoppingBasket
     */
    public void setShoppingBasket(ShoppingBasket shoppingBasket) {
        this.shoppingBasket = shoppingBasket;
    }

    /**
     * 
     * @return
     */
    public QueueingType getQueuingType() {
        return queuingType;
    }

    /**
     * 
     * @param queuingType
     */
    public void setQueuingType(QueueingType queuingType) {
        this.queuingType = queuingType;
    }
    
    
}
