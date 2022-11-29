package easv_MTunes.gui.Model;

import easv_MTunes.BE.Song;
import easv_MTunes.BLL.SongManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.sql.SQLException;

public class SongModel {
    private ObservableList<Song> songsToBeViewed;
    private SongManager songManager;

    public SongModel() throws SQLException {
        songManager = new SongManager();
        songsToBeViewed = FXCollections.observableArrayList();
        songsToBeViewed.addAll(songManager.getAllSongs());
    }

    public ObservableList<Song> getObservableSongs()
    {
        return songsToBeViewed;
    }
    public void createNewSong(String title, String artist, File songFile) throws Exception {
        Song song = songManager.createNewSong(title, artist, songFile);
        songsToBeViewed.add(song);
    }
}
