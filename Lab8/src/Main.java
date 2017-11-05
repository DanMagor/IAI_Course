
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.jfugue.rhythm.Rhythm;
import org.jfugue.theory.Chord;
import org.jfugue.theory.ChordProgression;
import org.jfugue.theory.Note;

import static javafx.scene.input.KeyCode.I;

public class Main {

    public static void main(String[] args) {
        Player player = new Player();
        player.play("60maj 65maj 67maj");
    }
}
