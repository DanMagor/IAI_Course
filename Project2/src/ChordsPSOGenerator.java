import java.util.Random;

/**
 * Created by Anton Skudarnov on 05.11.2017.
 * This class generate single Chord with PSO algorithm
 */
public class ChordsPSOGenerator {


    private final int C1 = 2, C2 = 2, M = 1; //Constants for PSO

    private final static int P = 10000;//Number of particles


    private final static int MIN_NOTE = 48; //Min Note for Chord
    private final static int MAX_NOTE = 96; //Max Note for Chord
    private final static int N = 3; //Total number of notes in Chord
    private static Random chordRandom;


    public int[] finalChord;
    private String scale;
    private int tonic;
    private int subdominant;
    private int dominant;


    public ChordsPSOGenerator(int tone, String scale) {
        chordRandom = new Random();
        finalChord = new int[3];
        this.scale = scale;
        this.tonic = tone;
        subdominant = tonic + 5;
        dominant = tonic + 7;



    }

    public int[] generateChord() {

        //Particles random creation
        Particle[] particles = new Particle[P];
        for (int i = 0; i < P; i++) {
            particles[i] = new Particle();
        }
        Particle.GlobalBest = particles[0].chord; //Initial Global Best


        //Optimization
        while (calculateFitness(Particle.GlobalBest) != 0) { //If fitness equal 0, it means that we have already reached possible value of notes for chord

            for (Particle p : particles) {
                for (int i = 0; i < 3; i++) {
                    p.speed[i] = (int) (M * p.speed[i] + C1 * chordRandom.nextDouble() * (p.localBest[i] - p.chord[i]) +
                            C2 * chordRandom.nextDouble() * (Particle.GlobalBest[i] - p.chord[i]));
                    p.chord[i] = p.chord[i] + p.speed[i];
                    if (p.chord[i] < MIN_NOTE) p.chord[i] = MIN_NOTE;
                    if (p.chord[i] > MAX_NOTE) p.chord[i] = MAX_NOTE;
                }
                if (calculateFitness(p.chord) < calculateFitness(p.localBest))
                    p.localBest = p.chord;
                if (calculateFitness(p.chord) < calculateFitness(Particle.GlobalBest))
                    Particle.GlobalBest = p.chord;
            }
        }

        for (int i = 0; i < Particle.GlobalBest.length; i++) {
            finalChord[i] = Particle.GlobalBest[i];
        }
        return finalChord;
    }


    private int calculateFitness(int[] particle) {
        int result = 0;

        //Find the closest possible value for the beginning of chord from tonic, subdominant and dominant
        int min = Math.abs(particle[0] - tonic);
        int minGamma = tonic;

        if (Math.abs(particle[0] - subdominant) < min) {
            min = Math.abs(particle[0] - subdominant);
            minGamma = subdominant;
        }
        if (Math.abs(particle[0] - dominant) < min) {
            min = Math.abs(particle[0] - dominant);
            minGamma = dominant;
        }

        result += min;  // min is the difference between the value of the first note and closest possible value.

        if (scale.equals("maj")) {
            result += Math.abs(particle[1] - (minGamma + 4)); //The difference between the "good" values for the second and third notes and real values
            result += Math.abs(particle[2] - (minGamma + 7)); // for major scale
            return result;
        }

        result += Math.abs(particle[1] - (minGamma + 3));  //The same difference for min scale
        result += Math.abs(particle[2] - (minGamma + 7));
        return result;

    }


    private static class Particle {


        public int chord[];  // all(3) Notes of Chord
        public int localBest[];
        public int speed[];
        public static int[] GlobalBest;

        //Create Particle with Random Chord
        public Particle() {
            chord = new int[N];
            localBest = new int[N];
            speed = new int[N];
            int board = MAX_NOTE - MIN_NOTE + 1;
            for (int i = 0; i < 3; i++) {
                chord[i] = chordRandom.nextInt(board) + MIN_NOTE;
                localBest[i] = chord[i];
                speed[i] = 0;
            }
        }
    }


}
