package easv_MTunes.DAL;

import easv_MTunes.BE.Playlist;
import easv_MTunes.BE.Song;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IPlaylistDataAccess {
    public ArrayList<Playlist> getPlaylistSongs() throws Exception;

    public Playlist createPlaylist(int id, String title, File songFile) throws Exception;

    public void updatePlaylist(Playlist playlist) throws Exception;

    public void deleteSong(Playlist playlist) throws Exception;
}
