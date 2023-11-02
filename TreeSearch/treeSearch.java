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

        if(proximitySize>0){ // if we have at least one node (it'll be root)
           System.out.println("We don't have any node.");
           return;
        }
        
        // create new object from Fringe
        Fringe fringe;
        Integer num = null;
        fringe = new Fringe(0, null, num, num, 0, 0); // the root node 

        
        // check fringe
        while(!fringe.get_Fringe().isEmpty())
        {
            
        }
    }

}