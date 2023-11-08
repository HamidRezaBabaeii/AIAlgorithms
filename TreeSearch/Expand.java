package AIAlgorithms.TreeSearch;

import java.util.ArrayList;

public class Expand {

    // variables
    private ArrayList<Node> expand_Nodes = new ArrayList<Node>();
    private int counterDepth = 1;

    // null constructor
    public Expand() {

    }

    // constructor
    public void add_expand(Node node, int[][] proximityArray, Fringe fringe) {
        this.expand_Nodes.add(node);
        for (int i = 0; i < proximityArray.length; i++) {
            if (proximityArray[(int) node.getNodeState()][i] != 0) {
                Node new_node = new Node(i, node, proximityArray[node.getNodeState()][i], counterDepth);
                fringe.add_new_node(new_node);
            }
        }
        counterDepth++;
    }

    // return nodes
    public ArrayList<Node> show_expandNodes() {
        return expand_Nodes;
    }

    // returns node nums
    public ArrayList<Integer> show_expandNodes_state() {

        ArrayList<Integer> nodeNums = new ArrayList<>();
        for (int i = 0; i < expand_Nodes.size(); i++) {
            nodeNums.add(expand_Nodes.get(i).getNodeState());
        }
        return nodeNums;
    }

}
