
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class EightMinister extends JFreeChart {
    public EightMinister(Plot plot) {
        super(plot);
        // TODO Auto-generated constructor stub
    }

    static Random rand = new Random();
    private static ArrayList<Float> avrage_of_fitness_in_each_generation = new ArrayList<>();
    private static ArrayList<ArrayList<Integer>> best_chromosomes_in_each_generation = new ArrayList<>();
    private static ArrayList<Integer> best_chromosomes_fitness_in_each_generation = new ArrayList<>();
    private static ArrayList<Integer> chromosomes_fitness_in_each_generation = new ArrayList<>();
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

        System.out.print("Please Enter mutation rate(Note:best number is %5): ");
        int mutationRate = Get_Value.nextInt();
        System.out.println();

        // Generate Initial Generation
        NewGeneration generation = new NewGeneration(n_population, n_Queen);
        generation.generate_Chromosomes();
        fitness1(generation.getGeneration(), n_Queen, literacyrate);

        // Tournoment
        Tournoment(teamSize, n_population, n_Queen, n_new_generation, literacyrate,
                generation, mutationRate);

        String ans = "There is no answer!";
        if (!answers.isEmpty()) {
            System.out.println("The answers are:");
            int gn = avrage_of_fitness_in_each_generation.size() - 1;
            ans = "Answer is in Generation " + String.valueOf(gn);
            for (int i = 0; i < answers.size(); i++) {
                System.out.println("\t" + answers.get(i));
            }
        }
        // chart of average
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < best_chromosomes_fitness_in_each_generation.size(); i++) {
            String generatioString = String.valueOf(i);
            dataset.setValue(avrage_of_fitness_in_each_generation.get(i), "Average of fitness", generatioString);
        }
        String header = "P:" + String.valueOf(n_population) + " G:" +
                String.valueOf(n_new_generation) + " Q:"
                + String.valueOf(n_Queen) + " TS:" + String.valueOf(teamSize) + " LR:" +
                String.valueOf(literacyrate)
                + " MR:" + String.valueOf(mutationRate);
        JFreeChart chart = ChartFactory.createLineChart(header, "Generations",
                "Average of fitness", dataset,
                PlotOrientation.VERTICAL, false, true, false);
        chart.setBackgroundPaint(Color.WHITE);
        CategoryPlot p = chart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.BLACK);
        ChartFrame frame = new ChartFrame("Bar Chart For Average of Each Generation Fitness (Tournoment)", chart);
        frame.setVisible(true);
        frame.setSize(700, 600);

        // chart of fitness of each best chromosome
        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
        for (int i = 0; i < best_chromosomes_fitness_in_each_generation.size(); i++) {
            String generatioString = String.valueOf(i);
            dataset2.setValue(best_chromosomes_fitness_in_each_generation.get(i),
                    "Average of fitness",
                    generatioString);
        }

        JFreeChart chart2 = ChartFactory.createLineChart(ans, "Generations", "Best Fitness", dataset2,
                PlotOrientation.VERTICAL, false, true, false);
        chart2.setBackgroundPaint(Color.WHITE);
        CategoryPlot p2 = chart2.getCategoryPlot();
        p2.setRangeGridlinePaint(Color.BLACK);
        ChartFrame frame2 = new ChartFrame("Bar Chart For Average of Each Generation Fitness (Tournoment)", chart2);
        frame2.setVisible(true);
        frame2.setSize(700, 600);

        // Roulette wheel
        best_chromosomes_fitness_in_each_generation.clear();
        avrage_of_fitness_in_each_generation.clear();
        best_chromosomes_in_each_generation.clear();
        answers.clear();
        fitness2(generation.getGeneration2(), n_Queen, literacyrate, n_population);
        RouletteWheel(n_population, n_Queen, n_new_generation, literacyrate, generation, mutationRate);

        ans = "There is no answer!";
        if (!answers.isEmpty()) {
            System.out.println("The answers are:");
            int gn = avrage_of_fitness_in_each_generation.size() - 1;
            ans = "Answer is in Generation " + String.valueOf(gn);
            for (int i = 0; i < answers.size(); i++) {
                System.out.println("\t" + answers.get(i));
            }
        }

        // chart of average
        DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();
        for (int i = 0; i < best_chromosomes_fitness_in_each_generation.size(); i++) {
            String generatioString = String.valueOf(i);
            dataset1.setValue(avrage_of_fitness_in_each_generation.get(i), "Average of fitness", generatioString);
        }
        String header1 = "P:" + String.valueOf(n_population) + " G:" + String.valueOf(n_new_generation) + " Q:"
                + String.valueOf(n_Queen) + " COR: %75" + " LR:" + String.valueOf(literacyrate)
                + " MR:" + String.valueOf(mutationRate);
        JFreeChart chart1 = ChartFactory.createLineChart(header1, "Generations", "Average of fitness", dataset1,
                PlotOrientation.VERTICAL, false, true, false);
        chart1.setBackgroundPaint(Color.WHITE);
        CategoryPlot p1 = chart1.getCategoryPlot();
        p1.setRangeGridlinePaint(Color.BLACK);
        ChartFrame frame1 = new ChartFrame("Bar Chart For Average of Each Generation Fitness (RuletteWheel)", chart1);
        frame1.setVisible(true);
        frame1.setSize(700, 600);

        // chart of fitness of each best chromosome
        DefaultCategoryDataset dataset3 = new DefaultCategoryDataset();
        for (int i = 0; i < best_chromosomes_fitness_in_each_generation.size(); i++) {
            String generatioString = String.valueOf(i);
            dataset3.setValue(best_chromosomes_fitness_in_each_generation.get(i), "Average of fitness",
                    generatioString);
        }

        JFreeChart chart3 = ChartFactory.createLineChart(ans, "Generations", "Best Fitness", dataset3,
                PlotOrientation.VERTICAL, false, true, false);
        chart3.setBackgroundPaint(Color.WHITE);
        CategoryPlot p3 = chart3.getCategoryPlot();
        p3.setRangeGridlinePaint(Color.BLACK);
        ChartFrame frame3 = new ChartFrame("Bar Chart For Average of Each Generation Fitness (RuletteWheel)", chart3);
        frame3.setVisible(true);
        frame3.setSize(700, 600);

    }

    public static void RouletteWheel(int n_population, int n_Queen, int n_new_generation, int literacyrate,
            NewGeneration generation, int mutationRate) {

        // generations
        for (int g = 1; g < n_new_generation; g++) {

            int totalFitness = chromosomes_fitness_in_each_generation
                    .get(chromosomes_fitness_in_each_generation.size() - 1);
            //System.out.println("total fitness :" + totalFitness);
            chromosomes_fitness_in_each_generation.remove(chromosomes_fitness_in_each_generation.size() - 1);

            //
            if (!answers.isEmpty()) {
                break;
            }
            ArrayList<ArrayList<Integer>> new_generation = new ArrayList<>();
            for (int x = 0; x < literacyrate; x++) {
                new_generation.add(best_chromosomes_in_each_generation.get(x));

            }
            best_chromosomes_in_each_generation.clear();

            // generate population
            for (int p = literacyrate / 2; p < n_population / 2; p++) {

                ArrayList<ArrayList<Integer>> parent1 = new ArrayList<>();
                ArrayList<ArrayList<Integer>> parent2 = new ArrayList<>();
                ArrayList<Integer> new_child = new ArrayList<>();
                ArrayList<Integer> new_child1 = new ArrayList<>();

                // get parent 1
                int randomRoulette = rand.nextInt(totalFitness);
                int index = 0;
                int sum = 0;
                for (int i = 0; i < chromosomes_fitness_in_each_generation.size(); i++) {
                    sum += chromosomes_fitness_in_each_generation.get(i);
                    if (sum >= randomRoulette) {
                        index = i;
                        break;
                    }
                }
                parent1.add(generation.getGeneration2().get(index));

                // get parent 2
                randomRoulette = rand.nextInt(totalFitness);
                index = 0;
                sum = 0;
                for (int i = 0; i < chromosomes_fitness_in_each_generation.size(); i++) {
                    sum += chromosomes_fitness_in_each_generation.get(i);
                    if (sum >= randomRoulette) {
                        index = i;
                        break;
                    }
                }
                parent2.add(generation.getGeneration2().get(index));

                // cross over rate
                int randomCrossRate = rand.nextInt(100);
                if (randomCrossRate <= 75) {
                    int randCross = rand.nextInt(n_Queen - 1) + 1;
                    for (int uu = 0; uu < n_Queen; uu++) {
                        if (uu < randCross) {
                            new_child1.add(parent1.get(0).get(uu));
                            new_child.add(parent2.get(0).get(uu));
                        }

                        if (uu >= randCross) {
                            new_child1.add(parent1.get(0).get(uu));
                            new_child.add(parent2.get(0).get(uu));
                        }

                    }
                    // mutation for child 1
                    int randMutation = rand.nextInt(100);
                    if (mutationRate > randMutation) {
                        int new_gen = rand.nextInt(n_Queen);
                        int gen_place = rand.nextInt(n_Queen);
                        new_child.remove(gen_place);
                        new_child.add(gen_place, new_gen);
                    }
                    // mutation for child 2
                    randMutation = rand.nextInt(100);
                    if (mutationRate > randMutation) {
                        int new_gen = rand.nextInt(n_Queen);
                        int gen_place = rand.nextInt(n_Queen);
                        new_child1.remove(gen_place);
                        new_child1.add(gen_place, new_gen);
                    }

                    new_generation.add(new_child1);
                    new_generation.add(new_child);

                } else {

                    int randMutation = rand.nextInt(100);
                    if (mutationRate < randMutation) {
                        int new_gen = rand.nextInt(n_Queen);
                        int gen_place = rand.nextInt(n_Queen);
                        parent1.get(0).remove(gen_place);
                        parent1.get(0).add(gen_place, new_gen);
                    }
                    new_generation.add(parent1.get(0));
                    // mutation for child 2
                    randMutation = rand.nextInt(100);
                    if (mutationRate < randMutation) {
                        int new_gen = rand.nextInt(n_Queen);
                        int gen_place = rand.nextInt(n_Queen);
                        parent2.get(0).remove(gen_place);
                        parent2.get(0).add(gen_place, new_gen);
                    }
                    new_generation.add(parent2.get(0));

                }
            }
            //System.out.println("new generation number:" + new_generation.size());
            chromosomes_fitness_in_each_generation.clear();
            generation.setnewGeneration2(new_generation);
            new_generation.clear();
            fitness2(generation.getGeneration2(), n_Queen, literacyrate, n_population);

        }
    }

    public static void Tournoment(int teamSize, int n_population, int n_Queen, int n_new_generation,
            int literacyrate, NewGeneration generation, int mutationRate) {
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
            for (int u = literacyrate / 2; u < n_population / 2; u++) {
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
                if (rate < mutationRate) {
                    int new_gen = rand.nextInt(n_Queen);
                    int gen_place = rand.nextInt(n_Queen);
                    new_child.remove(gen_place);
                    new_child.add(gen_place, new_gen);
                }
                rate = rand.nextInt(99);
                if (rate < mutationRate) {
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
            fitness1(generation.getGeneration(), n_Queen, literacyrate);
            // generation_chromosomes1.clear();
            // generation_chromosomes1 = new_generation;
            new_generation.clear();

        }
    }

    // Fitness method1
    public static void fitness1(ArrayList<ArrayList<Integer>> generation_chromosomes, int n_Queen, int literacyrate) {

        // store fitness of each chromosome in generation
        ArrayList<Integer> fitness_of_chromosome = new ArrayList<>();
        // maximum fitness along with its location in arraylist
        int maxFitness = 0;
        // int wich_k = 0;
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
                // wich_k = k;
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

    // Fitness method2
    public static void fitness2(ArrayList<ArrayList<Integer>> generation_chromosomes, int n_Queen, int literacyrate,
            int n_population) {

        // store fitness of each chromosome in generation
        ArrayList<Integer> fitness_of_chromosome = new ArrayList<>();
        // maximum fitness along with its location in arraylist
        int maxFitness = 0;
        int sumOfFitness = 0;
        // int wich_k = 0;
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
            chromosomes_fitness_in_each_generation.add(t_fitness);
            sumOfFitness += t_fitness;
            if (t_fitness > maxFitness) {
                // wich_k = k;
                maxFitness = t_fitness;
            }
            fitness_of_chromosome.add(t_fitness);
            //
            if (n_Queen * 4 == t_fitness) {
                answers.add(generation_chromosomes.get(k));
            }
        }
        chromosomes_fitness_in_each_generation.add(sumOfFitness);
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
