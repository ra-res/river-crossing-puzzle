package Solution;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Solver {

    ArrayList<Node> unexpanded = new ArrayList<Node>();
    ArrayList<Node> expanded = new ArrayList<Node>();
    Node rootNode;

    public Solver(char[] initialBoard) {
        GameState initialState = new GameState(initialBoard);
        rootNode = new Node(initialState);
    }

    public void solveBreathFirst(PrintWriter output) {
        //Breath First Search Implementation
        unexpanded.add(rootNode);
        while (unexpanded.size() > 0){
            Node n = unexpanded.get(0);
            expanded.add(n);
            unexpanded.remove(0);
            if(n.state.isGoal()){
                reportSolution(n, output);
                return;
            }else{
                ArrayList<GameState> moves = n.state.possibleMoves();
                for(GameState move : moves){
                    if ((Node.findNodeWithState(unexpanded, move) == null) &&
                            (Node.findNodeWithState(expanded, move) == null)){
                            int newCost = n.getCost()+1;
                            Node newNode = new Node(move, n , newCost);
                            unexpanded.add(newNode);
                    }
                }
            }
        }
            output.println("No Solution Found!.");
        }

        public void solveDepthFirst(PrintWriter output) {
            //Depth First Search Implementation
            unexpanded.add(rootNode);
            while (unexpanded.size() > 0){
                Node n = unexpanded.get(unexpanded.size() - 1);
                expanded.add(n);
                unexpanded.remove(unexpanded.size() - 1);
            if(n.state.isGoal()){
                reportSolution(n, output);
                return;
            }else{
                ArrayList<GameState> moves = n.state.possibleMoves();
                for(GameState move : moves){
                    if ((Node.findNodeWithState(unexpanded, move) == null) &&
                            (Node.findNodeWithState(expanded, move) == null)){
                        int newCost = n.getCost()+1;
                        Node newNode = new Node(move, n , newCost);
                        unexpanded.add(newNode);
                    }
                }
            }
        }
        output.println("No Solution Found!.");
    }

    public void printSolution(Node n, PrintWriter output) {
        if (n.parent != null) printSolution(n.parent, output);
        output.println(n.state);
    }

    public void reportSolution(Node n, PrintWriter output) {
        output.println("Solution found!");
        printSolution(n, output);
        output.println(n.getCost() + " Moves");
        output.println("Nodes expanded: " + this.expanded.size());
        output.println("Nodes unexpanded: " + this.unexpanded.size());
        output.println();
    }


    public static void main(String[] args) throws Exception {
//         Breath First Search
        Solver problem = new Solver(GameState.INITIAL_BOARD);
        File outFile = new File("outputBreathFirst.txt");
        PrintWriter output = new PrintWriter(outFile);
        problem.solveBreathFirst(output);
        output.close();
        // Depth First Search
        Solver problem1 = new Solver(GameState.INITIAL_BOARD);
        File outFile1 = new File("outputDepthFirst.txt");
        PrintWriter output1 = new PrintWriter(outFile1);
        problem1.solveDepthFirst(output1);
        output1.close();
    }
}
