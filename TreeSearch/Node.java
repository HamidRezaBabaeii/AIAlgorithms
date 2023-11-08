package AIAlgorithms.TreeSearch;


public class Node {


   private int nodeState;
   private Node nodeParent;
   private float pathCost;
   private int nodeDepth;
 

    public Node( int nodeState, Node nodeParent, float pathCost, int nodeDepth){
        this.nodeState = nodeState;
        this.nodeParent = nodeParent;
        this.pathCost = pathCost;
        this.nodeDepth = nodeDepth;
    }

    public int getNodeState() {
        return this.nodeState;
    }

    public void setNodeState(int nodeState) {
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
