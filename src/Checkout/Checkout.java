/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Checkout;

import Customer.Customer;
import StoreStaff.Bagger;
import StoreStaff.Checker;
import Supermarket.SupermarketStats;
import java.util.LinkedList;

/**
 * Class representing a checkout line at a supermarket.
 *
 * @author  Stephen J Russell
 * @date    01-Nov-2012
 * @name    Checkout
 * @version 
 */
public class Checkout {

    private Checker checker;
    private LinkedList<Customer> queue;
    private PackingQueue packingQueue;
    private Bagger bagger;
    private CheckoutHeader header; 
    private SupermarketStats ss;

    /**
     * Checkout with:
     * Checker,
     * Queue,
     * Bagger,
     * Header.
     * @param ss 
     */
    public Checkout(SupermarketStats ss) {
        checker = new Checker();
        queue = new LinkedList();
        packingQueue = new PackingQueue();
        bagger = null;
        header = new CheckoutHeader();
        this.ss = ss;
    }
    
    /**
     * Run Checkout for one min.
     */
    public void runForMin() {
        if (queue.size() != 0) {
            checker.checkItems(queue, packingQueue, header, ss);
            if (bagger != null) {
                bagger.packBags(packingQueue, header);
            }
            if (!packingQueue.getPackables().isEmpty()) {
                queue.getFirst().packBags(packingQueue, header);
            }            
        }
    }
    
    /**
     * 
     * @param c
     */
    public void addCustomer(Customer c) {
        queue.add(c);
        header.addCustomer(c);
    }

    /**
     * 
     * @return
     */
    public Bagger getBagger() {
        return bagger;
    }

    /**
     * 
     * @param bagger
     */
    public void setBagger(Bagger bagger) {
        this.bagger = bagger;
    }

    /**
     * 
     * @return
     */
    public Checker getChecker() {
        return checker;
    }

    /**
     * 
     * @param checker
     */
    public void setChecker(Checker checker) {
        this.checker = checker;
    }

    /**
     * 
     * @return
     */
    public CheckoutHeader getHeader() {
        return header;
    }

    /**
     * 
     * @param header
     */
    public void setHeader(CheckoutHeader header) {
        this.header = header;
    }

    /**
     * 
     * @return
     */
    public LinkedList<Customer> getQueue() {
        return queue;
    }

    /**
     * 
     * @param queue
     */
    public void setQueue(LinkedList<Customer> queue) {
        this.queue = queue;
    }

    /**
     * 
     * @return
     */
    public PackingQueue getPackingQueue() {
        return packingQueue;
    }

    /**
     * 
     * @param packingQueue
     */
    public void setPackingQueue(PackingQueue packingQueue) {
        this.packingQueue = packingQueue;
    }
    
}
