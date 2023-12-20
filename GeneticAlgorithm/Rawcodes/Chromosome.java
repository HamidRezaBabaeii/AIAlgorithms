package AIAlgorithms.GeneticAlgorithm.Rawcodes;

import java.util.ArrayList;
import java.util.Random;

public class Chromosome {
    Random rand = new Random();
    private int n_queen;
    private ArrayList<Integer> chromosome = new ArrayList<>();   
    public Chromosome(int n_queen){
     this.n_queen = n_queen;
    }

    public ArrayList<Integer> getChromosome() {
        return this.chromosome;
    }

    public void setChromosome(ArrayList<Integer> chromosome) {
        this.chromosome = chromosome;
    }

    public void create_chromosome(){
        for(int i=0 ; i<n_queen ; i++)
        {
            int row = rand.nextInt(n_queen);
            chromosome.add(row);

        }
    }
}
