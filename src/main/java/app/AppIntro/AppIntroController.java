package app.AppIntro;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.util.Objects;

public class AppIntroController {
    @FXML
    private MediaView IntroVideo;
    @FXML
    private AnchorPane root;

    public void initialize() {
        String videoFile = Objects.requireNonNull(getClass().getResource("video.mp4")).toExternalForm();
        System.out.println(videoFile);
        Media media = new Media(videoFile);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        IntroVideo.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
        IntroVideo.fitWidthProperty().bind(root.widthProperty());
        IntroVideo.fitHeightProperty().bind(root.heightProperty());
        IntroVideo.setPreserveRatio(true);

    }

}