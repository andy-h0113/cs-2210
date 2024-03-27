/*
 * Class represents an edge
 * Author: Andy Hwang | 251217976
 * CS2210
 */

public class Edge {
    private Node firstNode;
    private Node secondNode;
    private String type;

    // Constructor
    Edge (Node u, Node v, String type) {
        firstNode = u;
        secondNode = v;
        this.type = type;
    }

    // returns the first endpoint of the edge
    Node firstNode() {
        return firstNode;
    }

    // returns the second endpoint of the edge
    Node secondNode() {
        return secondNode;
    }

    // returns the type of the edge
    String getType() {
        return type;
    }

}
