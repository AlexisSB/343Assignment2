
import cosc343.assig2.Creature;
import java.util.*;

/**
 * The MyCreate extends the cosc343 assignment 2 Creature. Here you implement
 * creatures chromosome and the agent function that maps creature percepts to
 * actions.
 *
 * @author
 * @version 1.0
 * @since 2017-04-05
 */
public class MyCreature extends Creature {

    // Random number generator
    Random rand = new Random();
    int chromosomeLength;
    private int[] chromosome;

    /* Empty constructor - might be a good idea here to put the code that 
   initialises the chromosome to some random state   
  
   Input: numPercept - number of percepts that creature will be receiving
          numAction - number of action output vector that creature will need
                      to produce on every turn
     */
    public MyCreature(int numPercepts, int numActions) {
        //int rowLength = (int) Math.pow(3, numPercepts);
        //intiailse length of chromosome
        this.chromosomeLength = (int) Math.pow(3, numPercepts) * numActions;

        this.chromosome = new int[this.chromosomeLength];
        for (int row = 0; row < this.chromosome.length; row++) {
            this.chromosome[row] = rand.nextInt(numActions);
            //System.out.println(chromosome[row][col]);
        }

        //System.out.println((Arrays.toString(chromosome)));
    }

    public MyCreature(int[] chromosome) {
        this.chromosome = chromosome;
        this.chromosomeLength = chromosome.length;
    }

    /* This function must be overridden by MyCreature, because it implements
     the AgentFunction which controls creature behavoiur.  This behaviour
     should be governed by a model (that you need to come up with) that is
     parameterise by the chromosome.  
  
     Input: percepts - an array of percepts
            numPercepts - the size of the array of percepts depend on the percept
                          chosen
            numExpectedAction - this number tells you what the expected size
                                of the returned array of percepts should bes
     Returns: an array of actions 
     */
    @Override
    public float[] AgentFunction(int[] percepts, int numPercepts, int numExpectedActions) {

        // This is where your chromosome gives rise to the model that maps
        // percepts to actions.  This function governs your creature's behaviour.
        // You need to figure out what model you want to use, and how you're going
        // to encode its parameters in a chromosome.
        // At the moment, the actions are chosen completely at random, ignoring
        // the percepts.  You need to replace this code.
        float actions[] = new float[numExpectedActions];
        int[] perceptsCopy = Arrays.copyOf(percepts, percepts.length);

        for (int i = 0; i < perceptsCopy.length; i++) {
            perceptsCopy[i]++;
        }
        //System.out.println(Arrays.toString(perceptsCopy));

        StringBuilder perceptString = new StringBuilder();
        StringBuilder monsterString = new StringBuilder();
        StringBuilder foodString = new StringBuilder();
        StringBuilder creatureString = new StringBuilder();

        for (int i = 0; i < numPercepts; i++) {
            perceptString.append(perceptsCopy[i]);
        }

        for (int i = 0; i <= 1; i++) {
            monsterString.append(perceptsCopy[i]);
        }
        //System.out.println(monsterString);
        for (int i = 2; i <= 3; i++) {
            creatureString.append(perceptsCopy[i]);
        }
        //System.out.println(creatureString);
        for (int i = 4; i <= 5; i++) {
            foodString.append(perceptsCopy[i]);
        }
        //System.out.println(foodString);

        //codes return value from 0 to 8 depending on location of nearest monster, creature, food etc.
        int monsterCode = Integer.parseInt(monsterString.toString(), 3);
        int creatureCode = Integer.parseInt(creatureString.toString(), 3);
        int foodCode = Integer.parseInt(foodString.toString(), 3);
        int perceptCode = Integer.parseInt(perceptString.toString(), 3);

        //System.out.println(monsterCode);
        //System.out.println(creatureCode);
        //System.out.println(foodCode);
        //check monsters
        int offset = 0;
        if (monsterCode != 4) {
            int actionCode = this.chromosome[monsterCode + offset];
            System.out.println(actionCode);
            actions = Actions.pickAction(actionCode);
            

        } else {
            if (foodCode != 4) {
                offset = 10;
                
            }else{
                offset = 20;
                int green = 6;
                int red = 7;
                if (percepts[green]==2){
                    int actionCode = this.chromosome[foodCode + offset];
                    actions = Actions.pickAction(actionCode);
                    
                }
            }
        }

        /*
        if (monsterCode != 4) {
            actions = chromosome[monsterCode];
        } else {
            //check if food is on square and what state, if no food check creatures
            if (foodCode == 4) {
                if (perceptsCopy[6] == 2) {
                    actions = chromosome[9];
                } else {
                    if (perceptsCopy[7] == 2) {
                        actions = chromosome[10];

                    } else {
                        //check creatures
                        if (creatureCode != 4) {
                            actions = chromosome[creatureCode + 11];
                        } else {
                            //all zeros case
                            actions = chromosome[20];
                        }
                    }
                }

            } else {
                //check if other food around
                actions = chromosome[foodCode + 21];

            }
        }
         */
        return actions;
    }

    public void printChromosome() {
        System.out.println("Chromosome : ");
        System.out.println(Arrays.toString(chromosome));
        System.out.println();

    }

    public int[] getChromosome() {
        int[] chromosomeCopy = new int[this.chromosomeLength];
        for (int gene = 0; gene < this.chromosomeLength; gene++) {
            chromosomeCopy[gene] = this.chromosome[gene];
        }
        return chromosomeCopy;
    }

    public int getChromosomeAt(int index) {
        return chromosome[index];
    }

    public void setChromosome(int[] chromosome) {
        this.chromosome = chromosome;
    }

    public void setChromosome(int value, int index) {
        this.chromosome[index] = value;
    }
}
