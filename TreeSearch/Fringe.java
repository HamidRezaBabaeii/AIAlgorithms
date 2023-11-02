package AIAlgorithms.TreeSearch;

import java.util.ArrayList;

public class Fringe {
    
    private ArrayList<Node> node = new ArrayList<Node>();

    public Fringe(int nodeNum, String nodeState, int nodeParent, int nodeAction, float pathCost, int nodeDepth){
        Node node_class = new Node(nodeNum,nodeState,nodeParent,nodeAction,pathCost,nodeDepth);
        this.node.add(node_class);
    }

    public ArrayList<Node> get_Fringe(){
        return node;
    }
    //public void add_Node_to_fringe(int nodeNum, String nodeState, int nodeParent, int nodeAction, float pathCost, int nodeDepth){}


}
