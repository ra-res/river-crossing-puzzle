package Solution;
import java.util.ArrayList;

public class GameState {
    final char[] board;
    private char farmerPlace;
    static final char[] INITIAL_BOARD = {'L','L','L','L'};
    static final char[] GOAL_BOARD = {'R','R','R','R'};
    static final int BOARD_SIZE = 4;

    public GameState(char[] board) {
        this.board = board;
        this.farmerPlace = board[BOARD_SIZE-1];
    }

    public GameState clone() {
        char[] clonedBoard = new char[BOARD_SIZE];
        System.arraycopy(this.board, 0, clonedBoard, 0, BOARD_SIZE);
        return new GameState(clonedBoard);
    }

    public char getFarmerPlace() {
        return farmerPlace;
    }

    public String toString() {
        String s = "[";
        for (char c : this.board) s = s + c;
        return s + "]";
    }

    public boolean isGoal() {
        for (int j = 0; j < BOARD_SIZE; j++) {
            if (this.board[j] != GOAL_BOARD[j]) return false;
        }
        return true;
    }

    public boolean sameBoard (GameState gs) {
        for (int j = 0; j < BOARD_SIZE; j++) {
            if (this.board[j] != gs.board[j]) return false;
        }
        return true;
    }

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

