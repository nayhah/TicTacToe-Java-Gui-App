package cpsc2150.extendedTicTacToe.models;

public abstract class AbsGameBoard implements IGameBoard {


    /**
     * This method provides a string representation of the GameBoard.
     *
     * @return A string representation of GameBoard.
     *
     * @pre NONE
     * @post toString = [a string representation of GameBoard] AND board[4][7] = #board[4][7]
     */
    @Override
    public String toString()
    {

        int r = getNumRows();
        int c = getNumColumns();

        String str = "   ";

        for(int i = 0; i < c; i++) {
            //prints out top numbers labelling the columns
            str = str.concat(String.format("%2d|",i));
        }
        str = str.concat("\n");

        for (int row = 0; row < r; row++) {
            for ( int col = 0; col < c; col++) {
                BoardPosition pos = new BoardPosition(row, col);
                //they all need to be 2 spaces to the left of 0
                //add row numbers
                if (col == 0) {
                    //if in the numbered row area
                    str = str.concat(String.format("%2d",row));
                }
                str = str.concat("|");
                str = str.concat(String.valueOf(whatsAtPos(pos)));
                str = str.concat(" ");
            }
            str = str.concat("|");
            str = str.concat("\n");
        }

        return str;
    }

}
