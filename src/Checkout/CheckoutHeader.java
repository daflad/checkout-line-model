/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Checkout;

import Customer.Customer;

/**
 * Class to hold information on checkout so customers can choose which queue
 * to join.
 *
 * @author  Stephen J Russell
 * @date    01-Nov-2012
 * @name    CheckoutHeader
 * @version 
 */
public class CheckoutHeader {
    
    private int totalItems;
    private int totalCustomers;
    private int itemsToBag;
    private boolean bagger;
    private boolean closing;

    /**
     * 
     */
    public CheckoutHeader() {
        this.totalItems = 0;
        this.totalCustomers = 0;
        this.itemsToBag = 0;
        this.bagger = false;
        this.closing = false;
    }
    
    /**
     * 
     * @param p
     * @return  
     */
    public int checkItemsToBag(PackingQueue p) {
        return this.itemsToBag = totalItems + p.getPackables().size();
    }
    
    /**
     * 
     * @param c
     */
    public void addCustomer(Customer c) {
        totalCustomers++;
        addItems(c);
    }
    
    private void addItems(Customer c) {
        setTotalItems(totalItems + c.getShoppingBasket().getNumItems());
    }
    
    /**
     * 
     * @param c
     */
    public void removeCustomer(Customer c) {        
        totalCustomers--;        
    }
    
    /**
     * 
     */
    public void removeItem() {
        totalItems--;
    }

    /**
     * 
     * @return
     */
    public boolean isClosing() {
        return closing;
    }

    /**
     * 
     * @param closing
     */
    public void setClosing(boolean closing) {
        this.closing = closing;
    }

    /**
     * 
     * @return
     */
    public boolean isBagger() {
        return bagger;
    }

    /**
     * 
     * @param bagger
     */
    public void setBagger(boolean bagger) {
        this.bagger = bagger;
    }

    /**
     * 
     * @return
     */
    public int getTotalCustomers() {
        return totalCustomers;
    }

    /**
     * 
     * @param totalCustomers
     */
    public void setTotalCustomers(int totalCustomers) {
        this.totalCustomers = totalCustomers;
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
    public int getItemsToBag() {
        return itemsToBag;
    }

    /**
     * 
     * @param itemsToBag
     */
    public void setItemsToBag(int itemsToBag) {
        this.itemsToBag = itemsToBag;
    }
}
