package AIAlgorithms.TreeSearch;

import java.util.ArrayList;
import java.util.Scanner;

//BFS 

public class treeSearch {

    public static void main(String[] args) {
        Scanner getValue = new Scanner(System.in);
        // get matrix size
        System.out.print("Enter number of Nodes:");
        int proximitySize = getValue.nextInt();
        System.out.println();

        // define matrix
        int proximityArray[][] = new int[proximitySize][proximitySize];

        // initialization matrix
        int proxSize = proximitySize - 1;
        System.out.println("Please fill matrix rows and columns, It starts from (0,0) to (" + proxSize + ","
                + proxSize + ") :");
        for (int i = 0; i < proximitySize; i++) {
            System.out.print("Enter new row :");
            for (int j = 0; j < proximitySize; j++) {
                proximityArray[i][j] = getValue.nextInt();
            }
            System.out.println();
        }

        // show matrix
        for (int i = 0; i < proximitySize; i++) {
            for (int j = 0; j < proximitySize; j++) {
                System.out.print(proximityArray[i][j] + "\t");
            }
            System.out.print("\n");
        }

        // check the matrix size
        if (proximitySize == 0) { // if we have at least one node (it'll be root)
            System.out.println();
            System.out.println("We don't have any node.");
            return;
        }

        // show node names then select one of them for Start and another one for Goal.
        show_Nodes(proximitySize);
        System.out.print("Give Start node:");
        int start_node = getValue.nextInt();
        System.out.print("\nGive Goal node:");
        int goal_node = getValue.nextInt();
        System.out.println();

        // create new object from Fringe
        Fringe fringe;
        fringe = new Fringe(start_node, null, 0, 0); // the root node

        // check
        Expand expand = new Expand();

        // check fringe
        while (!fringe.get_Fringe().isEmpty()) {
            Node test_Node = fringe.get_Fringe().get(0);
            fringe.get_Fringe().remove(0);
            if (goal_node == test_Node.getNodeState()) {
                // show expanded nodes
                show_expandedNodes(expand);
                // show path
                Node path = test_Node;
                show_path(path);
                break;

            } else {
                expand.add_expand(test_Node, proximityArray, fringe);
            }

        }
    }

    public static void show_Nodes(int matrixSize) {
        System.out.println();
        int char_Start = 65;
        for (int i = 0; i < matrixSize; i++) {
            System.out.print("Node " + i + " = " + (char) (char_Start + i) + "\t");
            if (i != 0 && i % 5 == 0) {
                System.out.println("");
            }
        }
        System.out.println("Please First Select Start Node Number Then Select Goal Node Number:");
    }

    public static void show_expandedNodes(Expand expand){
        System.out.println("\nShow expanded nodes:");
        for (int i = 0; i < expand.show_expandNodes_state().size(); i++) {
            System.out.print(expand.show_expandNodes_state().get(i));
            if(i!=expand.show_expandNodes_state().size()-1){
                System.out.print("-->");
            }
        }
        System.out.println("");
    }

    public static void show_path(Node path){
        System.out.println("\nShow path:");
        ArrayList<Node> full_path = new ArrayList<>();
        while (path != null) {
            full_path.add(path);
            path = path.getNodeParent();
        }
        for(int i=full_path.size()-1; i>=0 ; i--){
            System.out.print("Node:"+full_path.get(i).getNodeState());
            if(i!=0){
                System.out.print("-->");
            }
        }
        System.out.println();
    }
}