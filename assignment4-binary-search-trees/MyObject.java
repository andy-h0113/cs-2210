/*
 * CS 2210
 * Author: Andy Hwang | 251217976
 * This class represents an object in whose width, height, and locus are stored.
 */

public class MyObject implements MyObjectADT{
    
    private int id;
    private int width;
    private int height;
    private String type;
    private Location pos;
    private BinarySearchTree tree;

    /*
     * Constructor that initializes the values of this MyObject
     */
    public MyObject (int id, int width, int height, String type, Location pos){
        this.id = id;
        this.width = width;
        this.height = height;
        this.type = type;
        this.pos = pos;
        tree = new BinarySearchTree();
    }

    /*
     * Function sets the type of this Myobject to the specified value
     */
    public void setType (String type) {
        this.type = type;
    }

    /*
     * Getter function returns the width of this MyObject rectangle
     */
    public int getWidth() {
        return this.width;
    }

    /*
     * Getter function returns the height of this MyObject rectangle
     */
    public int getHeight(){
        return this.height;
    }

    /*
     * Getter function returns the type of this MyObject
     */
    public String getType(){
        return this.type;
    }

    /*
     * Getter function returns the id of this MyObject
     */
    public int getId(){
        return this.id;
    }

    /*
     * Getter function returns the locus of the node
     */
    public Location getLocus(){
        return this.pos;
    }

    /*
     * Setter function sets the location the specified value
     */
    public void setLocus(Location value) {
        this.pos = value;
    }

    /*
     * Inserts a Pel object into the BST associated with this MyObject
     */
    public void addPel(Pel pix) throws DuplicatedKeyException{
        try {
            tree.put(tree.getRoot(), pix);
        } catch (Exception e) {
            throw new DuplicatedKeyException("Object with key already exists");
        }
    }

    /*
     * Returns true if this figure intersects the one specified in the
	 * parameter; it returns false otherwise.
     */
    public boolean intersects(MyObject fig) {
        boolean horizontalCheck = checkX(fig);
        boolean verticalCheck = checkY(fig);
        
        // If statement checks if there is overlap of rectangles
        if (horizontalCheck && verticalCheck) {
            // Enclosed in try catch due to smallest function
            try {
                Pel thisPosition = this.tree.smallest(this.tree.getRoot());
                Pel primePosition = fig.tree.smallest(fig.tree.getRoot());

                // Loops through each object in the fig tree once for each object in this tree to confirm where there are overlaps
                while (thisPosition != null) {
                    while (primePosition != null) {
                        int testX = thisPosition.getLocus().getx() + this.getLocus().getx() - fig.getLocus().getx();
                        int testY = thisPosition.getLocus().gety() + this.getLocus().gety() - fig.getLocus().gety();

                        if ((primePosition.getLocus().getx() == testX) && (primePosition.getLocus().gety() == testY)){
                            return true;
                        }
                        primePosition = fig.tree.successor(fig.tree.getRoot(), primePosition.getLocus());
                    }
                    thisPosition = this.tree.successor(this.tree.getRoot(), thisPosition.getLocus());
                }
            } catch (Exception e) {
                System.out.println("Empty Tree Error");
            }
        } else {
            return false;
        }
        return false;
    }

    /*
     * Checks if there is horizontal overlap of the enclosing rectangles
     * returns true if there is overlap
     * returns false for no overlap
     */
    private boolean checkX(MyObject fig) {
        int thisX = this.getLocus().getx();
        int thisW = this.getWidth();
        int primeX = fig.getLocus().getx();
        int primeW = fig.getWidth();

        if ((primeX <= thisX) && ((primeX + primeW) >= thisX)){
            return true;
        } else if ((primeX <= (thisX + thisW)) && ((primeX + primeW) >= (thisX + thisW))){
            return true;
        } else {
            return false;
        }
    }

    /*
     * Checks if there is vertical overlap of the enclosing rectangles
     * returns true if there is overlap
     * returns false for no overlap
     */
    private boolean checkY(MyObject fig) {
        int thisY = this.getLocus().gety();
        int thisH = this.getHeight();
        int primeY = fig.getLocus().gety();
        int primeH = fig.getHeight();

        if ((primeY <= thisY) && ((primeY + primeH) >= thisY)){
            return true;
        } else if ((primeY <= (thisY + thisH)) && ((primeY + primeH) >= (thisY + thisH))){
            return true;
        } else {
            return false;
        }
    }
}
