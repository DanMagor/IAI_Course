import org.jfugue.player.Player;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

//        MusicGenerator.makeMelody();

        String melody = "";
        Random random = new Random();
        int tonic = random.nextInt(60 - 48 + 1) + 48;
        for (int i = 0; i < 16; i++) {
            ChordPSO temp = new ChordPSO(tonic, "maj");
            melody += "V1 " + temp.finalChord[0] + "q ";
            melody += "V2 " + temp.finalChord[1] + "q ";
            melody += "V3 " + temp.finalChord[2] + "q ";

        }
        Player player = new Player();
        System.out.println(tonic);
        System.out.println(melody);
        player.play("" + melody + melody + melody +melody);

    }
}
