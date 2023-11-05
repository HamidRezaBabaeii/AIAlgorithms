package AIAlgorithms.TreeSearch;

import java.util.ArrayList;

public class Expand {

    // variables 
    private ArrayList<Node> expand_Nodes = new ArrayList<>();
    private int counterDepth = 1 ;
    private int counterNodeNumber = 1;

    //constructor
    public Expand(Node node , int[][] proximityArray){
        this.expand_Nodes.add(node);
        for(int i=0 ; i< node.getNodeAction().length; i++){
            if(node.getNodeAction()[i]!=0){
                int node_actions[] = new int[proximityArray.length];
                for(int j=0 ; j<proximityArray.length ; j++){
                    node_actions[j] = proximityArray[counterNodeNumber][j];
                }
                new Fringe(counterNodeNumber, null, node.getNodeNum(), node_actions, node.getNodeAction()[i], counterDepth);
                counterNodeNumber++;
                counterDepth++;
            }
        }
    }

    //null constructor
    public Expand(){
        
    }

    // return nodes
    public  ArrayList<Node> show_expandNodes(){
        return expand_Nodes;
    }

    // returns node nums
    public  ArrayList<Integer> show_expandNodes_numbers(){

        ArrayList<Integer> nodeNums = new ArrayList<>();
        for(int i=0 ; i<expand_Nodes.size();i++){
            nodeNums.add(expand_Nodes.get(i).getNodeNum());
        }
        return nodeNums;
    }
    
}
