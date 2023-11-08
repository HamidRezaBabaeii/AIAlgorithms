package AIAlgorithms.TreeSearch;

import java.util.ArrayList;

public class Fringe {
    
    private ArrayList<Node> node = new ArrayList<Node>();
    
    public Fringe(int nodeState, Node nodeParent, float pathCost, int nodeDepth){
        Node node_class = new Node(nodeState,nodeParent,pathCost,nodeDepth);
        this.node.add(node_class);

    }

    // return node
    public ArrayList<Node> get_Fringe(){
        return node;
    }

    // add function(for BFS)
    public void add_new_node_BFS(Node pushNode){
        node.add(pushNode);
    }

    // add function(for UCS)
    public void add_new_node_UCS(Node pushNode){
        node.add(pushNode);
        if(node.size()>1){
            Sort_ArrayList(node);
        }
    }


    // sort the node arraylist
    private static void Sort_ArrayList(ArrayList<Node> node){
        for(int i=0 ; i<node.size(); i++){
            for(int j=i ; j<node.size();j++){
                if(node.get(i).getPathCost()>node.get(j).getPathCost()){
                    Node swapNodei = node.get(i);
                    Node swaNodej = node.get(j);
                    node.set(i, swaNodej);
                    node.set(j, swapNodei);
                }
            }
        }
    }

}
