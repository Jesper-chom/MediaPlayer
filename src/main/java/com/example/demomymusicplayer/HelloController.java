package com.example.demomymusicplayer;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


public class HelloController implements Initializable
{
    private MediaPlayer mediaPlayer;
    private List<Song> songs = new ArrayList<>();
    private int currentSongIndex = 0;
    @FXML private MediaView mediaV;
    @FXML private ListView<String> songsList;
    @FXML private Label songTitleLabel;
    @FXML private Label songArtistLabel;

    @FXML private ProgressBar progressBar;

    public void search(ActionEvent actionEvent) {
    }

    public void handleSearch(ActionEvent actionEvent) {
    }


    class Song
    {
        private String name;
        private String artist;
        private Media media;


        public Song(String name, String artist, Media media)
        {
            this.name = name;
            this.artist = artist;
            this.media = media;
        }
        public String getName() {
            return name;
        }
        public String getArtist() {
            return artist;
        }
        public Media getMedia() {
            return media;
        }
    }



    private void updateProgress()
    {
        progressBar.setProgress(mediaPlayer.getCurrentTime().toMillis() / mediaPlayer.getTotalDuration().toMillis());
    }

    public void initialize(URL location, ResourceBundle resources)
    {
        songs.add(new Song("Stranger Things", "AlexiAction", new Media(new File("src/main/java/com/example/demomymusicplayer/media/stranger-things-124008.mp3").toURI().toString())));
        songs.add(new Song("Sunset Rider", "Music Unlimited", new Media(new File("src/main/java/com/example/demomymusicplayer/media/sunset-rider-112776.mp3").toURI().toString())));
        songs.add(new Song("Synthwave 80s", "Grand Project", new Media(new File("src/main/java/com/example/demomymusicplayer/media/synthwave-80s-110045.mp3").toURI().toString())));
        songs.add(new Song("Rock N Christmas", "Musictown", new Media(new File("src/main/java/com/example/demomymusicplayer/media/rock-n-christmas-80s-127420.mp3").toURI().toString())));
        songs.add(new Song("Neon Lights ", "EvgnyBardyuha", new Media(new File("src/main/java/com/example/demomymusicplayer/media/neon-lights-128797.mp3").toURI().toString())));
        songs.add(new Song("Lady of The 80s", "AleXZavesa", new Media(new File("src/main/java/com/example/demomymusicplayer/media/lady-of-the-80x27s-128379.mp3").toURI().toString())));
        songs.add(new Song("Insurrection", "REDproductions", new Media(new File("src/main/java/com/example/demomymusicplayer/media/insurrection-10941.mp3").toURI().toString())));
        songs.add(new Song("Fun Disco", "Grand Project", new Media(new File("src/main/java/com/example/demomymusicplayer/media/fun-disco-1-108497.mp3").toURI().toString())));
        songs.add(new Song("Dreamy Inspiring Electronic", "FASSounds", new Media(new File("src/main/java/com/example/demomymusicplayer/media/dreamy-inspiring-electronic-15015.mp3").toURI().toString())));
        songs.add(new Song("Digital Love", "AlexiAction", new Media(new File("src/main/java/com/example/demomymusicplayer/media/digital-love-127441.mp3").toURI().toString())));
        songs.add(new Song("Cyberpunk 2099 ", "DopeStuff", new Media(new File("src/main/java/com/example/demomymusicplayer/media/cyberpunk-2099-10701.mp3").toURI().toString())));
        songs.add(new Song("Hero Of The 80s ", "Slicebeats", new Media(new File("src/main/java/com/example/demomymusicplayer/media/a-hero-of-the-80s-126684.mp3").toURI().toString())));
        songs.add(new Song("Pop Futuristic Electronic", "Lightyeartraxx", new Media(new File("src/main/java/com/example/demomymusicplayer/media/80s-synthwave-analog-synth-pop-futuristic-electronic-music-20598.mp3").toURI().toString())));
        songs.add(new Song("Synthwave Retro Synthpop ", "Eidunn", new Media(new File("src/main/java/com/example/demomymusicplayer/media/80s-retrowave-synthwave-retro-synthpop-futuristic-electro-pop-music-20596.mp3").toURI().toString())));
        songs.add(new Song("Synthwave Pop Retro Synth ", "Playsound", new Media(new File("src/main/java/com/example/demomymusicplayer/media/80s-futuristic-analog-synthwave-pop-retro-synth-music-20595.mp3").toURI().toString())));




        for (Song song : songs)
        {
            songsList.getItems().add(song.getName());
        }


        mediaPlayer = new MediaPlayer(songs.get(currentSongIndex).getMedia());
        mediaV.setMediaPlayer(mediaPlayer);
        mediaPlayer.setAutoPlay(true);


        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateProgress()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();



        progressBar.setOnMouseClicked(event -> {
            double mouseX = event.getX();
            double progressBarWidth = progressBar.getWidth();
            double progress = mouseX / progressBarWidth;
            mediaPlayer.seek(mediaPlayer.getTotalDuration().multiply(progress));



        });
    }




    @FXML
    private void songSelected(MouseEvent event)
    {
        String song = songsList.getSelectionModel().getSelectedItem();
        for (int i = 0; i < songs.size(); i++) {
            if (songs.get(i).getName().equals(song)) {
                currentSongIndex = i;
                break;
            }
        }
        mediaPlayer.stop();
        mediaPlayer = new MediaPlayer(songs.get(currentSongIndex).getMedia());
        mediaV.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
        mediaPlayer.currentTimeProperty().addListener((observable, oldTime, newTime) -> updateProgress());
        songTitleLabel.setText(songs.get(currentSongIndex).getName());
        songArtistLabel.setText(songs.get(currentSongIndex).getArtist());
    }



    @FXML private void handlePause() {
        mediaPlayer.pause();
    }
    @FXML private void handleStop() {
        mediaPlayer.stop();
    }
    @FXML private void handlePlay() {
        mediaPlayer.play();
    }
    
}
class Controller implements Initializable {

    ArrayList<String> words = new ArrayList<>(
            Arrays.asList("80s-futuristic-analog-synthwave-pop-retro-synth-music-20595.mp3", "80s-retrowave-synthwave-retro-synthpop-futuristic-electro-pop-music-20596.mp3","80s-synthwave-analog-synth-pop-futuristic-electronic-music-20598.mp3", "a-hero-of-the-80s-126684.mp3", "cyberpunk-2099-10701.mp3",
                    "digital-love-127441.mp3", "dreamy-inspiring-electronic-15015.mp3", "fun-disco-1-108497.mp3", "insurrection-10941.mp3", "lady-of-the-80x27s-128379.mp3", "neon-lights-128797.mp3",
                    "rock-n-christmas-80s-127420.mp3", "stranger-things-124008.mp3", "sunset-rider-112776.mp3", "synthwave-80s-110045.mp3")
    );

    @FXML
    private TextField searchBar;

    @FXML
    private ListView<String> listView;

    @FXML
    void search(ActionEvent event) {
        listView.getItems().clear();
        listView.getItems().addAll(searchList(searchBar.getText(),words));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listView.getItems().addAll(words);
    }

    private List<String> searchList(String searchWords, List<String> listOfStrings) {

        List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));

        return listOfStrings.stream().filter(input -> {
            return searchWordsArray.stream().allMatch(word ->
                    input.toLowerCase().contains(word.toLowerCase()));
        }).collect(Collectors.toList());
    }
}
