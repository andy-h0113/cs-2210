/*
 * Record represents an object that records game states
 * 
 * @author Andy Hwang || 251217976
 */

public class Record {
    private String key;     //stores key for the class
    private int score;      //stores the score
    private int level;      //stores the level of the game

    /*
     * Constructor that assigns values based on input
     */
    public Record(String key, int score, int level) {
        this.key = key;
        this.score = score;
        this.level = level;
    }

    /*
     * Getter function that returns the key of the Record object
     */
    public String getKey() {
        return key;
    }

    /*
     * Getter function that returns the integer stored in the score variable
     */
    public int getScore() {
        return score;
    }

    /*
     * Getter function that returns the integer stored in the level variable
     */
    public int getLevel() {
        return level;
    }
}
