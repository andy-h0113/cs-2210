/*
 * LinearNode implements a node to create a linked list that contain elements of the class Record
 * 
 * Author: Andy Hwang || 251217976
 */

public class LinearNode {
    
    private LinearNode next;
    private Record element;

    /*
     * Creates an empty node
     */
    public LinearNode() {
        next = null;
        element = null;
    }

    /*
     * Creates a node storing an element
     */
    public LinearNode(Record elem) {
        next = null;
        element = elem;
    }

    /*
     * Returns node that follows the current one
     */
    public LinearNode getNext(){
        return next;
    }

    /*
     * Sets the node that follows the current node
     */
    public void setNext(LinearNode node){
        next = node;
    }

    /*
     * Returns element stored in node
     */
    public Record getElement(){
        return element;
    }

    /*
     * Sets the element of the node to the Record object
     */
    public void setElement(Record elem){
        element = elem;
    }
}
