import java.util.Random;

/**
 * Created by Anton Skudarnov on 05.11.2017.
 */
public class ChordsPSOGenerator {


    private final int C1 = 2, C2 = 2, M = 1;

    private final static int P = 10000; //Number of particles
  //  private final static int I = 100; //Number of Iterations

    private final static int MIN_NOTE = 48; //Min Note for Chord
    private final static int MAX_NOTE = 96; //Max Note for Chord
//    private final int C = 16; //Total number of chords
    private static Random chordRandom;


    public int[] finalChord;
    private String tonality;
    private int tonic;
    private int subdominant;
    private int dominant;
    private int[] gamma;

    public ChordsPSOGenerator(int tone, String tonality) {
        chordRandom = new Random();
        finalChord = new int[3];
        this.tonality = tonality;
        this.tonic = tone;
        subdominant = tonic + 5;
        dominant = tonic + 7;

        gamma = new int[]{};
        if (tonality.equals("maj")) {
            gamma = new int[]{tonic, tonic + 2, tonic + 4,/*tonic+5,*/ tonic + 7, tonic + 9,/*tonic+11*/};
        } else {
            gamma = new int[]{tonic,/*tonic+2,*/tonic + 3, tonic + 5, tonic + 7,/*tonic+8,*/tonic + 10};
        }





    }

    public int[] generateChord(){
        Particle[] particles = new Particle[P];
        for (int i = 0; i < P; i++) {
            particles[i] = new Particle();
        }
        Particle.GlobalBest = particles[0].chord;


        //Optimization;

        while (calculateFitness(Particle.GlobalBest)!=0) {
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

//        for (int g : gamma) {
//            int difference = Math.abs(particle[0] - g);
//            if (difference < min) {
//                min = difference;
//                minGamma = g;
//            }
//        }
        result += min;
        if (tonality.equals("maj")) {
            result += Math.abs(particle[1] - (minGamma + 4));
            result += Math.abs(particle[2] - (minGamma + 7));
            return result;
        }
        result += Math.abs(particle[1] - (minGamma + 3));
        result += Math.abs(particle[2] - (minGamma + 7));
        return result;

    }


    private static class Particle {


        public int chord[];
        public int localBest[];
        public int speed[];
        public static int[] GlobalBest;

        //Create Particle with Random Chord
        public Particle() {
            chord = new int[3];
            localBest = new int[3];
            speed = new int[3];
            int board = MAX_NOTE - MIN_NOTE + 1;
            for (int i = 0; i < 3; i++) {
                chord[i] = chordRandom.nextInt(board) + MIN_NOTE;
                localBest[i] = chord[i];
                speed[i] = 0;
            }
        }
    }




}
