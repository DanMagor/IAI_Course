import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

import java.io.File;
import java.io.IOException;
import java.util.Random;


/**
 * The main class of programm
 * It calls the methods of Chord and Melody Generators,
 * writes result in MIDI file and plays the music
 */
public class Main {

    public static void main(String[] args) {


        final int MAX_CHORD = 72;   //The max Chord for tonic. The value higher than 72 generates too high melody
        final int MIN_CHORD = 48;
        final String FILE_NAME = "AntonSkudarnov.midi";


        //initialization
        String melody = "";
        String scale = "min"; // scale initialization
        Random random = new Random(); //random for tonic generation

        //tonic random generation
        int tonic = random.nextInt(MAX_CHORD - MIN_CHORD + 1) + MIN_CHORD;  //
        if(random.nextDouble()<0.5){
            scale = "maj";
        }


        //Music generation
        for (int i = 0; i < 16; i++) {

            ChordsPSOGenerator chord = new ChordsPSOGenerator(tonic, scale); //Chords generator(PSO 1)
            MelodyPSOGenerator notes = new MelodyPSOGenerator(tonic,scale); //Melody generator(PSO 2)

            int j = 0;      //The counter of channel (V0, V1, ...)
            for (int noteOfChord:chord.generateChord()) {  //generate single Chord


                melody += "V" + j +" " + noteOfChord + "q ";
                j++;
            }
            long start = System.nanoTime();
            notes.generateMelody(); //generate Melody in tonic
            System.out.println(System.nanoTime() - start);
            melody+="V"+j +" " + notes.finalMelody[0]+"i " + notes.finalMelody[1]+"i ";
        }

        //Write result in MIDI file
        Pattern pattern = new Pattern(melody);
        Player player = new Player();

        try{
            MidiFileManager.savePatternToMidi(pattern, new File(FILE_NAME));
        }
        catch (IOException ex){
            System.out.println("File creation Exception!");
        }

        //Play the melody (Here could be some lags because of the library, you should check the result in MIDI file)
        player.play(pattern);

    }
}
