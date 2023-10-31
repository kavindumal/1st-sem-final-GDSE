package lk.ijse.alert;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.Objects;

import static lk.ijse.alert.Sounds.INVALID;

public class AlertSound {
    String soundFile;
    Media media;
    MediaPlayer mediaPlayer;
    public void checkSounds(Sounds sound){
        switch (sound) {
            case INVALID:
                soundFile = Objects.requireNonNull(getClass().getResource("sound/alertSounds/attack2t22wav-14511.mp3")).toExternalForm();
                media = new Media(soundFile);
                mediaPlayer = new MediaPlayer(media);
                break;
            case CONFIRM:
//                soundFile = getClass().getResource("sound/alertSounds/wrongError.mp3").toExternalForm();
//                media = new Media(soundFile);
//                mediaPlayer = new MediaPlayer(media);
                break;
            default:
                System.out.println("wrong");
        }
    }
}
