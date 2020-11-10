//The method solve is empty. Please write code for this method based on the pseudo code for breath first search.
package Solution;
import java.io.File;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
   Solver is a class that contains the methods used to search for and print solutions
   plus the data structures needed for the search.
 */

public class Solver {

    ArrayList<Node> unexpanded = new ArrayList<Node>(); // Holds unexpanded node list
    ArrayList<Node> expanded = new ArrayList<Node>();   // Holds expanded node list
    Node rootNode;                                      // Node representing initial state



    /*
       Solver is a constructor that sets up an instance of the class with a node corresponding
       to the initial state as the root node.
     */
    public Solver(char[] initialBoard) {
        GameState initialState = new GameState(initialBoard);
        rootNode = new Node(initialState);
    }

    /*
       The method solve searches for a solution. It implements a breadth first search.
       The problem asks for a solution with the minimum number of moves.
       Breadth first search is both complete and optimal with respect to number of moves.
       The Printwriter argument is used to specify where the output should be directed.
     */
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



    /*
       printSolution is a recursive method that prints all the states in a solution.
       It uses the parent links to trace from the goal to the initial state then prints
       each state as the recursion unwinds.
       Node n should be a node representing the goal state.
       The Printwriter argument is used to specify where the output should be directed.
     */
    public void printSolution(Node n, PrintWriter output) {
        if (n.parent != null) printSolution(n.parent, output);
        output.println(n.state);
    }

    /*
       reportSolution prints the solution together with statistics on the number of moves
       and the number of expanded and unexpanded nodes.
       The Node argument n should be a node representing the goal state.
       The Printwriter argument is used to specify where the output should be directed.
     */
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
        Solver problem = new Solver(GameState.INITIAL_BOARD);  // Set up the problem to be solved.
        File outFile = new File("outputBreathFirst.txt");                 // Create a file as the destination for output
        PrintWriter output = new PrintWriter(outFile);         // Create a PrintWriter for that file
        problem.solveBreathFirst(output);                                 // Search for and print the solution
        output.close();
        // Depth First Search
        Solver problem1 = new Solver(GameState.INITIAL_BOARD);
        File outFile1 = new File("outputDepthFirst.txt");
        PrintWriter output1 = new PrintWriter(outFile1);
        problem1.solveDepthFirst(output1);
        output1.close();
    }
}
