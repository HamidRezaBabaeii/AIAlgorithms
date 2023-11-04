package AIAlgorithms.TreeSearch;

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
        if(proximitySize==0){ // if we have at least one node (it'll be root)
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
        //Integer num = null;
        int start_node_actions[] = new int[proximitySize];
        for(int i=0 ; i<proximitySize ; i++){
            start_node_actions[i] = proximityArray[start_node][i];
        }
        fringe = new Fringe(start_node, null, -1, start_node_actions, 0, 0); // the root node 

        
        //check fringe
        while(!fringe.get_Fringe().isEmpty())
        {
            System.out.println("true");
            break;
            
        }
    }


    public static void show_Nodes(int matrixSize){
        System.out.println();
        int char_Start = 65;
        for(int i=0 ; i < matrixSize ; i++){
            System.out.print("Node "+i+" = "+(char)(char_Start+i)+"\t");
            if(i!=0 && i%5==0){
                System.out.println("");
            }
        }
        System.out.println("Please First Select Start Node Number Then Select Goal Node Number:");
    }

}