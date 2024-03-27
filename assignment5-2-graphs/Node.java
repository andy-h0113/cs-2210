/*
 * Class represents a node
 * Author: Andy Hwang | 251217976
 * CS2210
 */

public class Node {
    private int id;
    private boolean mark;

    // Constructor
    Node (int id) {
        this.id = id;
    }

    // Marks node to check whether it's been checked or not
    void markNode (boolean mark) {
        this.mark = mark;
    }

    // Returns the mark of the node
    boolean getMark() {
        return mark;
    }

    // Returns the id of the node
    int getId() {
        return id;
    }

}
