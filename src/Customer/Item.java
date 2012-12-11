/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Customer;

/**
 * Class representing one supermarket item.
 *
 * @author  Stephen J Russell
 * @date    01-Nov-2012
 * @name    Item
 * @version 
 */
public class Item {
    
    private int itemNumber;

    /**
     * 
     * @param itemNumber
     */
    public Item(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    /**
     * 
     * @return
     */
    public int getItemNumber() {
        return itemNumber;
    }

    /**
     * 
     * @param itemNumber
     */
    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }
}
