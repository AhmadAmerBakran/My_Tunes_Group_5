package easv_MTunes.DAL;

import easv_MTunes.BE.Playlist;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IPlaylistDataAccess {
    ArrayList<Playlist> getPlaylistSongs() throws Exception;
}
