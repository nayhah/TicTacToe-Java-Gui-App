package cpsc2150.extendedTicTacToe.models;

import java.util.*;

/**
 * This class contains memory efficent gameBoard
 * @author Nayha Hussain
 * @invariant minWin <= numRows <= rowColMax AND minWin <= numCols
 *  <= rowColMax AND minWin <= numToWin <= maxWin
 * @correspondance rowNum = #rowNum
 * @correspondence colNum = #colNum
 * @correspondence winNum = #winNum
 */
public class GameBoardMem extends AbsGameBoard implements IGameBoard {
    //variables for the memory efficent version of game
    int rowNum;
    int colNum;
    int winNum;
    public Map<Character, List<BoardPosition>> gbMap= new HashMap<>();

    //game board 2D character array
    //do we need one gameBoardM for output
    //or an individual one for every player

    //constructor

    /**
     * This is constructor for gameBoardMem creates variables for memory efficent game
     * @param rowNum, colNum, and winNum are integer values
     * @pre [must be in bounds of user defined rows and columns] [numToWin must be less than numOfRows]
     * @post rowNum = #rowNum AND colNum = #colNum AND numToWin = #numToWin
     * [map is initially empty and each of the rows, columns and number to win
     * have been properly stored in the private variables.]
     */
    public GameBoardMem(int rowNum, int colNum, int winNum) {
        //set out private variables to the values
        //that are passed into the constructor
        this.rowNum = rowNum;
        this.colNum = colNum;
        this.winNum = winNum;

    }

    //getter functions
    @Override
    public int getNumRows() {
        return rowNum;
    };
    @Override
    public int getNumColumns() {
        return colNum;
    }
    @Override
    public int getNumToWin() {
        return winNum;
    }
    @Override
    public void placeMarker(BoardPosition marker, char player) {
        if(!gbMap.containsKey(player)){
            List<BoardPosition> list = new LinkedList<>();
            gbMap.put(player, list);
        }
        gbMap.get(player).add(marker);

    }
    @Override
    public char whatsAtPos(BoardPosition pos) {
        //get all the markers in the map
        for (Map.Entry<Character, List<BoardPosition>> marker : gbMap.entrySet()) {
            //get the marker at that position, put it into a list
            List<BoardPosition> tempList = gbMap.get(marker.getKey());
            if (tempList.contains(pos)) {
                //if that position has the marker return the marker
                return marker.getKey();
            }
        }
        return ' ';
    }

    @Override
    public boolean isPlayerAtPos(BoardPosition pos, char player){
        //if it contains the players token
        if(gbMap.containsKey(player)){
            List<BoardPosition> tmpList = gbMap.get(player);
            return tmpList.contains(pos);
        }
        return false;
    }

}
