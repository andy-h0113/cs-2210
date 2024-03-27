/*
 * CS 2210
 * Author: Andy Hwang | 251217976
 * This class represents the a Pel object holding the objects color and coordinates
 */

public class Pel {
    private Location p;
    private int color;

    /*
     * Constructor initializes the new object with the defined coordinates and color
     */
    public Pel(Location p, int color){
        this.p = p;
        this.color = color;
    }

    /*
     * Getter function returns the location of the object's locus
     */
    public Location getLocus(){
        return p;
    }

    /*
     * Getter function returns the object's color
     */
    public int getColor(){
        return color;
    }
}
