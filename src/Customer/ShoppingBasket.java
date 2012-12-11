/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Customer;

import java.util.LinkedList;
import java.util.Random;

/**
 * Class representing a supermarket shopping basket.
 * Baskets can contain upto 100 items and are given
 * a random number of items on construction.
 *
 * @author  Stephen J Russell
 * @date    01-Nov-2012
 * @name    ShoppingBasket
 * @version 
 */
public class ShoppingBasket {

    private LinkedList<Item> basket;
    private Random gen;
    private int numItems;

    /**
     * 
     */
    public ShoppingBasket() {
        this.basket = new LinkedList<>();
        setupBasket();
    }

    /**
     * 
     * @param item
     */
    public void addItem(Item item) {
        this.basket.add(item);
    }

    /**
     * 
     */
    public void removeItem() {
        this.basket.removeFirst();
    }

    //Setup basket with random number of items 0 - 250.
    private void setupBasket() {
        gen = new Random();         
        this.numItems = gen.nextInt(94) + 5;
        for (int i = 0; i < numItems; i++) {
            addItem(new Item(i));
        }
    }

    /**
     * 
     * @return
     */
    public int getNumItems() {
        return numItems;
    }

    /**
     * 
     * @param numItems
     */
    public void setNumItems(int numItems) {
        this.numItems = numItems;
    }

    /**
     * 
     * @return
     */
    public LinkedList<Item> getBasket() {
        return basket;
    }

    /**
     * 
     * @param basket
     */
    public void setBasket(LinkedList<Item> basket) {
        this.basket = basket;
    }   
}
