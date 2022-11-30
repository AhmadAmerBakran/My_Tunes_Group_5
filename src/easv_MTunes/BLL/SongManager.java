package easv_MTunes.BLL;

import easv_MTunes.BE.Song;
import easv_MTunes.DAL.ISongDataAccess;
import easv_MTunes.DAL.SongDAO_Files;
import easv_MTunes.DAL.db.SongDAO_DB;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

public class SongManager {
    private ISongDataAccess songDAO;

    public SongManager() {
        songDAO = new SongDAO_DB();
    }

    public ArrayList<Song> getAllSongs() throws SQLException {
        return songDAO.getAllSongs();
    }
    public Song createNewSong(String title, String artist, File songFile) throws Exception {
        return songDAO.createSong(title, artist, songFile);
    }
    public void deleteSong(Song song) throws Exception {
        songDAO.deleteSong(song);
    }

    public void updateMovie(Song updatedSong) throws Exception {
        songDAO.updateSong(updatedSong);
    }
}
