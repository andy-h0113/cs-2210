/*
 * Dictionary implements a dictionary using a hash table to store objects of class Record
 * 
 * @author Andy Hwang || 251217976
 */

public class Dictionary implements DictionaryADT{

    private int recordCount;
    private LinearNode[] hashMap;
    private int hashSize;
    
    /*
     * Contructor for the dictionary class
     */
    public Dictionary(int size){
        hashMap = new LinearNode[size];
        hashSize = size;
        recordCount = 0;
    }
    
    /*
     * Function inserts the Record object into the dictionary
     * Returns 1 if there is a collision and returns 0 if there is no collision
     */
    public int put(Record rec) throws DuplicatedKeyException{
        int hashKey = hashCode(rec.getKey());
        LinearNode newNode = new LinearNode(rec);
        LinearNode currentNode = hashMap[hashKey];
        
        //Inserts LinearNode object containg Record object into the list
        if (currentNode == null){
            hashMap[hashKey] = newNode;
            recordCount += 1;
            return 0;
        // Collision cases
        } else {
            // Traverses through the linked list checking for the end or a duplicate key
            while((currentNode.getNext() != null) && !(currentNode.getElement().getKey().equals(rec.getKey()))) {
                currentNode = currentNode.getNext();
            }
            // Throws exception if there's a duplicate key
            if (currentNode.getElement().getKey().equals(rec.getKey())){
                throw new DuplicatedKeyException ("Duplicate Key");
            // Adds object to the list
            } else {
                currentNode.setNext(newNode);
                recordCount += 1;
            }
            return 1;
        }
    }

    /*
     * Function removes a Record object from the dictionary if it exists, given a key
     */
    public void remove(String key) throws InexistentKeyException{
        int hashKey = hashCode(key);
        LinearNode keyPosition = hashMap[hashKey];
        LinearNode prevPosition = keyPosition;
        int depth = 0; // Will keep track of how many links deep the record is

        while ((keyPosition != null) && !(keyPosition.getElement().getKey().equals(key))) {
            prevPosition = keyPosition;
            keyPosition = keyPosition.getNext();
            depth += 1;
        }

        if (keyPosition == null){
            throw new InexistentKeyException("Key does not exist");
        } else {
            if (depth == 0) {
                hashMap[hashKey] = keyPosition.getNext();
            } else if (keyPosition.getNext() != null){
                keyPosition.setNext(keyPosition.getNext().getNext());
            } else {
                prevPosition.setNext(null);
            }
            recordCount -= 1;
        }
    }

    /*
     * Returns a Record object given a key
     * If the object does not exist, returns null
     */
    public Record get(String key){
        int hashKey = hashCode(key);
        LinearNode keyPosition = hashMap[hashKey]; //position of value in hashmap given the key

        // Loops through to find the Record object using the key
        while ((keyPosition != null) && !(keyPosition.getElement().getKey().equals(key))) {
            keyPosition = keyPosition.getNext();
        }
        
        // Returns null if the object wasn't found and the object if it was found
        if (keyPosition == null){
            return null;
        } else {
            return keyPosition.getElement();
        }
    }

    /*
     * Returns the number of records in the Dictionary
     */
    public int numRecords(){
        return recordCount;
    }

    /*
     * Helper function calculates the hash code given a String key
     * Returns hash code as an integer
     */
    private int hashCode (String key){
        int value = (int)(key.charAt(key.length() - 1));    //initial value from first character of String key
        int HASH = 37;                                      //Constant value used in hash function

        // Finds value of hash from String key
        for (int i = (key.length() - 2); i >= 0; i--){
            value = (value * HASH + (int)key.charAt(i)) % hashSize;
        }

        return value;                                       //Returns value
    }
    

}
