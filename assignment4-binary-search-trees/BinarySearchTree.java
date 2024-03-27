/*
 * CS 2210
 * Author: Andy Hwang | 251217976
 * This class represents a binary search tree with nodes that stores objects of the type Pel
 */


public class BinarySearchTree implements BinarySearchTreeADT{
    private BNode root;
    
    public BinarySearchTree(){
        root = new BNode();
    }

    /*
	 * Returns the root of the binary search tree
	 */
	public BNode getRoot(){
        return root;
    }

	/*
	 * Returns the Pel object storing the given key, if the key is stored in
	 * the tree. Returns null otherwise.
	 */
	public Pel get(BNode r, Location key){
        BNode target = getNode(r, key);
        if (target.getData() == null){
            return null;
        }
        if (key.compareTo(target.getData().getLocus()) == 0){
            return target.getData();
        } else {
            return null;
        }
    }

    /*
     * Gets the node of the function given a key. 
     */
    private BNode getNode(BNode r, Location key){
        if (r.isLeaf()) {
            return r;
        } else {
            if (key.compareTo(r.getData().getLocus()) == 0){
                return r;
            } else if (key.compareTo(r.getData().getLocus()) == -1){
                return getNode(r.leftChild(), key);
            } else {
                return getNode(r.rightChild(), key);
            }
        }
    }

	/*
	 * Inserts the given data in the tree if no data item with the same key is
	 * already there. If a node already stores the same key, the algorithm
	 * throws a DuplicatedKeyException.
	 */
	public void put(BNode r, Pel data) throws DuplicatedKeyException{
        Pel p = get(r, data.getLocus());
        BNode newNode = getNode(r, data.getLocus());
        if (p == null){
            newNode.setContent(data);
            newNode.setLeftChild(new BNode());
            newNode.setRightChild(new BNode());
            newNode.leftChild().setParent(newNode);
            newNode.rightChild().setParent(newNode);
        } else {
            throw new DuplicatedKeyException("Duplicate Key");
        }
    }

	/*
	 * Removes the data item with the given key, if the key is stored in the
	 * tree. Throws an InexistentKeyException otherwise.
	 */
	public void remove(BNode r, Location key) throws InexistentKeyException{
        Pel p = get(r, key);
        if (p == null){
            throw new InexistentKeyException("Key does not exist");
        } else {
            removeNode(r, key);
        }
    }

    /*
     * Removes the node containing the data item with the given key.
     * SHOULD ONLY BE CALLED AFTER IT IS CONFIRMED THAT THE KEY EXISTS
     */
    private boolean removeNode(BNode r, Location key) {
        BNode nodeToRemove = getNode(r, key);
        if (nodeToRemove.isLeaf()) {
            return false;
        } else {
            BNode parentOfRemove = nodeToRemove.parent();
            // If the left child is a leaf
            if (nodeToRemove.leftChild().isLeaf()) {
                nodeToRemove.rightChild().setParent(nodeToRemove.parent());
                if (parentOfRemove != null) {
                    if (parentOfRemove.getData().getLocus().compareTo(key) == 1){
                        nodeToRemove.parent().setLeftChild(nodeToRemove.rightChild());
                    } else {
                        nodeToRemove.parent().setRightChild(nodeToRemove.rightChild());
                    }
                } else {
                    this.root = nodeToRemove.rightChild();
                }
            // If the right child is a leaf
            } else if (nodeToRemove.rightChild().isLeaf()){
                nodeToRemove.leftChild().setParent(nodeToRemove.parent());
                if (parentOfRemove != null) {
                    if (parentOfRemove.getData().getLocus().compareTo(key) == 1){
                        nodeToRemove.parent().setLeftChild(nodeToRemove.leftChild());
                    } else {
                        nodeToRemove.parent().setRightChild(nodeToRemove.leftChild());
                    }
                } else {
                    this.root = nodeToRemove.rightChild();
                }
            // If none of the children are leaves
            } else {
                BNode small = smallestNode(nodeToRemove.rightChild());
                nodeToRemove.setContent(small.getData());
                removeNode(small, key);
            }
        }
        return true;
    }

	/*
	 * Returns the Pel object with the smallest key larger than the given one (note
	 * that the tree does not need to store a node with the given key). Returns
	 * null if the given key has no successor.
	 */
	public Pel successor(BNode r, Location key){
        if (r.isLeaf()){
            return null;
        } else {
            BNode p = getNode(r, key);
            if ((p.isLeaf() == false) && p.rightChild().isLeaf() == false){
                return smallestNode(p.rightChild()).getData();
            } else {
                p = p.parent();
                while ((p != null) && (p.getData().getLocus().compareTo(key) == -1)){
                    p = p.parent();
                }
                if (p == null) {
                    return null;
                } else {
                    return p.getData();
                }
            }
        }
    }

	/*
	 * Returns the Pel object with the largest key smaller than the given one (note
	 * that the tree does not need to store a node with the given key). Returns
	 * null if the given key has no predecessor.
	 */
	public Pel predecessor(BNode r, Location key){
        if (r.isLeaf()){
            return null;
        } else {
            BNode p = getNode(r, key);
            if ((p.isLeaf() == false) && p.leftChild().isLeaf() == false){
                return largestNode(p.leftChild()).getData();
            } else {
                p = p.parent();
                while ((p != null) && (p.getData().getLocus().compareTo(key) == 1)){
                    p = p.parent();
                }
                if (p == null) {
                    return null;
                } else {
                    return p.getData();
                }
            }
        }
    }

	/*
	 * Returns the Pel object with the smallest key. Throws an EmptyTreeException if
	 * the tree is empty.
	 */
	public Pel smallest(BNode r) throws EmptyTreeException{
        BNode smallNode = smallestNode(r);
        if (smallNode == null){
            throw new EmptyTreeException("No data in tree");
        } else {
            return smallNode.getData();
        }
    }

    /*
     * Returns the node of the smallest node in that subtree
     */
    private BNode smallestNode(BNode r) {
        if (r.isLeaf()) {
            return null;
        } else {
            BNode p = r;
            while (p.isLeaf() != true){
                p = p.leftChild();
            }
            return p.parent();
        }
    }

	/*
	 * Returns the Pel object with the largest key. Throws an EmptyTreeException if
	 * the tree is empty.
	 */
	public Pel largest(BNode r) throws EmptyTreeException{
        BNode largeNode = largestNode(r);
        if (largeNode == null){
            throw new EmptyTreeException("No data in tree");
        } else {
            return largeNode.getData();
        }
    }

    /*
     * Returns the node of the largest node in that subtree
     */
    private BNode largestNode(BNode r) {
        if (r.isLeaf()) {
            return null;
        } else {
            BNode p = r;
            while (p.isLeaf() != true){
                p = p.rightChild();
            }
            return p.parent();
        }
    }
}
