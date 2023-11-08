package AIAlgorithms.TreeSearch;

import java.util.ArrayList;

public class Expand {

    // variables 
    private ArrayList<Node> expand_Nodes = new ArrayList<Node>();
    private int counterDepth = 1 ;

    //constructor
    public Expand(Node node , int[][] proximityArray){
        this.expand_Nodes.add(node);
        for(int i=0 ; i< proximityArray.length; i++){
            if(proximityArray[(int)node.getNodeState()-48][i]!=0){
                new Fringe( (char)(i), node, proximityArray[(int)node.getNodeState()-48][i], counterDepth);
            }
        }
        counterDepth++;
    }

    //null constructor
    public Expand(){
        
    }

    // return nodes
    public  ArrayList<Node> show_expandNodes(){
        return expand_Nodes;
    }

    // returns node nums
    public  ArrayList<Character> show_expandNodes_numbers(){

        ArrayList<Character> nodeNums = new ArrayList<>();
        for(int i=0 ; i<expand_Nodes.size();i++){
            nodeNums.add(expand_Nodes.get(i).getNodeState());
        }
        return nodeNums;
    }
    
}
