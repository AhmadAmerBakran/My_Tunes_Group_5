package easv_MTunes.gui.Controller;

import easv_MTunes.BE.AllPlaylists;
import easv_MTunes.BE.Song;
import easv_MTunes.gui.Model.AllPlaylistsModel;
import easv_MTunes.gui.Model.SongModel;
import easv_MTunes.gui.Model.SongsInPlaylistModel;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;


public class SongViewController extends ControllerManager implements Initializable {

    public TableColumn <Song, String>cSongTitle;
    public TableColumn <Song, Integer>cSongId;
    @FXML
    public TableView <Song> sipListTable;
    public TableColumn<AllPlaylists, Integer> cPListsId;
    @FXML
    private TableColumn<AllPlaylists, Integer> cPListsSongs;
    @FXML
    private TableColumn<AllPlaylists, String> cPListsName;
    @FXML
    private TableView<AllPlaylists> pListsTable;
    @FXML
    private TableColumn<Song, Integer> cTime;
    @FXML
    private TableColumn<Song, String> cArtist;
    @FXML
    private TableColumn<Song, String> cTitle;
    @FXML
    private TableView<Song> songTable;
    @FXML
    private Label playedSong;
    @FXML
    private ImageView imgPlay;
    @FXML
    private ImageView soundOn;
    @FXML
    private Button btnVolume;
    @FXML
    Button btnPlay;
    @FXML
    private Slider slideVolume, slideTime;
    @FXML
    private Label lblStart, lblEnd;
    @FXML
    private TextField searchField;

    private Media media;
    private MediaPlayer mediaPlayer;
    private int songNumber;
    private Timer timer;
    private TimerTask task;
    private boolean playClicked = true;
    private boolean muteClicked = true;
    private int playlistNumber;


    private SongModel songModel;
    private SongsInPlaylistModel songsInPlaylistModel;
    private AllPlaylistsModel allPlaylistsModel;
    public Song selectedSong;
    public AllPlaylists selectedPlaylist;
    private boolean songInPlaylistSelected = false;
    private boolean songInSonglistSelected = false;

