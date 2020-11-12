package Solution;
import java.util.ArrayList;

public class Node {
    GameState state;
    Node parent;
    private int cost;

    public Node(GameState state, Node parent, int cost) {
        this.state = state;
        this.parent = parent;
        this.cost = cost;
    }

    public Node(GameState state) {
        this(state,null,0);
    }

    public int getCost() {
        return cost;
    }

    public String toString() {
        return "Node:" + state + " ";
    }

    public static Node findNodeWithState(ArrayList<Node> nodeList, GameState gs) {
        for (Node n : nodeList) {
            if (gs.sameBoard(n.state)) return n;
        }
        return null;
    }
}
