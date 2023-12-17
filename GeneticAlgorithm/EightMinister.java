package AIAlgorithms.GeneticAlgorithm;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class EightMinister {
    static Random rand = new Random();
    private static ArrayList<Float> avrage_of_fitness_in_each_generation = new ArrayList<>();
    private static ArrayList<ArrayList<Integer>> best_chromosome_in_each_generation = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> generation_chromosomes = new ArrayList<>();

    public static void main(String[] args) {
        Scanner Get_Value = new Scanner(System.in);
        //ArrayList<ArrayList<Integer>> generation_chromosomes = new ArrayList<>();
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

        // IF WE CAN'T FIND SOLUTION, SHOULD GO TO Nth GENERATION
        System.out.print("Please Enter TeamSize(Note:should select %2 to %3 of population): ");
        int teamSize = Get_Value.nextInt();
        System.out.println();

        // Generate Initial Generation
        NewGeneration generation = new NewGeneration(n_population, n_Queen);
        generation.generate_Chromosomes();    
        fitness(generation.getGeneration(), n_Queen);

        // loop for create nth new generation
    
        for (int i = 1; i < n_new_generation; i++) {
            
            // selection level
            ArrayList<ArrayList<Integer>> tournomentTeam = new ArrayList<>();           
            ArrayList<ArrayList<Integer>> new_generation = new ArrayList<>();
            
            
            // create tournoment team
            for(int u=1 ; u<=teamSize ; u++)
            {
                int index = rand.nextInt(n_population);
                tournomentTeam.add(generation.getGeneration().get(index)); // error
            }
            // select two random chromosome from tournomentTeam

                // under line is literacy rate, in this solution is %1 from 100 population.
                new_generation.add(best_chromosome_in_each_generation.get(i-1));
                //  we should clear() generation_chromosomes array list
                //generation_chromosomes.clear();

            // add 2 to 100 chromosome via cross over method
            for(int u=1 ; u<n_population ; u++)
            {
                ArrayList<Integer> new_child = new ArrayList<>();
                ArrayList<ArrayList<Integer>> two_parent_chromosome = new ArrayList<>();
                for(int uu=0 ; uu<2 ; uu++)
                {
                    int index = rand.nextInt(teamSize);
                    two_parent_chromosome.add(tournomentTeam.get(index));
                }
                // cross over --> I changed it instead of create two new chromosome I create one new chromosome from 2 parent
                int crossOverPoint = rand.nextInt(n_Queen);
                for(int uu=0 ; uu<n_Queen ; uu++)
                {
                    if(uu < crossOverPoint)
                        new_child.add(two_parent_chromosome.get(0).get(uu));
                    
                    if(uu >= crossOverPoint)
                        new_child.add(two_parent_chromosome.get(1).get(uu));
                }

                // mutation
                int new_gen = rand.nextInt(n_Queen);
                int gen_place = rand.nextInt(n_Queen);
                new_child.remove(gen_place);
                new_child.add(gen_place, new_gen);

                // add new child to new generation
                new_generation.add(new_child);
                two_parent_chromosome.clear();  
            }
            generation.setnewGeneration(new_generation);
            fitness(generation.getGeneration(), n_Queen);
            // generation_chromosomes1.clear();
            // generation_chromosomes1 = new_generation;
            System.out.println(best_chromosome_in_each_generation.get(0) + " / " + best_chromosome_in_each_generation.get(1));
            new_generation.clear();

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
        if (n_Queen * 3 == maxFitness) {
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