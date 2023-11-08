package AIAlgorithms.TreeSearch;

import java.util.ArrayList;

public class Expand {

    // variables
    private ArrayList<Node> expand_Nodes_BFS = new ArrayList<Node>();
    private ArrayList<Node> expand_Nodes_UCS = new ArrayList<Node>();
    private int counterDepth = 1;
    

    // null constructor
    public Expand() {

    }

    // BFS expand methods
    public void add_expand_BFS(Node node, int[][] proximityArray, Fringe fringe) {
        this.expand_Nodes_BFS.add(node);
        for (int i = 0; i < proximityArray.length; i++) {
            if (proximityArray[node.getNodeState()][i] != 0) {
                Node new_node = new Node(i, node, proximityArray[node.getNodeState()][i], counterDepth);
                fringe.add_new_node_BFS(new_node);
            }
        }
        counterDepth++;
    }

    // UCS expand methods
    public void add_expand_UCS(Node node, int[][] proximityArray, Fringe fringe) {
        this.expand_Nodes_UCS.add(node);
        
        for (int i = 0; i < proximityArray.length; i++) {
            if (proximityArray[node.getNodeState()][i] != 0) {
                Node new_node = new Node(i, node, node.getPathCost() + proximityArray[node.getNodeState()][i], 0);
                fringe.add_new_node_UCS(new_node);
            }
        }
    }

    // returns node States
    public ArrayList<Integer> show_expandNodes_state_BFS() {

        ArrayList<Integer> nodeNums = new ArrayList<>();
        for (int i = 0; i < expand_Nodes_BFS.size(); i++) {
            nodeNums.add(expand_Nodes_BFS.get(i).getNodeState());
        }
        return nodeNums;
    }

    public ArrayList<Integer> show_expandNodes_state_UCS() {

        ArrayList<Integer> nodeNums = new ArrayList<>();
        for (int i = 0; i < expand_Nodes_UCS.size(); i++) {
            nodeNums.add(expand_Nodes_UCS.get(i).getNodeState());
        }
        return nodeNums;
    }

}
