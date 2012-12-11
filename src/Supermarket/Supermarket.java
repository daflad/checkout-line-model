/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Supermarket;

import Customer.Customer;
import Customer.QueueingType;
import Customer.ShoppingBasket;
import StoreStaff.Bagger;

/**
 * Class defining a supermarket with Customers and Checkouts: you can add
 * customers one-by-one.
 *
 * @author Stephen J Russell @date 01-Nov-2012 @name Supermarket
 * @version
 */
public class Supermarket {

    private MultiCheckout checkouts;

    /**
     * Construct a new Supermarket with a given number of Checkouts and one
     * Bagger; We need at least one bagger in case customer cannot bag.
     *
     * @param numCheckouts
     * @param ss
     */
    public Supermarket(int numCheckouts, SupermarketStats ss) {
        checkouts = new MultiCheckout(numCheckouts, ss);
        checkouts.addBagger(0);

    }

    /**
     * Run the Supermarket for one minute.
     *
     */
    public void runSuperMarketForMin() {
        checkouts.runCheckoutsForMin();
    }

    /**
     * Run the Supermarket for a given number of minutes.
     *
     * @param numMins
     */
    public void runSuperMarketForMins(int numMins) {
        for (int i = 0; i < numMins; i++) {
            checkouts.runCheckoutsForMin();
        }

    }

    /**
     * Add a Customer who will choose their own queue.
     *
     */
    public void addCustomer() {
        checkouts.chooseCheckout(new Customer(new ShoppingBasket(), new QueueingType()));
    }

    /**
     * Add a number of customers to the supermarket, each choosing its own
     * queue.
     *
     * @param numCustomers
     */
    public void addCustomers(int numCustomers) {
        for (int i = 0; i < numCustomers; i++) {
            checkouts.chooseCheckout(new Customer(new ShoppingBasket(), new QueueingType()));
        }
    }

    /**
     * Add a bagger to a random queue.
     *
     */
    public void addBagger() {
        checkouts.addBagger(new Bagger());
    }

    /**
     * Add a bagger to a specified queue;
     *
     * @param i
     */
    public void addBagger(int i) {
        checkouts.addBagger(i - 1);
    }

    /**
     * 
     * @param i
     */
    public void removeBagger(int i) {
        checkouts.removeBagger(i - 1);
    }

    /**
     *
     * @return
     */
    public MultiCheckout getCheckouts() {
        return checkouts;
    }
}
