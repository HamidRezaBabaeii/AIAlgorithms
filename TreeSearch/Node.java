package AIAlgorithms.TreeSearch;

public class Node {

   private int nodeNum ;
   private String nodeState;
   private int nodeParent;
   private int nodeAction;
   private float pathCost;
   private int nodeDepth;

 

    public Node(int nodeNum, String nodeState, int nodeParent, int nodeAction, float pathCost, int nodeDepth){
        this.nodeNum = nodeNum;
        this.nodeState = nodeState;
        this.nodeParent = nodeParent;
        this.pathCost = pathCost;
        this.nodeDepth = nodeDepth;
    }

        public int getNodeNum() {
        return this.nodeNum;
    }

    public void setNodeNum(int nodeNum) {
        this.nodeNum = nodeNum;
    }

    public String getNodeState() {
        return this.nodeState;
    }

    public void setNodeState(String nodeState) {
        this.nodeState = nodeState;
    }

    public int getNodeParent() {
        return this.nodeParent;
    }

    public void setNodeParent(int nodeParent) {
        this.nodeParent = nodeParent;
    }

    public int getNodeAction() {
        return this.nodeAction;
    }

    public void setNodeAction(int nodeAction) {
        this.nodeAction = nodeAction;
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
