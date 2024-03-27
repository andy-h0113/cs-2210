/*
 * CS 2210
 * Author: Andy Hwang | 251217976
 * This class represents a node of a binary search tree
 */

public class BNode {
    private Pel value;
    private BNode leftChild;
    private BNode rightChild;
    private BNode parent;

    /*
     * Constructor initalizes the class variables
     */
    public BNode(Pel value, BNode left, BNode right, BNode parent){
        this.value = value;
        this.leftChild = left;
        this.rightChild = right;
        this.parent = parent;
    }

    /*
     * Constructor that initializes a leaf node
     */
    public BNode(){
        this.value = null;
        this.leftChild = null;
        this.rightChild = null;
        this.parent = null;
    }

    /*
     * Getter function returns the parent node of a node
     */
    public BNode parent(){
        return parent;
    }

    /*
     * Setter function sets the parent node of the node object
     */
    public void setParent(BNode newParent){
        this.parent = newParent;
    }

    /*
     * Setter function sets the left child of the node object
     */
    public void setLeftChild(BNode p){
        this.leftChild = p;
    }

    /*
     * Setter function sets the right child of the node object
     */
    public void setRightChild(BNode p){
        this.rightChild = p;
    }

    /*
     * Setter functino stores the given Pel object in the node object
     */
    public void setContent(Pel value){
        this.value = value;
    }

    /*
     * Function checks whether this node object is a leaf
     * Returns true if it is a leaf
     * Returns false if it is not a leaf
     */
    public boolean isLeaf(){
        if ((this.leftChild == null) && (this.rightChild == null)) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * Getter function returns the Pel object stored in the node (value)
     */
    public Pel getData(){
        return this.value;
    }

    /*
     * Getter function returns the left child of the node
     */
    public BNode leftChild(){
        return leftChild;
    }

    /*
     * Getter function returns the right child of the node
     */
    public BNode rightChild(){
        return rightChild;
    }
}
