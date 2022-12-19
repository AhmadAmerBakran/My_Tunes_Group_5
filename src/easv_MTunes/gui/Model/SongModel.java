package easv_MTunes.gui.Model;

import easv_MTunes.BE.Song;
import easv_MTunes.BLL.SongManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

public class SongModel {
    private ObservableList<Song> songsToBeViewed;
    private SongManager songManager;
    private Song selectedSong;

    public SongModel() throws SQLException {
        songManager = new SongManager();
        songsToBeViewed = FXCollections.observableArrayList();
        songsToBeViewed.addAll(songManager.getAllSongs());
    }

    /**
     * Creates Observable List of songs
     * Returns songsToBeViewed
     */
    public ObservableList<Song> getObservableSongs()
    {
        return songsToBeViewed;
    }

    /**
     * Searches for the user input in searchSongs method from the songManager class
     * @throws Exception
     */
    public void searchSong(String query) throws Exception {
        List<Song> searchResults = songManager.searchSongs(query);
        songsToBeViewed.clear();
        songsToBeViewed.addAll(searchResults);
    }

    /**
     * Creates song from title, artist and song file from the createSong method in the songManager class
     * Adds created song to songsToBeViewed list
     */
    public void createNewSong(String title, String artist, File songFile) throws Exception {
        Song song = songManager.createNewSong(title, artist, songFile);
        songsToBeViewed.add(song);

    }

    /**
     * Gets song from the deleteSong method in the songManager class
     * Deletes song from songsToBeViewed
     * @throws Exception
     */
    public void deleteSong(Song song) throws Exception {
        songManager.deleteSong(song);
        songsToBeViewed.remove(song);
    }

    /**
     * Getter for selectedSong
     */
    public Song getSelectedSong() {
        return selectedSong;
    }

    /**
     * Setter for selectedSong
     */
    public void setSelectedSong(Song selectedSong) {
        this.selectedSong = selectedSong;
    }

    /**
     * Gets value from updatedSong method in the songManager class
     * Initiates show list method
     * @throws Exception
     */
    public void updateSong(Song updatedSong) throws Exception {
        songManager.updateSong(updatedSong);

        showList();
    }

    /**
     * Updates songsToBeViewed List by first clearing it and then creating it again with updated values
     * @throws Exception
     */
    public void showList() throws Exception {
        //Update the listview
        songsToBeViewed.clear();
        songsToBeViewed.addAll(songManager.getAllSongs());
    }
}
