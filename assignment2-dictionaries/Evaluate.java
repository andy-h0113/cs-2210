/*
 * This class evaluates the game board and accomodates for game play.
 * 
 * Author = Andy Hwang || 251217976
 */

public class Evaluate {

    private char[][] gameBoard;
    private int tilesToWin;
    private int boardSize;

    private final int DICTIONARY_SIZE = 9887;
    private final char EMPTY = 'e';
    private final char COMPUTER = 'c';
    private final char HUMAN = 'h';

    /*
     * Contstructor for class evaluate
     * Takes in inputs and assigns them to variables
     */
    public Evaluate (int size, int tilesToWin, int maxLevels){
        this.tilesToWin = tilesToWin;
        this.boardSize = size;

        //initializes gameBoard so that every slot stores character 'e'
        gameBoard = new char[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++){
            for (int j = 0; j < boardSize; j++){
                gameBoard[i][j] = EMPTY;
            }
        }
    }

    /*
     * returns an empty dictionary of the selected size
     */
    public Dictionary createDictionary() {
        Dictionary dict = new Dictionary(DICTIONARY_SIZE);
        return dict;
    }

    /*
     * represents the contents of gameBoard as a String and checks for record in dict
     * if dict contains it, method returns the record. Otherwise, method returns null
     */
    public Record repeatedState(Dictionary dict){
        String arrayForm = this.makeString();       //Converts gameBoard into string
        return dict.get(arrayForm);
    }

    /*
     * Creates and inserts a Record object given the values
     */
    public void insertState(Dictionary dict, int score, int level) {
        String arrayForm = this.makeString();
        Record newRecord = new Record(arrayForm, score, level);
        dict.put(newRecord);
    }
    
    /*
     * Stores a play made by either the computer or the human in the gameBoard
     */
    public void storePlay(int row, int col, char symbol){
        gameBoard[row][col] = symbol;
    }

    /*
     * Checks if the square on the gameBoard is empty
     */
    public boolean squareIsEmpty(int row, int col){
        return this.isSymbol(row, col, EMPTY);
    }

    /*
     * Checks if the tile belongs to the computer
     */
    public boolean tileOfComputer (int row, int col){
        return this.isSymbol(row, col, COMPUTER);
    }

    /*
     * Checks if the tile belonds to the human
     */
    public boolean tileOfHuman (int row, int col){
        return this.isSymbol(row, col, HUMAN);
    }

    /*
     * Checks gameBoard and returns false if any square is empty. Otherwise returns true.
     */
    public boolean isDraw(){
        for (int i = 0; i < boardSize; i++){
            for (int j = 0; j < boardSize; j++){
                if (gameBoard[i][j] == EMPTY) return false;           
            }
        }
        return true;
    }

    /*
     * Checks if the owner of the received symbol has won
     * Returns true if won, and false if they didn't win
     */
    public boolean wins(char symbol){
        boolean horizontal = horizontalCheck(symbol);
        boolean vertical = verticalCheck(symbol);
        boolean ascDiag = ascDiagCheck(symbol);
        boolean descDiag = descDiagCheck(symbol);

        if (horizontal || vertical || ascDiag || descDiag){
            return true;
        } else {
            return false;
        }
    }

    public int evalBoard(){
        if (this.wins(HUMAN)){
            return 0;
        } else if (this.wins(COMPUTER)){
            return 3;
        } else if (this.isDraw()){
            return 2;
        } else {
            return 1;
        }
    }

    /*
     * Checks for consecutive horizontal tiles of the received character
     */
    private boolean horizontalCheck(char symbol){
        int counter = 0;

        for (int row = 0; row < boardSize; row++){
            for (int col = 0; col < boardSize; col++){
                // If tile is equal to the symbol it increases the counter. Otherwise, it resets the counter
                if (gameBoard[row][col] == symbol){
                    counter += 1;
                    if (counter == tilesToWin) return true;
                } else {
                    counter = 0;
                }
            }
            counter = 0; // Resets the counter after every row
        }
        return false; // Returns false if it traverses through the whole table without meeting the win criteria
    }

    /*
     * Checks for consecutive vertical tiles of the received character
     */
    private boolean verticalCheck(char symbol){
        int counter = 0;

        for (int col = 0; col < boardSize; col++){
            for (int row = 0; row < boardSize; row++){
                // If tile is equal to the symbol it increases the counter. Otherwise, it resets the counter
                if (gameBoard[row][col] == symbol){
                    counter += 1;
                    if (counter == tilesToWin) return true;
                } else {
                    counter = 0;
                }
            }
            counter = 0; // Resets the counter after every column
        }
        return false; // Returns false if it traverses through the whole table without meeting the win criteria
    }

    /*
     * Checks for consecutive ascending diagonal tiles of the received character
    image.png */
    private boolean ascDiagCheck(char symbol){
        int counter;

        // Checks diagonals that start from rows first for any winning lines
        for (int startRow = 0; startRow <= (boardSize - tilesToWin); startRow++){
            counter = 0;
            // Iterates through table by adjusting which row the diagonal starts on
            for (int row = startRow, col = (boardSize - 1); (row < boardSize) && (col >= 0); row++, col--){
                if (gameBoard[row][col] == symbol) {
                    counter += 1;
                    if (counter == tilesToWin) return true;
                } else {
                    counter = 0;
                }
            }
        }

        // Checks diagonals that start from the columns for any winning lines
        for (int startCol = (boardSize - 2); startCol >= (boardSize - tilesToWin); startCol--){   
            counter = 0;
            // Iterates through table by adjusting which column the diagonal starts on
            for (int col = startCol, row = 0; (row < boardSize) && (col >= 0); col--, row++){
                if (gameBoard[row][col] == symbol) {
                    counter += 1;
                    if (counter == tilesToWin) return true;
                } else {
                    counter = 0;
                }
            }
        }
        return false;
    }

    /*
     * Checks for consecutive descending diagonal tiles of the received character
     */
    private boolean descDiagCheck(char symbol){
        int counter;

        // Checks diagonals that start from rows first for any winning lines
        for (int startRow = 0; startRow <= (boardSize - tilesToWin); startRow++){
            counter = 0;
            // Iterates through table by adjusting which row the diagonal starts on
            for (int row = startRow, col = 0; row < boardSize && col < boardSize; row++, col++){
                if (gameBoard[row][col] == symbol) {
                    counter += 1;
                    if (counter == tilesToWin) return true;
                } else {
                    counter = 0;
                }
            }
        }

        // Checks diagonals that start from the columns for any winning lines
        for (int startCol = 1; startCol <= (boardSize - tilesToWin); startCol++){   // startCol == 1 because we already checked startCol == 0
            counter = 0;
            // Iterates through table by adjusting which column the diagonal starts on
            for (int col = startCol, row = 0; row < boardSize && col < boardSize; col++, row++){
                if (gameBoard[row][col] == symbol) {
                    counter += 1;
                    if (counter == tilesToWin) return true;
                } else {
                    counter = 0;
                }
            }
        }
        return false;
    }

    /*
     * Helper function to convert the game board into a string
     */
    private String makeString(){
        String arrayForm = "";

        for (int i = 0; i < boardSize; i++){
            for (int j = 0; j < boardSize; j++){
                arrayForm += Character.toString(gameBoard[i][j]);
            }
        }
        return arrayForm;
    }

    /*
     * Checks if the position the gameBoard contains the received symbol
     * Returns true if it does and false if it doesn't
     */
    private boolean isSymbol(int row, int col, char symbol){
        if (gameBoard[row][col] == symbol){
            return true;
        } else {
            return false;
        }
    }
}
