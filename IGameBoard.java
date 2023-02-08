package cpsc2150.extendedTicTacToe.models;


/**
 * IGameBoard has two implementations:
 * GameBoard which uses a 2D array of characters
 * GameBoardMem uses a List of board positions and tokens
 * Initialization ensures:
 *  board contains only blank characters and is MAX SIZE x MAX SIZE or smaller
 *  Mem version contains nothing until added to list and is MAX SIZE x MAX SIZE or smaller
 * Defines:
 *  num_rows : Z
 *  num_cols : Z
 *  num_to_win : Z
 * Constraints:
 *  MIN SIZE < number of rows <= MAX SIZE
 *  MIN SIZE < number of cols <= MAX SIZE
 * @invariant
 * 0 < numRow <= MAX SIZE AND
 * 0 < numCol <= MAX SIZE
 * @correspondance
 * number of rows = numRow AND
 * number of columns = numCol AND
 * self = myGrid[0...numRow-1][0...numCol-1]
 */
public interface IGameBoard {
    //here
    public static final int rowMax = 100;
    public static final int rowMin = 3;
    public static final int colMax = 100;
    public static final int colMin = 3;
    public static final int winMax = 25;
    //array should be empty
    //no constructor interfaces have no constructors

    /**
     * This method checks to see if element contains' ' or not
     * user can put in anything and then checkSpace will see if its valid
     * if out of bounds, will return false
     * @param pos is boardPosition object used to get Object.getRow and Object.getCol
     * @pre
     * 0 <= r < number of rows and
     * 0 <= c < number of columns
     * @post
     * self = #self, except a will appear in
     * row r, column c
     */
    default public boolean checkSpace(BoardPosition pos) {
        //returns true if the position specified in pos is available;
        //false otherwise. If a space is not in bounds, then it is not available
        if(pos.getRow() >= getNumRows() || pos.getRow()<0 || pos.getColumn() >= getNumColumns() || pos.getColumn()<0) {
            return false;
        }
        else return whatsAtPos(pos) == ' ';
    }


    /**
     * This function places the marker on the position user specified IFF space is empty, does not return anything
     * @param marker is a boardPosition object used to access the Objects methods
     * @param player is character 'X' or 'O' to be placed in space specified by user
     * @pre checkSpace is TRUE, marker is a valid object, player =='X' OR player =='O'
     *  0<self<rowDim && 0<self<colDim
     * @post gameBoard[pos.getRow()][pos.getCol()]=='X' OR gameBoard[pos.getRow()][pos.getCol()]=='O'
     */
    public void placeMarker(BoardPosition marker, char player);

    /**
     * This method will check if last position played resulted in a winner
     * @param lastPos is boardPosition object used to access the Objects method
     * @pre checkSpace is valid, placeMarker is valid
     * @post checkForWin return TRUE IFF [lastPos results in numToWin(horizontal, vertical, or diagonal)
     * self=#self
     * SECONDARY so code in interface
     * @return checkForWinner= TRUE IFF checkHorizonalWin, checkVerticalWin, or checkDiagonalWin is TRUE else
     * checkForWinner=FALSE
     */
    default boolean checkForWinner(BoardPosition lastPos) {
        //this function will check to see if the lastPos placed resulted
        //in a winner. If so it will return true, otherwise (false)//Passing in the last position will help limit the possible
        //places to check for a win condition since you can assume that any win
        //condition that did not include the most recent play made would have
        //been caught earlier.
        //You may call other methods to complete this method
        char player= whatsAtPos(lastPos);
        return(checkVerticalWin(lastPos, player)|| checkHorizontalWin(lastPos, player)||
                checkDiagonalWin(lastPos, player));
    }


