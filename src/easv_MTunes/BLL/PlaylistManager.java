/*
package easv_MTunes.BLL;

import easv_MTunes.BE.Playlist;
import easv_MTunes.DAL.IPlaylistDataAccess;
import easv_MTunes.DAL.db.PlaylistDAO_DB;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaylistManager {
    private IPlaylistDataAccess playlistDAO;

    public PlaylistManager() throws SQLException {
        playlistDAO = new PlaylistDAO_DB();
    }

    public ArrayList<Playlist> getCurrentPlaylist() throws Exception {
        return playlistDAO.getPlaylistSongs();
    }
    public Playlist createNewPlaylist(int id, String title, File songFile) throws Exception {
        return playlistDAO.createPlaylist(id, title, songFile);
    }
    public void deletePlaylist(Playlist playlist) throws Exception {
        playlistDAO.deleteSong(playlist);
    }

    public void updatePlaylist(Playlist updatedPlaylist) throws Exception {
        playlistDAO.updatePlaylist(updatedPlaylist);
    }
}
*/
