/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Supermarket;

import Checkout.Checkout;
import Customer.Customer;
import Customer.QueueingType;
import StoreStaff.Bagger;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class representing multiple checkouts:
 *
 * @author Stephen J Russell
 * @date 01-Nov-2012
 * @name MultiCheckout
 * @version
 */
public class MultiCheckout {

    private ArrayList<Checkout> checkouts;
    private int numCheckouts;
    private int numCustomers;

    /**
     * Constructs the number of checkouts required.
     *
     * @param numCheckouts
     * @param ss
     */
    public MultiCheckout(int numCheckouts, SupermarketStats ss) {
        this.numCheckouts = numCheckouts;
        this.numCustomers = 0;
        checkouts = new ArrayList<>();
        setupCheckouts(ss);
    }

    /**
     * Setup all required checkouts.
     *
     */
    private void setupCheckouts(SupermarketStats ss) {
        for (int i = 0; i < numCheckouts; i++) {
            checkouts.add(new Checkout(ss));
        }
    }

    /**
     * Return specified checkout.
     *
     * @param i
     * @return
     */
    public Checkout getCheckout(int i) {
        return checkouts.get(i);
    }

    /**
     * Run all checkouts for one minute.
     *
     */
    public void runCheckoutsForMin() {
        for (Checkout e : checkouts) {
            e.runForMin();
        }
    }

    /**
     * Quicker access apposed to getList.getSize.
     *
     * @return
     */
    public int getNumCheckouts() {
        return numCheckouts;
    }

    /**
     * Returns the current number of customers.
     *
     * @return
     */
    public int getNumCustomers() {
        numCustomers = 0;
        for (Checkout e : checkouts) {
            numCustomers += e.getQueue().size();
        }
        return numCustomers;
    }

    /**
     * Add a bagger to a random queue.
     *
     * @param bagger
     */
    public void addBagger(Bagger bagger) {
        Random gen = new Random();
        boolean baggerAdded = false;
        int count = 0;
        while (!baggerAdded) {
            if (count == numCheckouts) {
                baggerAdded = true;
            }
            Checkout ch = getCheckout(gen.nextInt(numCheckouts));
            if (ch.getBagger() == null) {
                ch.setBagger(bagger);
                baggerAdded = true;
            }
            count++;
        }
    }

    /**
     * Add a bagger to a specified queue.
     *
     * @param checkoutIndex
     */
    public void addBagger(int checkoutIndex) {
        getCheckout(checkoutIndex).setBagger(new Bagger());
    }

    /**
     * Remove a bagger from a specified queue, making sure there isn't a
     * customer in the queue who needs help bagging.
     *
     * @param checkoutIndex
     */
    public void removeBagger(int checkoutIndex) {
        boolean baggerNeeded = false;
        Checkout ch = getCheckout(checkoutIndex);
        if (ch.getBagger() != null) {
            for (int i = 0; i < ch.getQueue().size(); i++) {
                if (ch.getQueue().get(i).isPackingHelp()) {
                    baggerNeeded = true;
                }
            }
            if (!baggerNeeded) {
                ch.setBagger(null);
            }
        }
    }

    /**
     * Logic for customer to choose a checkout If they need a bagger ignore
     * their QueueingType otherwise calculate QScore.
     *
     * @param c
     */
    public void chooseCheckout(Customer c) {
        int chosenIndex = 0;
        int queueScore = getQueueScore(getCheckout(0), c);
        for (int i = 0; i < numCheckouts; i++) {
            if (c.isPackingHelp()) {
                if (getCheckout(i).getBagger() != null) {
                    int a;
                    if (queueScore > (a = getQueueScore(getCheckout(i), c))) {
                        queueScore = a;
                        chosenIndex = i;
                    }
                }
            } else {
                int a;
                if (queueScore > (a = getQueueScore(getCheckout(i), c))) {
                    queueScore = getQueueScore(getCheckout(i), c);
                    chosenIndex = i;
                }
            }
        }
        getCheckout(chosenIndex).addCustomer(c);
    }

    /**
     * Customer queue score generator.
     */
    private int getQueueScore(Checkout ch, Customer c) {
        int queueScore;
        QueueingType q = c.getQueuingType();
        if (q.getQueuingtype() == 0) {
            queueScore = ch.getHeader().getTotalItems();
        } else {
            queueScore = ch.getHeader().getTotalCustomers();
        } 
        return queueScore;
    }
}
