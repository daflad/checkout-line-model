/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Customer;

import java.util.Random;

/**
 * Class defining a customer queueing type.
 *
 * @author Stephen J Russell @date 01-Nov-2012 @name QueueingType
 * @version
 */
public class QueueingType {

    private int queuingtype;
    private Random gen;

    /**
     * Construct a queueing type.
     */
    public QueueingType() {
        setupQueuingType();
    }

    private void setupQueuingType() {
        gen = new Random();
        setQueuingtype(gen.nextInt(1));
    }

    /**
     * 
     * @return
     */
    public int getQueuingtype() {
        return queuingtype;
    }

    /**
     * 
     * @param queuingtype
     */
    public void setQueuingtype(int queuingtype) {
        this.queuingtype = queuingtype;
    }
}
