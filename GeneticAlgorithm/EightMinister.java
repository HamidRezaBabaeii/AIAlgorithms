package AIAlgorithms.GeneticAlgorithm;

import java.util.ArrayList;
import java.util.Scanner;

public class EightMinister {

    private static ArrayList<Float> avrage_of_fitness_in_each_generation = new ArrayList<>();
    private static ArrayList<ArrayList<Integer>> best_chromosome_in_each_generation = new ArrayList<>();
    private static ArrayList<ArrayList<Integer>> generation_chromosomes = new ArrayList<>();

    public static void main(String[] args) {
        Scanner Get_Value = new Scanner(System.in);

        // GET INITIAL POPULATION
        System.out.print("Please Enter the number of initial population:");
        int n_population = Get_Value.nextInt();
        System.out.println();

        // GET NUMBER OF QUEENS
        System.out.print("Please Enter the number of QUEENS:");
        int n_Queen = Get_Value.nextInt();
        System.out.println();

        // IF WE CAN'T FIND SOLUTION, SHOULD GO TO Nth GENERATION
        System.out.print("How many generation you want to generate? ");
        int n_new_generation = Get_Value.nextInt();
        System.out.println();

        // Generate Initial Generation
        NewGeneration generation = new NewGeneration(n_population, n_Queen);
        generation.generate_Chromosomes();
        generation_chromosomes = generation.getGeneration();
        fitness(generation_chromosomes, n_Queen);

        // loop for create nth new generation
        for(int i=2 ; i<=n_new_generation ; i++)
        {
            
        }


 
    }

    public static void fitness(ArrayList<ArrayList<Integer>> generation_chromosomes, int n_Queen) {

        // store fitness of each chromosome in generation
        ArrayList<Integer> fitness_of_chromosome = new ArrayList<>();
        // maximum fitness along with its location in arraylist
        int maxFitness = 0;
        int wich_k = 0;

        for (int k = 0; k < generation_chromosomes.size(); k++) {
            ArrayList<Integer> row = new ArrayList<>();
            ArrayList<Integer> northEastDiagonal = new ArrayList<>();
            ArrayList<Integer> southEastDiagonal = new ArrayList<>();
            for (int i = 0; i < generation_chromosomes.get(k).size(); i++) {
                if (!row.contains(generation_chromosomes.get(k).get(i))) {
                    row.add(generation_chromosomes.get(k).get(i));
                }
                if (!southEastDiagonal.contains(generation_chromosomes.get(k).get(i) + i)) {
                    southEastDiagonal.add(generation_chromosomes.get(k).get(i) + i);
                }
                if (!northEastDiagonal.contains(n_Queen - 1 + generation_chromosomes.get(k).get(i) - i)) {
                    northEastDiagonal.add(n_Queen - 1 + generation_chromosomes.get(k).get(i) - i);
                }
            }

            int t_fitness = row.size() + northEastDiagonal.size() + southEastDiagonal.size();
            if (t_fitness > maxFitness) {
                wich_k = k;
                maxFitness = t_fitness;
            }
            fitness_of_chromosome.add(t_fitness);
        }
        
        //
        if(n_Queen*3 == maxFitness){
            System.out.println("The answer is:");
            System.out.println(generation_chromosomes.get(wich_k));
            System.out.println("This chromosome is answer of problem");
            return;
        }
        best_chromosome_in_each_generation.add(generation_chromosomes.get(wich_k));

        float average = 0;
        for (int k = 0; k < generation_chromosomes.size(); k++) {
            average += fitness_of_chromosome.get(k);
        }
        avrage_of_fitness_in_each_generation.add(average / fitness_of_chromosome.size());
        fitness_of_chromosome.clear();
    }
}


        // System.out.println(generation.getGeneration());
        // for(int k=0 ; k<generation.getGeneration().size() ;k++)
        // {
        // System.out.println("Chromosome:"+k);
        // for(int i=0 ; i<generation.getGeneration().get(k).size();i++)
        // {
        // System.out.println("\trow:"+generation.getGeneration().get(k).get(i)+",
        // column"+i);
        // }
        // }