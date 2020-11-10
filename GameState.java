// This class implements the state space representation of the corn, goose and fox problem. 
// Please write comments where appropriate on the state space, initial state, goal state, operations and transition functions. 
package Solution;
import java.util.ArrayList;

/*
      Instances of the class GameState represent states that can arise in the sliding block puzzle.
      The char array board represents the locations (L or R) of Corn, Goose, Fox, and Farmer respectively. 
      INITIAL_BOARD and GOAL_BOARD are constant arrays holding the initial and goal states (board configurations).
 */

public class GameState {
    final char[] board;
    private char farmerPlace; //not really necessary. the same as board[BOARD_SIZE-1]
    static final char[] INITIAL_BOARD = {'L','L','L','L'};
    static final char[] GOAL_BOARD = {'R','R','R','R'};
    static final int BOARD_SIZE = 4;
    
    /*
        GameState is a constructor that takes a char array holding a board configuration as argument.
     */
    public GameState(char[] board) {
        this.board = board;
        this.farmerPlace = board[BOARD_SIZE-1];
    }

    /*
        clone returns a new GameState with the same board configuration as the current GameState.
     */
    public GameState clone() {
        char[] clonedBoard = new char[BOARD_SIZE];
        System.arraycopy(this.board, 0, clonedBoard, 0, BOARD_SIZE);
        return new GameState(clonedBoard);
    }

    public char getFarmerPlace() {
        return farmerPlace;
    }

    /*
        toString returns the board configuration of the current GameState as a printable string.
    */
    public String toString() {
        String s = "[";
        for (char c : this.board) s = s + c;
        return s + "]";
    }

    /*
        isGoal returns true if and only if the board configuration of the current GameState is the goal
        configuration.
    */
    public boolean isGoal() {
        for (int j = 0; j < BOARD_SIZE; j++) {
            if (this.board[j] != GOAL_BOARD[j]) return false;
        }
        return true;
    }

    /*
         sameBoard returns true if and only if the GameState supplied as argument has the same board
         configuration as the current GameState.
     */
    public boolean sameBoard (GameState gs) {
        for (int j = 0; j < BOARD_SIZE; j++) {
            if (this.board[j] != gs.board[j]) return false;
        }
        return true;
    }

    /*
        possibleMoves returns a list of all GameStates that can be reached in a single move from the current GameState.
     */
    public ArrayList<GameState> possibleMoves() {
        ArrayList<GameState> moves = new ArrayList<GameState>();
        for (int start = 0; start < BOARD_SIZE-1; start++) {
            if (this.board[start] == this.farmerPlace) {
                 GameState newState = this.clone();
                 if (this.farmerPlace == 'L') {
                    newState.board[start] = 'R';
                    newState.board[BOARD_SIZE-1]= 'R';
                    newState.farmerPlace = 'R';
                 } else {
                    newState.board[start] = 'L';
                    newState.board[BOARD_SIZE-1]= 'L';
                    newState.farmerPlace = 'L';
                 }
                 moves.add(newState);
            }
        }
        GameState newState = this.clone();
	if (this.farmerPlace == 'L') {
	    newState.board[BOARD_SIZE-1] = 'R';
	    newState.farmerPlace = 'R';
	}  else {
	    newState.board[BOARD_SIZE-1]= 'L';
	    newState.farmerPlace = 'L';
	}
	moves.add(newState);
        return moves;
    }
}

