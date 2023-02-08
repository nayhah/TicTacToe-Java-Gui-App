package cpsc2150.extendedTicTacToe.models;

/**
 * Purpose of BoardPosition is to keep track of one specific location
 * generic enough to use in other games needing board position
 * @author Nayha Hussain
 * @version 2.0
 * @invariant row>=0 && column >=0
 */
public class BoardPosition {
    private int boardRow;
    private int boardColumn;


    /**
     * Constructor takes in to parameters both of type int, one for row and one for column
     * creates an object BoardPosition
     * Constructors do not return anything
     * Limitations are out of boardPosition scope, therefore no pre
     * @param row is row integer
     * @param column is column integer
     */
    public BoardPosition( int row, int column){
        boardRow=row;
        boardColumn=column;
    }

    /**
     * This method gets the row int and returns row
     * not modifying anything, no pre-conditions
     * @post getRow=row
     * @return getRow=#row (row should not change)
     */
    public int getRow() {
        return boardRow;
    }

    /**
     * this methods gets the column int and returns column
     * not modifying anything, no pre-conditions
     * @post getColumn=column
     * @return getColumn=#column (column should not change)
     */
    public int getColumn(){
        return boardColumn;
    }




    /**
     * This method checks to see is board position is set to coordinates
     * overrided equals() function will check whether two valid BoardPosition objects are equal to each other
     * by comparing their row and column attributes
     * //@param Board is an BoardPosition object
     * @pre BoardPosition must be a valid object
     * @post return true iff Object.boardRow == board.boardRow AND Object.boardColumn == board.boardColumn
     *  Will return true or false depending on if the rows and column attributes between two BoardPosition objects
     *  are identical
     * @return whether Object == board
     */
    @Override
    public boolean equals(Object obj) {

        //in case an Object was passed that is not of type BoardPosition
        if(!(obj instanceof BoardPosition)) return false;


        return (this.getRow() == ((BoardPosition)obj).getRow() &&
                this.getColumn() == ((BoardPosition)obj).getColumn());
    }

    /**
     * This method outputs the coordinates
     * not modifying anything, no pre condition
     * Overrided tostring() function will return a string in the format "<row>,<column>" of the current BoardPosition
     * @post return("<"+row+">"+"<"+column+">");
     * @return string <row><column> "3,5"
     */
    @Override
    public String toString(){
        String coordinates = getRow() + "," + getColumn();
        return coordinates;
    }
}