    public SongViewController() {
        try {
            songModel = new SongModel();
            songsInPlaylistModel = new SongsInPlaylistModel();
            allPlaylistsModel = new AllPlaylistsModel();
        }catch (Exception e){

            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            allPlaylistsModel = new AllPlaylistsModel();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        media = new Media(songModel.getObservableSongs().get(songNumber).getSongFile().toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        showAllSongsAndPlaylists();
        volumeSlider();
        timeSlider();

        pListsTable.setOnMouseClicked(event -> {
            AllPlaylists selectedPlaylist = pListsTable.getSelectionModel().getSelectedItem();
            playlistNumber = selectedPlaylist.getPlaylistId();

            try{
                songsInPlaylistModel.showList(playlistNumber);
            }
            catch(Exception e){
                throw new RuntimeException(e);
            }
        });

        sipListTable.setOnMouseClicked(event -> {

            mediaPlayer.stop();
            if(playClicked == false){
                Image pausing = new Image("/easv_MTunes/images/play_96px.png");
                imgPlay.setImage(pausing);
                playClicked = true;}

            songInSonglistSelected = false;
            songInPlaylistSelected = true;


            Song selectedSongInPlaylist = sipListTable.getSelectionModel().getSelectedItem();
            songNumber = songsInPlaylistModel.getObservableSongs().indexOf(selectedSongInPlaylist);

            media = new Media(songsInPlaylistModel.getObservableSongs().get(songNumber).getSongFile().toURI().toString());
            mediaPlayer = new MediaPlayer(media);
        });

        songTable.setOnMouseClicked(event -> {

            mediaPlayer.stop();
            if(playClicked == false){
                Image pausing = new Image("/easv_MTunes/images/play_96px.png");
                imgPlay.setImage(pausing);
                playClicked = true;
            }

            songInSonglistSelected = true;
            songInPlaylistSelected = false;

            Song selectedSongInSonglist = songTable.getSelectionModel().getSelectedItem();
            songNumber = songModel.getObservableSongs().indexOf(selectedSongInSonglist);

            media = new Media (songModel.getObservableSongs().get(songNumber).getSongFile().toURI().toString());
            mediaPlayer = new MediaPlayer(media);
        });

    }
    @Override
    public void setup() {
        songModel = getModel().getSongModel();
        allPlaylistsModel = getModel().getAllPlaylistsModel();
        showAllSongsAndPlaylists();
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                songModel.searchSong(newValue);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

    }
    public void play(ActionEvent actionEvent) {
        if(playClicked)
        {
            Image playing = new Image("/easv_MTunes/images/pause_button_96px.png");
            imgPlay.setImage(playing);
            playFunctions();
            mediaPlayer.play();
            playClicked = false;
        } else if (!playClicked) {
            Image pausing = new Image("/easv_MTunes/images/play_96px.png");
            imgPlay.setImage(pausing);
            mediaPlayer.pause();
            playClicked = true;

        }

    }
    public void showAllSongsAndPlaylists()
    {

        songTable.setItems(songModel.getObservableSongs());
        cTitle.setCellValueFactory(new PropertyValueFactory<Song, String>("title"));
        cArtist.setCellValueFactory(new PropertyValueFactory<Song, String>("artist"));


        pListsTable.setItems(allPlaylistsModel.getObservableAllPlaylists());
        cPListsName.setCellValueFactory(new PropertyValueFactory<AllPlaylists, String>("playlistName"));
        cPListsSongs.setCellValueFactory(new PropertyValueFactory<AllPlaylists, Integer>("playlistSongsNumber"));
        cPListsId.setCellValueFactory(new PropertyValueFactory<AllPlaylists, Integer>("playlistId"));

        sipListTable.setItems(songsInPlaylistModel.getObservableSongs());
        cSongTitle.setCellValueFactory(new PropertyValueFactory<Song, String>("title"));


    }




    public void playNext(ActionEvent actionEvent) {

        if(songInSonglistSelected == true) {
            if (songNumber < songModel.getObservableSongs().size() - 1) {
                mediaPlayer.stop();
                songNumber++;
                playNextOrPrev();
                playFunctions();

            } else {
                mediaPlayer.stop();
                songNumber = 0;
                playNextOrPrev();
                playFunctions();
            }
        }
        else if(songInPlaylistSelected == true){
            if (songNumber < songsInPlaylistModel.getObservableSongs().size()-1){
                mediaPlayer.stop();
                songNumber++;
                playNextOrPrev();
                playFunctions();
            } else {
                mediaPlayer.stop();
                songNumber = 0;
                playNextOrPrev();
                playFunctions();
            }
        }

    }


    public void playPrevious(ActionEvent actionEvent) {
        if(songNumber > 0)
        {
            mediaPlayer.stop();
            songNumber--;
            playNextOrPrev();
            playFunctions();
        } else
        {
            mediaPlayer.stop();
            if (songInSonglistSelected) {
                songNumber = songModel.getObservableSongs().size()-1;
            }
            if (songInPlaylistSelected) {
                songNumber = songsInPlaylistModel.getObservableSongs().size()-1;
            }
            playNextOrPrev();
            playFunctions();
        }
    }


    public void stopPlaying(ActionEvent actionEvent) {
        mediaPlayer.seek(Duration.seconds(0));
        mediaPlayer.stop();
        playClicked = false;
        if(playClicked == false){
        Image pausing = new Image("/easv_MTunes/images/play_96px.png");
        imgPlay.setImage(pausing);
        playClicked = true;
        }
    }


    public void getCurrentTimeSlider()
    {
        timer = new Timer();

        task = new TimerTask() {
            @Override
            public void run() {
                slideTime.setMax(mediaPlayer.getTotalDuration().toSeconds());
                double current = mediaPlayer.getCurrentTime().toSeconds();
                slideTime.setValue(current);
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }


    private void bindCurrentTimeLabel()
    {
        lblStart.textProperty().bind(Bindings.createStringBinding(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return getTime(mediaPlayer.getCurrentTime()) + " / ";
            }
        }, mediaPlayer.currentTimeProperty()));


    }
    public void playNextOrPrev()
    {
        if (songInSonglistSelected == true){
            media = new Media(songModel.getObservableSongs().get(songNumber).getSongFile().toURI().toString());
        }
        else if(songInPlaylistSelected == true){
            media = new Media(songsInPlaylistModel.getObservableSongs().get(songNumber).getSongFile().toURI().toString());
        }
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        Image playing = new Image("/easv_MTunes/images/pause_button_96px.png");
        imgPlay.setImage(playing);
        playClicked = false;
    }

    private void bindTotalTimeLabel()
    {
        lblEnd.textProperty().bind(Bindings.createStringBinding(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return getTime(mediaPlayer.getTotalDuration()) + " / ";
            }
        }, mediaPlayer.currentTimeProperty()));


    }
    private String getTime(Duration time)
    {

        int hours = (int) time.toHours();
        int minutes = (int) time.toMinutes();
        int seconds = (int) time.toSeconds();
        if(seconds > 59)
            seconds = seconds % 60;
        if(minutes > 59)
            minutes = minutes % 60;
        if(hours > 59)
            hours = hours % 60;
        if(hours > 0)
            return String.format("%d:%02d:%02d", hours, minutes, seconds);
        else return String.format("%02d:%02d", minutes, seconds);
    }

