
import org.jfugue.player.Player;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Objects;
import java.util.Random;

/**
 * Created by Anton Skudarnov on 03.11.2017.
 */


public class MusicGenerator {

    private final static int B = 4; //Number Of Bars in Melody
    private final static int C = 4; //Number of Chords in Bar
    private final static int N = 2; //Number of Melody Notes in Chord
    private final static int MIN_NOTE = 48; //Min Note
    private final static int MAX_NOTE = 96; //Max Note
    private static Random randomChord = new Random();
    private static int tonic;
    private static int subdominant;
    private static int dominant;
    private static int[] chords;
    private static String tonality;
    private static int[] gamma;


    //main method for melody creation
    public static void makeMelody() {
        Random tonalityRandom = new Random();
        if (tonalityRandom.nextDouble() < 0.5)
            tonality = "min";
        else
            tonality = "maj";


        // tonality = "maj";
        String melody = "";
        tonic = randomChord.nextInt(MAX_NOTE - MIN_NOTE + 1) + MIN_NOTE;
        // tonic = 60;

        if (tonality.equals("maj")) {
            subdominant = tonic + 5;
            dominant = tonic + 7;
            gamma = new int[]{tonic, tonic + 2, tonic + 4,/*tonic+5,*/ tonic + 7, tonic + 9,/*tonic+11*/};
        } else {
            subdominant = tonic + 5;
            dominant = tonic + 7;
            gamma = new int[]{tonic,/*tonic+2,*/tonic + 3, tonic + 5, tonic + 7,/*tonic+8,*/tonic + 10};
        }
        chords = new int[]{tonic, subdominant, dominant};

        String firstBar = makeTonicBar();
        melody += firstBar;
        for (int i = 0; i < B-2; i++)
            melody += makeBar() + " ";
        melody += firstBar;
        System.out.println(melody);
        Player player = new Player();
        player.play("" + melody);
    }


    private static String makeBar() {


        String bar = "";

        for (int i = 0; i < C; i++) {
            bar += makeChord() + "q+" + makeNote() + "i_" + makeNote() + "i" + " ";
            // bar += makeNote() +" " +  makeNote() + " "; //Melody only
            //bar += makeChord() + " "; //Chords only
        }


        return bar;
    }

    private static String makeTonicBar() {
        String bar = "";
        for (int i = 0; i < C; i++) {
            if (i==3)
            bar += makeChord() + "q+" + makeNote() + "i_" + makeTonicNote() + "i" + " ";
            else
                bar += makeChord() + "q+" + makeNote() + "i_" + makeNote() + "i" + " ";
            //bar +=makeNote() + " " + makeTonicNote() + " "; //Melody Only
            //bar += makeChord() + " "; // Chords Only
        }
        return bar;
    }

    private static String makeChord() {
        String chord = "";
        int currentChord = chords[randomChord.nextInt(chords.length)];
        chord += currentChord + tonality;
        return chord;
    }

    private static String makeNote() {
        String note = "";
        int tempNote = gamma[randomChord.nextInt(gamma.length)];
        note += tempNote + 12;
        return note;
    }

    private static String makeTonicNote() {
        String note = "";
        note += tonic + 12;
        return note;
    }

}
