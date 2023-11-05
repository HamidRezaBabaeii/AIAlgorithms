package AIAlgorithms.TreeSearch;

import java.util.ArrayList;

public class Node {

   private int nodeNum ;
   private char nodeState;
   private int nodeParent;
   private ArrayList<Integer> nodeAction;
   private float pathCost;
   private int nodeDepth;
 

    public Node(int nodeNum, char nodeState, int nodeParent, ArrayList<Integer> nodeAction, float pathCost, int nodeDepth){
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

    public char getNodeState() {
        return this.nodeState;
    }

    public void setNodeState(char nodeState) {
        this.nodeState = nodeState;
    }

    public int getNodeParent() {
        return this.nodeParent;
    }

    public void setNodeParent(int nodeParent) {
        this.nodeParent = nodeParent;
    }

    public ArrayList<Integer> getNodeAction() {
        return this.nodeAction;
    }

    public void setNodeAction(ArrayList<Integer> nodeAction) {
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