    private void playFunctions()
    {
        bindCurrentTimeLabel();
        bindTotalTimeLabel();
        if(!muteClicked)
        {
            mediaPlayer.setVolume(0);
        }else
        {
            mediaPlayer.setVolume(slideVolume.getValue() * 0.01);
        }
        getCurrentTimeSlider();
        //playedSong.setText(songModel.getObservableSongs().get(songNumber).toString().substring(7));
        Song selectedSongInPlaylist = sipListTable.getSelectionModel().getSelectedItem();
        if (selectedSongInPlaylist != null){
            //String path = selectedSongInPlaylist.getSongPath(selectedSongInPlaylist.getSongFile());
            playedSong.setText(songsInPlaylistModel.getObservableSongs().get(songNumber).toString().substring(7));
        }


    }
    private void volumeSlider()
    {
        slideVolume.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                mediaPlayer.setVolume(slideVolume.getValue() * 0.01);
                Image unMuted = new Image("/easv_MTunes/images/voice_96px.png");
                soundOn.setImage(unMuted);
                muteClicked = true;
            }
        });

    }
    private void timeSlider()
    {

        slideTime.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue)
                {
                    slideTime.setMax(mediaPlayer.getTotalDuration().toSeconds());

                    mediaPlayer.seek(Duration.seconds(slideTime.getValue()));
                }
            }
        });
        slideTime.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double currentTime = mediaPlayer.getCurrentTime().toSeconds();
                if(Math.abs(currentTime - newValue.doubleValue()) > 0.5)
                {
                    mediaPlayer.seek(Duration.seconds(newValue.doubleValue()));
                }
            }
        });
    }

    public void muteVolume(ActionEvent actionEvent) {
        if(muteClicked)
        {
            mediaPlayer.setVolume(0);
            Image mute = new Image("/easv_MTunes/images/mute_96px.png");
            soundOn.setImage(mute);

            muteClicked = false;

        } else if (!muteClicked) {
            mediaPlayer.setVolume(slideVolume.getValue() * 0.01);
            Image unMuted = new Image("/easv_MTunes/images/voice_96px.png");
            soundOn.setImage(unMuted);
            muteClicked = true;

        }
    }

    public void editSong(ActionEvent actionEvent) throws IOException {
        Song selectedSong = songTable.getSelectionModel().getSelectedItem();
        if(selectedSong==null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Needed Info");
            alert.setHeaderText("Please choose the song you would like to edit...");
            alert.show();
        }else{
        songModel.setSelectedSong(selectedSong);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/easv_MTunes/gui/View/SongCrud.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();

        SongCrud songCrud = loader.getController();
        songCrud.setModel(super.getModel());
        songCrud.setup();


        Stage dialogWindow = new Stage();
        dialogWindow.setTitle("Edit Song");
        dialogWindow.initModality(Modality.WINDOW_MODAL);
        dialogWindow.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
        Scene scene = new Scene(pane);
        dialogWindow.setScene(scene);

        dialogWindow.showAndWait();}
    }

    public void addSong(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/easv_MTunes/gui/View/SongCrud.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();

        SongCrud songCrud = loader.getController();
        songCrud.setModel(super.getModel());
        showAllSongsAndPlaylists();
        songCrud.setup();


        Stage dialogWindow = new Stage();
        dialogWindow.setTitle("Add Song");
        dialogWindow.initModality(Modality.WINDOW_MODAL);
        dialogWindow.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
        Scene scene = new Scene(pane);
        dialogWindow.setScene(scene);

        dialogWindow.showAndWait();

    }
    public Song getSelectedSong()
    {
        Song song;
        song = songTable.getSelectionModel().getSelectedItem();
        return song;
    }
    public AllPlaylists getSelectedplaylist()
    {
        AllPlaylists selectedPlaylist;
        selectedPlaylist = pListsTable.getSelectionModel().getSelectedItem();
        return selectedPlaylist;
    }

    public Song getSelectedSongFromPlaylist(){
        Song selectedSongFromPlaylist;
        selectedSongFromPlaylist = sipListTable.getSelectionModel().getSelectedItem();
        return selectedSongFromPlaylist;
    }

    public void deleteSong(ActionEvent actionEvent) throws Exception {
        Song selectedSong = songTable.getSelectionModel().getSelectedItem();
        if(selectedSong==null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Needed Info");
            alert.setHeaderText("Please choose the song you would like to delete...");
            alert.show();
        }else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Warning");
            alert.setHeaderText("Are you sure you want to delete: " + selectedSong.getTitle().concat( " ?"));
            Optional<ButtonType> action = alert.showAndWait();
            if(action.get() == ButtonType.OK)
            {
                songModel.deleteSong(getSelectedSong());
            }
        }
    }


    public void addPlaylist(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/easv_MTunes/gui/View/PlaylistsView.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();

        PlaylistsView playlistsView = loader.getController();
        playlistsView.setModel(super.getModel());
        showAllSongsAndPlaylists();
        playlistsView.setup();


        Stage dialogWindow = new Stage();
        dialogWindow.setTitle("New Playlist");
        dialogWindow.initModality(Modality.WINDOW_MODAL);
        dialogWindow.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
        Scene scene = new Scene(pane);
        dialogWindow.setScene(scene);

        dialogWindow.showAndWait();
    }

    public void editPlaylist(ActionEvent actionEvent) throws IOException {
        AllPlaylists selectedPlaylist = pListsTable.getSelectionModel().getSelectedItem();
        if(selectedPlaylist==null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Needed Info");
            alert.setHeaderText("Please choose the playlist you would like to edit...");
            alert.show();
        }else{
            allPlaylistsModel.setSelectedPlaylist(selectedPlaylist);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/easv_MTunes/gui/View/PlaylistsView.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();

            PlaylistsView playlistsView = loader.getController();
            playlistsView.setModel(super.getModel());
            playlistsView.setup();


            Stage dialogWindow = new Stage();
            dialogWindow.setTitle("Edit Playlist");
            dialogWindow.initModality(Modality.WINDOW_MODAL);
            dialogWindow.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            Scene scene = new Scene(pane);
            dialogWindow.setScene(scene);

            dialogWindow.showAndWait();}

    }

    public void deletePlaylist(ActionEvent actionEvent) {
        AllPlaylists selectedPlaylist = pListsTable.getSelectionModel().getSelectedItem();
        if(selectedPlaylist==null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Needed Info");
            alert.setHeaderText("Please choose the playlist you would like to delete...");
            alert.show();
        }else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Warning");
            alert.setHeaderText("Are you sure you want to delete: " + selectedPlaylist.getPlaylistName().concat( " ?"));
            Optional<ButtonType> action = alert.showAndWait();
            if(action.get() == ButtonType.OK)
            {
                try {
                    allPlaylistsModel.deletePlaylist(getSelectedplaylist());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    //Delete song from playlist
    public void deleteFromPlaylist(ActionEvent actionEvent) throws Exception {
        //selectedSong = sipListTable.getSelectionModel().getSelectedItem();
        selectedSong = sipListTable.getSelectionModel().getSelectedItem();
        selectedPlaylist = pListsTable.getSelectionModel().getSelectedItem();
        //Get needed information and assign them
        int selectedSongID = selectedSong.getId();
        int selectedPlaylistID = selectedPlaylist.getPlaylistId();
        int deletedSongRank = songsInPlaylistModel.getRank(selectedSongID, selectedPlaylistID);
        songsInPlaylistModel.deleteSongFromPlaylist(selectedPlaylist, selectedSong, deletedSongRank);
            refreshSongInPlaylist();


        }


    public void refreshSongInPlaylist() throws Exception {
        songsInPlaylistModel = new SongsInPlaylistModel();
        sipListTable.setItems(songsInPlaylistModel.getObservableSongs());
        songsInPlaylistModel.showList(playlistNumber);
    }

    //Adds the selected song to the selected playlist
    public void addSongToPlaylist(ActionEvent actionEvent) throws Exception {

            selectedPlaylist = pListsTable.getSelectionModel().getSelectedItem();
            int sizeOfPlaylist = songsInPlaylistModel.getObservableSongs().size();
            Song selectedSong = songTable.getSelectionModel().getSelectedItem();
         AllPlaylists selectedPlaylist = pListsTable.getSelectionModel().getSelectedItem();
        if (sizeOfPlaylist != 0) {
            Song newSong = sipListTable.getItems().get(sizeOfPlaylist-1);
            int lastSongID = newSong.getId();
            int selectedPlaylistID = selectedPlaylist.getPlaylistId();
            int highestRank = songsInPlaylistModel.getRank(lastSongID, selectedPlaylistID);

            songsInPlaylistModel.addSongToPlaylist(selectedPlaylist, selectedSong, highestRank + 1);
        }
        else {
            songsInPlaylistModel.addSongToPlaylist(selectedPlaylist, selectedSong, 1);
        }

        /*try {
            if(selectedPlaylist !=null)
                songsInPlaylistModel.addSongToPlaylist(selectedPlaylist, song);
        } catch (Exception e) {

    }*/
    }
}
