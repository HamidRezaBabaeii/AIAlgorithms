package AIAlgorithms.GeneticAlgorithm;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;



public class EightMinister2 {
    static Random rand = new Random();
    private static ArrayList<Float> avrage_of_fitness_in_each_generation = new ArrayList<>();
    private static ArrayList<ArrayList<Integer>> best_chromosomes_in_each_generation = new ArrayList<>();
    private static ArrayList<Integer> best_chromosomes_fitness_in_each_generation = new ArrayList<>();
    private static ArrayList<ArrayList<Integer>> answers = new ArrayList<>();

    public static void main(String[] args) {
        Scanner Get_Value = new Scanner(System.in);

        // GET INITIAL POPULATION
        System.out.print("Please Enter the number of initial population:");
        int n_population = Get_Value.nextInt();
        System.out.println();
        if (n_population % 2 != 0) {
            n_population += 1;
        }

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

        // select literacy rate
        System.out.print("Please Enter literacy rate(Note:should select %2 to %3 of population): ");
        int literacyrate = Get_Value.nextInt();
        System.out.println();
        if (literacyrate % 2 != 0) {
            literacyrate -= 1;
        }
        // Generate Initial Generation
        NewGeneration generation = new NewGeneration(n_population, n_Queen);
        generation.generate_Chromosomes();
        fitness(generation.getGeneration(), n_Queen, literacyrate);

        // loop for create nth new generation

        for (int i = 1; i < n_new_generation; i++) {

            if (!answers.isEmpty()) {
                break;
            }

            // selection level
            ArrayList<ArrayList<Integer>> tournomentTeam = new ArrayList<>();
            ArrayList<ArrayList<Integer>> new_generation = new ArrayList<>();

            // create tournoment team
            for (int u = 1; u <= teamSize; u++) {
                int index = rand.nextInt(n_population);
                tournomentTeam.add(generation.getGeneration().get(index)); // error
            }
            // select two random chromosome from tournomentTeam

            // under line is literacy rate, in this solution is %1 from 100 population.
            for (int x = 0; x < literacyrate; x++) {
                new_generation.add(best_chromosomes_in_each_generation.get(x));
            }
            best_chromosomes_in_each_generation.clear();
            // we should clear() generation_chromosomes array list
            // generation_chromosomes.clear();

            // add 2 to 100 chromosome via cross over method
            for (int u = literacyrate/2; u < n_population / 2; u++) {
                ArrayList<Integer> new_child = new ArrayList<>();
                ArrayList<Integer> new_child1 = new ArrayList<>();
                ArrayList<ArrayList<Integer>> two_parent_chromosome = new ArrayList<>();
                for (int uu = 0; uu < 2; uu++) {
                    int index = rand.nextInt(teamSize);
                    two_parent_chromosome.add(tournomentTeam.get(index));
                }
                // cross over --> I changed it instead of create two new chromosome I create one
                // new chromosome from 2 parent
                int crossOverPoint = rand.nextInt(n_Queen - 1) + 1;
                for (int uu = 0; uu < n_Queen; uu++) {
                    if (uu < crossOverPoint) {
                        new_child1.add(two_parent_chromosome.get(1).get(uu));
                        new_child.add(two_parent_chromosome.get(0).get(uu));
                    }

                    if (uu >= crossOverPoint) {
                        new_child1.add(two_parent_chromosome.get(0).get(uu));
                        new_child.add(two_parent_chromosome.get(1).get(uu));
                    }

                }

                // mutation
                int rate = rand.nextInt(99);
                if (rate < 5) {
                    int new_gen = rand.nextInt(n_Queen);
                    int gen_place = rand.nextInt(n_Queen);
                    new_child.remove(gen_place);
                    new_child.add(gen_place, new_gen);
                }
                rate = rand.nextInt(99);
                if (rate < 5) {
                    int new_gen = rand.nextInt(n_Queen);
                    int gen_place = rand.nextInt(n_Queen);
                    new_child1.remove(gen_place);
                    new_child1.add(gen_place, new_gen);
                }

                // add new child to new generation
                new_generation.add(new_child);            
                new_generation.add(new_child1);
                two_parent_chromosome.clear();
            }
            generation.setnewGeneration(new_generation);
            fitness(generation.getGeneration(), n_Queen, literacyrate);
            // generation_chromosomes1.clear();
            // generation_chromosomes1 = new_generation;
            new_generation.clear();

        }


        if (!answers.isEmpty()) {
            System.out.println("The answers are:");

            for (int i = 0; i < answers.size(); i++) {
                System.out.println("\t" + answers.get(i));
            }
        }
        System.out.println("");
        for (int i = 0; i < best_chromosomes_fitness_in_each_generation.size(); i++) {
            System.out.println("Generation " + (i + 1) + ":");
            System.out.println("\tAverage of fitness: " + avrage_of_fitness_in_each_generation.get(i));
            System.out.println("\tBest chromosome : " + best_chromosomes_fitness_in_each_generation.get(i));

        }

    }

