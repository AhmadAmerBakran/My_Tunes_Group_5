package easv_MTunes.BLL;

import easv_MTunes.BE.Song;
import easv_MTunes.BLL.util.SongSearcher;
import easv_MTunes.DAL.ISongDataAccess;
import easv_MTunes.DAL.db.SongDAO_DB;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SongManager {
    private ISongDataAccess songDAO;
    private SongSearcher songSearcher = new SongSearcher();
    /**
     * Creates songDAO_DB to be able to use methods from SongDAO_DB class
     */
    public SongManager() {
        songDAO = new SongDAO_DB();
    }

    /**
     * Returns the getAllSongs method from the SongDAO_DB class
     * @throws Exception
     */
    public ArrayList<Song> getAllSongs() throws SQLException {
        return songDAO.getAllSongs();
    }

    /**
     * Creates list of all songs
     * Creates list og songs that match the user input
     * Returns the list of songs that matches the users input
     * @throws Exception
     */
    public List<Song> searchSongs(String query) throws Exception {
        List<Song> allSongs = getAllSongs();
        List<Song> searchResult = songSearcher.search(allSongs, query);
        return searchResult;
    }
    /**
     * Returns title, artist and song file from the createNewSong method in the SongDAO_DB class
     * @throws Exception
     */
    public Song createNewSong(String title, String artist, File songFile) throws Exception {
        return songDAO.createSong(title, artist, songFile);
    }

    /**
     * Returns the value from the deleteSong method from the SongDAO_DB class
     * @throws Exception
     */
    public void deleteSong(Song song) throws Exception {
        songDAO.deleteSong(song);
    }

    /**
     *
     * Returns the value from updatedSong method from the SongDAO_DB class
     * @throws Exception
     */
    public void updateSong(Song updatedSong) throws Exception {
        songDAO.updateSong(updatedSong);
    }
}
