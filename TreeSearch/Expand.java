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
        for(int i=0 ; i< node.getNodeAction().size(); i++){
            if(node.getNodeAction().get(i)!=0){
                ArrayList<Integer> node_actions = new ArrayList<>();
                for(int j=0 ; j<proximityArray.length ; j++){
                    node_actions.add(proximityArray[counterNodeNumber][j]);
                }
                new Fringe(counterNodeNumber, (char)(i), node.getNodeNum(), node_actions, node.getNodeAction().get(i), counterDepth);
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