    /**
     * This method will see if game is tied (no space left, will have already checked for wins)
     * @pre [checkForWinner=False] AND 0=<pos.getRow=<rowDim AND 0=<pos.getCol=<colDim
     * @post function will iterate through GameBoard and checkSpace will return false throughout entire board
     *  self=checkForWinner [is false] &&
     *  self=checkSpace()[is false on entire board]
     * SECONDARY METHOD so code in interface
     * @return TRUE is all spaces are acquired ,else return FALSE
     */
    default public boolean checkForDraw(){
        for(int row = 0; row < getNumRows(); row++) {
            for(int col = 0; col < getNumColumns(); col++) {
                if(whatsAtPos(new BoardPosition(row, col)) == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * this method checks to see if lastPos marker resulted in a row of 5 of the same characters
     * @param lastPos is a boardPosition object used to access the Objects methods
     * @param player is a character displaying the valid markers 'X' or 'O'
     * @pre lastPos must be VALID object and player must be VALID
     * @post return TRUE IFF numToWin player characters in a row or horizontally including last position
     * @return return TRUE iff horizontal win, else FALSE
     */
    default public boolean checkHorizontalWin(BoardPosition lastPos, char player)
    {
        int countWins = 0;
        int checkLastRow = lastPos.getRow();

        for (int col = 0; col < getNumColumns(); col++) {
            if (whatsAtPos(new BoardPosition(checkLastRow, col)) == player) { countWins++; }
            if (whatsAtPos(new BoardPosition(checkLastRow, col)) != player) { countWins = 0; }
            if (countWins == getNumToWin()) { return true; }
        }

        return false;
    }


    /**
     * This method checks to see if lastPos marker played resulted in win vertically
     * @param lastPos is a boardPosition object used to access the Objects methods
     * @param player is either valid charcater datatype 'X' or 'O'
     * @pre lastPos must be a VALID object and player is VALID player
     *  0=<pos.getRow=<rowDim AND 0=<pos.getCol=<colDim
     *  0<self<rowDim && 0<self<colDim
     * @post return TRUE if numToWin characters(players) in vertical column including last position
     * @return TRUE if vertical win else false
     */
    default public boolean checkVerticalWin(BoardPosition lastPos, char player)
    {
        int countWins = 0;
        int checkLastCol = lastPos.getColumn();

        for (int row = 0; row < getNumRows(); row++) {
            if (whatsAtPos(new BoardPosition(row, checkLastCol)) == player) { countWins++; }
            if (whatsAtPos(new BoardPosition(row, checkLastCol)) != player) { countWins = 0; }
            if (countWins == getNumToWin()) { return true; }
        }

        return false;
    }

    /**
     *This method checks to see if lastPos marker resulted in a win diagonally in both directions
     * @param lastPos is a BoardPosition object used to access the Objects methods
     * @param player is either two valid character datatypes 'X' or 'O'
     * @pre lastPos must be a VALID object and player is VALID player
     * 0=<pos.getRow=<rowDim AND 0=<pos.getCol=<colDim
     * 0<self<rowDim && 0<self<colDim
     * @post return TRUE if numToWin player characters in a diagonal results in win including last Position
     * @return TRUE if diagonal win else FALSE
     */
    default public boolean checkDiagonalWin(BoardPosition lastPos, char player)
    {
        int minInRow = 1;
        int r = lastPos.getRow();
        int c = lastPos.getColumn();

        for(int i = r - 1, j = c + 1; i >= 0 && j < getNumColumns(); i--, j++) {
            BoardPosition pos = new BoardPosition(i, j);

            if(whatsAtPos(pos) == player) minInRow++;
            else break;

            if(minInRow >= getNumToWin()) return true;
        }

        for(int i = r + 1, j = c - 1; i < getNumRows() && j >= 0; i++, j--) {
            BoardPosition pos = new BoardPosition(i, j);
            if(whatsAtPos(pos) == player) minInRow++;
            else break;

            if(minInRow >= getNumToWin()) return true;
        }

        minInRow = 1;
        for(int i = r + 1, j = c + 1; i < getNumRows() && j < getNumColumns(); i++, j++) {
            BoardPosition pos = new BoardPosition(i, j);

            if(whatsAtPos(pos) == player) minInRow++;
            else break;

            if(minInRow >= getNumToWin()) return true;
        }

        for(int i = r - 1, j = c - 1; i >= 0 && j >= 0; i--, j--) {
            BoardPosition pos = new BoardPosition(i, j);

            if(whatsAtPos(pos) == player) minInRow++;
            else break;

            if(minInRow >= getNumToWin()) return true;
        }

        return false;
    }


    /**
     * This method checks what is at specified position, either ' ' or VALID player marker 'X' or 'O'
     * @param pos is a BoardPosition object used to access the Objects methods
     * @pre lastPos must be a VALID object and player is VALID player
     * 0=<pos.getRow=<rowDim AND 0=<pos.getCol=<colDim
     * 0<self<rowDim && 0<self<colDim
     * @post return whatsAtPos==' ' IFF empty else whatsAtPos==[player marker]
     * self=#self
     * @return character (in this case ' ','X',or'O') at specified position
     */
    public char whatsAtPos(BoardPosition pos);

    /**
     *This method checks to see if player is at position specified
     * @param pos is a BoardPosition object used to access the Objects methods
     * @param player is either two valid character types 'X' or 'O'
     * @pre lastPos must be a VALID object and player is VALID player
     * 0=<pos.getRow=<rowDim AND 0=<pos.getCol=<colDim
     * 0<self<rowDim && 0<self<colDim
     * @post self=whatsAtPos(pos)== player && self=#self
     * @return TRUE if player is at specified position else FALSE
     */
    default public boolean isPlayerAtPos(BoardPosition pos, char player)
    {
        //returns true if the player is at pos; otherwise, it returns
        //false
        //Note: this method will be implemented very similarly to
        //whatsAtPos, but it's asking a different question. We only know they
        //will be similar because we know GameBoard will contain a 2D array. If
        //the data structure were to change in the future, these two methods
        //could be radically different.
        return whatsAtPos(pos) == player;
    }

    /**
     * Gets the number of rows in the current game board
     *
     * @pre none
     * @post [number of rows == ROW_DEPTH]
     *
     * @return [returns the number of rows as an int]
     */
    public int getNumRows();

    /**
     * Gets the number of column in the current game board
     *
     * @pre none
     * @post [number of columns == COLUMN_DEPTH]
     *
     * @return [returns the number of columns as an int]
     */
    public int getNumColumns();

    /**
     * Gets the number of tokens in a row needed to win a game
     *
     * @pre none
     * @post NUM_TO_WIN == 5
     *
     * @return [Returns the number of tokens needed, 5, in a row to win game]
     */
    public int getNumToWin();

}
