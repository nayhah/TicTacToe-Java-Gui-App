package cpsc2150.extendedTicTacToe.models;
/**
 *
 * @author Nayha Hussain
 * * @invariant gameBoard[rowDim][colDim]
 *      0<rowDim<=MAX_SIZE
 *      0<colDim<=MAX_SIZE
 * @correspondance
 *  numOfRows=rowDim
 *  numOfCols=colDim
 * self=gameBoard[rowDim-1][colDim-1]
 *
 * */
public class GameBoard extends AbsGameBoard implements IGameBoard {
    //variables for when user gets to decide dimensions
    private int rowNum = 0;
    private int colNum = 0;
    private int winNum = 0;

    //array should be empty
    private char[][] gameBoard = new char[rowMax][colMax];

    /**
     * the constructor is building the empty GameBoard array, used to access all methods and attributes
     * @param rowNum, colNum, and winNum are all integer values
     * @return none
     * @pre MIN_size<row<=MAX_size
            * MIN_size<col<=MAX_size
            * MIN_size<numToWin<=MAX_size
            * @post [every position in the 2D array is now filled with a blank space character
            * and each of the rows, columns and number to win have been properly stored
            * in the private variables.]
            * gameBoard=#gameBoard
     */
    //constructor
    //constructor
    public GameBoard(int rowNum, int colNum, int winNum) {
        this.rowNum=rowNum;
        this.colNum=colNum;
        this.winNum=winNum;
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                gameBoard[i][j] = ' ';
            }
        }

    }
    @Override
    public int getNumRows(){
        return rowNum;
    };
    @Override
    public int getNumColumns(){

        return colNum;
    };
    @Override
    public int getNumToWin(){

        return winNum;
    };
    @Override
    public void placeMarker(BoardPosition marker, char player){
        gameBoard[marker.getRow()][marker.getColumn()]=player;
    };
    @Override
    public char whatsAtPos(BoardPosition pos){
        return gameBoard[pos.getRow()][pos.getColumn()];
    };
}
