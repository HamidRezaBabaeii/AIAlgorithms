
import java.util.ArrayList;

public class NewGeneration {

    private ArrayList<ArrayList<Integer>> chromosomes = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> generation = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> generation2 = new ArrayList<>();
    private int n_chromosome;
    private int n_queen;

    public NewGeneration(int n_chromosome, int n_queen) {
        this.n_chromosome = n_chromosome;
        this.n_queen = n_queen;
    }

    // get generation
    public ArrayList<ArrayList<Integer>> getGeneration() {
        return this.generation;
    }

    // set generation
    public void setGeneration(ArrayList<ArrayList<Integer>> generation) {
        this.generation = generation;
    }

    // set new generation

    public void setnewGeneration(ArrayList<ArrayList<Integer>> generation) {
        this.generation.clear();
        for (int i = 0; i < generation.size(); i++) {
            this.generation.add(generation.get(i));
        }

    }

    // get generation2
    public ArrayList<ArrayList<Integer>> getGeneration2() {
        return this.generation2;
    }

    // set generation2
    public void setGeneration2(ArrayList<ArrayList<Integer>> generation) {
        this.generation2 = generation;
    }

        
    // set new generation2
    public void setnewGeneration2(ArrayList<ArrayList<Integer>> generation) {
        this.generation2.clear();
        for (int i = 0; i < generation.size(); i++) {
            this.generation2.add(generation.get(i));
        }

    }


    // generate chromosomes or generation
    public void generate_Chromosomes() {
        for (int i = 0; i < n_chromosome; i++) {
            Chromosome chromosome = new Chromosome(n_queen);
            chromosome.create_chromosome();
            chromosomes.add(chromosome.getChromosome());
        }

        for (int i = 0; i < chromosomes.size(); i++) {
            generation.add(chromosomes.get(i));
            generation2.add(chromosomes.get(i));
        }

        chromosomes.clear();
    }
}