    public static void fitness(ArrayList<ArrayList<Integer>> generation_chromosomes, int n_Queen, int literacyrate) {

        // store fitness of each chromosome in generation
        ArrayList<Integer> fitness_of_chromosome = new ArrayList<>();
        // maximum fitness along with its location in arraylist
        int maxFitness = 0;
        int wich_k = 0;
        int array[][] = new int[generation_chromosomes.size()][2];
        for (int k = 0; k < generation_chromosomes.size(); k++) {
            ArrayList<Integer> row = new ArrayList<>();
            ArrayList<Integer> column = new ArrayList<>();
            ArrayList<Integer> northEastDiagonal = new ArrayList<>();
            ArrayList<Integer> southEastDiagonal = new ArrayList<>();
            for (int i = 0; i < generation_chromosomes.get(k).size(); i++) {
                if (!row.contains(generation_chromosomes.get(k).get(i))) {
                    row.add(generation_chromosomes.get(k).get(i));
                }
                if (!column.contains(i)) {
                    row.add(generation_chromosomes.get(k).get(i));
                }
                if (!southEastDiagonal.contains(generation_chromosomes.get(k).get(i) + i)) {
                    southEastDiagonal.add(generation_chromosomes.get(k).get(i) + i);
                }
                if (!northEastDiagonal.contains(n_Queen - 1 + generation_chromosomes.get(k).get(i) - i)) {
                    northEastDiagonal.add(n_Queen - 1 + generation_chromosomes.get(k).get(i) - i);
                }
            }

            int t_fitness = row.size() + northEastDiagonal.size() + southEastDiagonal.size() + column.size();
            array[k][0] = k;
            array[k][1] = t_fitness;
            if (t_fitness > maxFitness) {
                wich_k = k;
                maxFitness = t_fitness;
            }
            fitness_of_chromosome.add(t_fitness);
            //
            if (n_Queen * 4 == t_fitness) {
                answers.add(generation_chromosomes.get(k));
            }
        }

        // sort chromosomes
        for (int x = 0; x < generation_chromosomes.size(); x++) {
            for (int xx = x; xx < generation_chromosomes.size(); xx++) {

                if (array[x][1] < array[xx][1]) {
                    for (int j = 0; j < 2; j++) {
                        int temp = array[x][j];
                        array[x][j] = array[xx][j];
                        array[xx][j] = temp;
                    }
                }
            }
        }

        best_chromosomes_fitness_in_each_generation.add(maxFitness);

        for (int x = 0; x < literacyrate; x++) {
            best_chromosomes_in_each_generation.add(generation_chromosomes.get(array[x][0]));
        }

        float average = 0;
        for (int k = 0; k < generation_chromosomes.size(); k++) {
            average += fitness_of_chromosome.get(k);
        }
        avrage_of_fitness_in_each_generation.add(average / fitness_of_chromosome.size());
        fitness_of_chromosome.clear();
    }
}
