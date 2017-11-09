import java.util.Random;

/**
 * Created by Anton Skudarnov on 05.11.2017.
 * This class generate Two Melody Notes with PSO algorithm
 */
public class MelodyPSOGenerator {

    private final int C1 = 2, C2 = 2, M = 1; //PSO Constants

    private final static int P = 10000; //Number of particles


    private final static int MIN_NOTE = 48; //Min Note for Melody
    private final static int MAX_NOTE = 96; //Max Note for Melody
    private final static int N = 2; //Number of Notes in Melody
    private static Random melodyRandom; //Random generator for melody


    public int[] finalMelody;

    private int tonic;

    private int[] gamma; //the gamma of possible notes

    public MelodyPSOGenerator(int tone, String scale) {
        melodyRandom = new Random();
        finalMelody = new int[2];
        this.tonic = tone;


         //gamma generation
        if (scale.equals("maj")) {
            gamma = new int[]{tonic, tonic + 2, tonic + 4, tonic + 7, tonic + 9};
        } else {
            gamma = new int[]{tonic, tonic + 3, tonic + 5, tonic + 7, tonic + 10};
        }


    }

    public int[] generateMelody() {
        Particle[] particles = new Particle[P];
        for (int i = 0; i < P; i++) {
            particles[i] = new Particle();
        }
        Particle.GlobalBest = particles[0].chord;  //Initial Global Best


        //Optimization;

        while (calculateFitness(Particle.GlobalBest) != 0) { //If fitness equal 0, it means that we have already reached possible value of notes
            for (Particle p : particles) {
                for (int i = 0; i < 2; i++) {
                    p.speed[i] = (int) (M * p.speed[i] + C1 * melodyRandom.nextDouble() * (p.localBest[i] - p.chord[i]) +
                            C2 * melodyRandom.nextDouble() * (Particle.GlobalBest[i] - p.chord[i]));
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
            int N = melodyRandom.nextInt(2) + 1;
            finalMelody[i] = Particle.GlobalBest[i] + 12 * N; //The same note from the one of the two next Octaves
        }
        return finalMelody;
    }


    private int calculateFitness(int[] particle) {
        int result = 0;

        for (int i = 0; i < N; i++) {
            int min = Math.abs(particle[i] - tonic);
            for (int g : gamma) {
                int difference = Math.abs(particle[i] - g);  //Check wat is the closest values of note from gamma
                if (difference < min) {
                    min = difference;
                }
            }
            result += min; // min is the difference between the value of note and closest possible value.
        }
        return result;

    }


    private static class Particle {


        public int chord[];
        public int localBest[];
        public int speed[];
        public static int[] GlobalBest;

        //Create Particle with Random Chord
        public Particle() {
            chord = new int[N];
            localBest = new int[N];
            speed = new int[N];
            int board = MAX_NOTE - MIN_NOTE + 1;
            for (int i = 0; i < N; i++) {
                chord[i] = melodyRandom.nextInt(board) + MIN_NOTE;
                localBest[i] = chord[i];
                speed[i] = 0;
            }
        }
    }


}
