/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package StoreStaff;

import Checkout.CheckoutHeader;
import Checkout.PackingQueue;
import Customer.Customer;
import Customer.ShoppingBasket;
import Supermarket.SupermarketStats;
import java.util.LinkedList;
import java.util.Random;

/**
 * Class defining a supermarket checker. A random checking speed of 20 - 30
 * items per minute is generate on construction along with a random payment
 * speed of either 0 or 1 representing the number of minutes taken to process
 * payment.
 *
 * @author Stephen J Russell @date 01-Nov-2012 @name Checker
 * @version
 */
public class Checker {

    private Random gen;
    //Number of items processed per minuite
    private int checkingSpeed;
    //Total number of items checked in shift
    private int itemsChecked;
    //Total number of customers served in shift
    private int cusomersChecked;

    /**
     *
     */
    public Checker() {
        this.itemsChecked = 0;
        this.cusomersChecked = 0;
        setupChecker();
    }

    //Setup checker with random checking speed & paymentSpeed
    private void setupChecker() {
        gen = new Random();
        setCheckingSpeed(gen.nextInt(20) + 10);
    }

    /**
     * Action of checking items by checker.
     *
     * @param queue
     * @param p
     * @param h 
     * @param ss  
     */
    public void checkItems(LinkedList<Customer> queue, PackingQueue p, CheckoutHeader h, SupermarketStats ss) {
        
        for (int i = 0; i < checkingSpeed && !queue.isEmpty(); i++) {
            Customer c = queue.getFirst();
            ShoppingBasket b = c.getShoppingBasket();
            if (!b.getBasket().isEmpty()) {
                p.addItem(b.getBasket().getFirst());
                b.removeItem();
                itemsChecked++;
            }
            if (b.getBasket().isEmpty() && p.getPackables().isEmpty()) {
                ss.addCustomer(c);
                queue.remove(c);
                h.removeCustomer(c);
            }
        }

    }

    /**
     *
     * @return
     */
    public int getCheckingSpeed() {
        return checkingSpeed;
    }

    /**
     *
     * @param checkingSpeed
     */
    public void setCheckingSpeed(int checkingSpeed) {
        this.checkingSpeed = checkingSpeed;
    }

    /**
     *
     * @return
     */
    public int getCusomersChecked() {
        return cusomersChecked;
    }

    /**
     *
     * @param cusomersChecked
     */
    public void setCusomersChecked(int cusomersChecked) {
        this.cusomersChecked = cusomersChecked;
    }

    /**
     *
     * @return
     */
    public int getItemsChecked() {
        return itemsChecked;
    }

    /**
     *
     * @param itemsChecked
     */
    public void setItemsChecked(int itemsChecked) {
        this.itemsChecked = itemsChecked;
    }
}
