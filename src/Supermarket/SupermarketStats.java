/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Supermarket;

import Checkout.CheckoutHeader;
import Checkout.PackingQueue;
import Customer.Customer;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Class collecting supermarket statistics:
 *
 *      Each customer is added to the list when they checkout.
 *
 * @author  Stephen J Russell
 * @date    02-Nov-2012
 * @name    SupermarketStats
 * @version 
 */
public class SupermarketStats {
    
    private LinkedList<Customer> happyCustomers;    

    /**
     * 
     */
    public SupermarketStats() {
        this.happyCustomers = new LinkedList<>();       
    }
    
    /**
     * 
     * @param c
     */
    public void addCustomer(Customer c) {
        happyCustomers.add(c);
    }
    
    /**
     * 
     */
    public void removeCustomer() {
        happyCustomers.remove();
    }

    /**
     * 
     * @return
     */
    public LinkedList<Customer> getHappyCustomers() {
        return happyCustomers;
    }
    
    /**
     * Retrieve all customer headers in list.
     * 
     * @param mCh
     * @return
     */
    public ArrayList<CheckoutHeader> getheaders(MultiCheckout mCh) {
        ArrayList<CheckoutHeader> checkHeads = new ArrayList<>();
        for (int i = 0; i < mCh.getNumCheckouts(); i++) {
            checkHeads.add(mCh.getCheckout(i).getHeader());            
        }
        return checkHeads;
    }
    
    /**
     * Retrieve all customer packing queues in list.
     * 
     * @param mCh
     * @return
     */
    public ArrayList<PackingQueue> getPackingQueues(MultiCheckout mCh) {
        ArrayList<PackingQueue> checkQueues = new ArrayList<>();
        for (int i = 0; i < mCh.getNumCheckouts(); i++) {
            checkQueues.add(mCh.getCheckout(i).getPackingQueue());            
        }
        return checkQueues;
    }
    
    /**
     * 
     * @param checkQueues
     * @return
     */
    public int getTotalItemsToBag(ArrayList<PackingQueue> checkQueues) {
        int items = 0;
        for (PackingQueue pq : checkQueues) {
            items += pq.getPackables().size();
        }
        return items;
    }
    
    /**
     * 
     * @param checkHeads
     * @return
     */
    public int getTotalCustomers(ArrayList<CheckoutHeader> checkHeads) {
        int customers = 0;
        for (CheckoutHeader ch : checkHeads) {
            customers += ch.getTotalCustomers();
        }
        return customers;
    }
    
    /**
     * 
     * @param checkHeads
     * @return
     */
    public int getTotalItems(ArrayList<CheckoutHeader> checkHeads) {
        int items = 0;
        for (CheckoutHeader ch : checkHeads) {
            items += ch.getTotalItems();
        }
        return items;
    }
    
    /**
     * 
     * @return
     */
    public int getItems() {
        int items = 0;
        for (Customer c : happyCustomers) {
            items += c.getShoppingBasket().getNumItems();
        }
        return items;
    }
}
