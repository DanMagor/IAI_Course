import org.jfugue.player.Player;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

//        MusicGenerator.makeMelody();

        String melody = "";
        String scale = "min";
        Random random = new Random();
        int tonic = random.nextInt(72 - 48 + 1) + 48;
        if(random.nextDouble()<0.5){
            scale = "maj";
        }
        for (int i = 0; i < 16; i++) {
            ChordsPSOGenerator chord = new ChordsPSOGenerator(tonic, scale);
            MelodyPSOGenerator notes = new MelodyPSOGenerator(tonic,scale);
            int j = 0;
            for (int note:chord.generateChord()) {

                melody += "V" + j +" " + note + "q ";
                j++;
            }
            for (int note:notes.generateMelody()){
                melody+="V" + j + " " + note + "i ";
                j++;
            }
        }
        Player player = new Player();
        System.out.println(tonic + scale);
        System.out.println(melody);
        player.play("" + melody);

    }
}
