package lk.ijse.alert;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import static lk.ijse.alert.Sounds.INVALID;

public class AlertSound {
    String soundFile;
    Media media;
    MediaPlayer mediaPlayer;
    public void checkSounds(Sounds sound){
        switch (sound) {
            case INVALID:
                soundFile = getClass().getResource("sound/alertSounds/wrongError.mp3").toExternalForm();
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
