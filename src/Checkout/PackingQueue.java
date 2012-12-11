/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Checkout;

import Customer.Item;
import java.util.LinkedList;

/**
 * Class representing queue of supermarket items to be packed.
 *
 * @author  Stephen J Russell
 * @date    01-Nov-2012
 * @name    PackingQueue
 * @version 
 */
public class PackingQueue {
    
    private LinkedList<Item> queue;

    /**
     * 
     */
    public PackingQueue() {
        queue = new LinkedList();
    }
    
    /**
     * 
     * @param i
     */
    public void addItem(Item i){
        queue.add(i);
    }
    
    /**
     * 
     */
    public void removeItem() {
        queue.removeFirst();
    }

    /**
     * 
     * @return
     */
    public LinkedList<Item> getPackables() {
        return queue;
    }

    /**
     * 
     * @param stack
     */
    public void setPackables(LinkedList<Item> stack) {
        this.queue = stack;
    }
    
}
