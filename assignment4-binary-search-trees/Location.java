/*
 * CS 2210
 * Author: Andy Hwang | 251217976
 * This class represents the location (coordinates (x, y)) of a pel
 */

public class Location {
    private int x;
    private int y;

    /*
     * Constructor assigns two integer values that represent the coordinates
     */
    public Location(int x, int y){
        this.x = x;
        this.y = y;
    }

    /*
     * Getter function returns the x-coordinate (x)
     */
    public int getx(){
        return x;
    }

    /*
     * Getter function returns the y-coordinate (y)
     */
    public int gety(){
        return y;
    }

    /*
     * Receives another Location object as a parameter.
     * Returns 1 if this object is greater than the other object
     * Returns 0 if this object is equal to the other object
     * Returns -1 if this object is less than the other object
     */
    public int compareTo(Location p){
        if ((this.gety() > p.gety()) || ((this.gety() == p.gety()) && (this.getx() > p.getx()))){
            return 1;
        } else if ((this.getx() == p.getx()) && (this.gety() == p.gety())){
            return 0;
        } else {
            return -1;
        }
    }
}
