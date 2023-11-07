package AIAlgorithms.TreeSearch;

import java.util.ArrayList;

public class Node {


   private char nodeState;
   private Node nodeParent;
   private float pathCost;
   private int nodeDepth;
 

    public Node( char nodeState, Node nodeParent, float pathCost, int nodeDepth){
        this.nodeState = nodeState;
        this.nodeParent = nodeParent;
        this.pathCost = pathCost;
        this.nodeDepth = nodeDepth;
    }

    public char getNodeState() {
        return this.nodeState;
    }

    public void setNodeState(char nodeState) {
        this.nodeState = nodeState;
    }

    public Node getNodeParent() {
        return this.nodeParent;
    }

    public void setNodeParent(Node nodeParent) {
        this.nodeParent = nodeParent;
    }

    public float getPathCost() {
        return this.pathCost;
    }

    public void setPathCost(float pathCost) {
        this.pathCost = pathCost;
    }

    public int getNodeDepth() {
        return this.nodeDepth;
    }

    public void setNodeDepth(int nodeDepth) {
        this.nodeDepth = nodeDepth;
    }

    

}
