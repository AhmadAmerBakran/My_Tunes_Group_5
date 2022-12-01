/*
package easv_MTunes.gui.Model;

import easv_MTunes.BE.Playlist;
import easv_MTunes.BE.Song;
import easv_MTunes.BLL.PlaylistManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.sql.SQLException;

public class PlaylistModel {
    private ObservableList<Song> currentPlaylist;
    private PlaylistManager playlistManager;
    private Playlist selectedPlaylist;

    public PlaylistModel() throws Exception {
        playlistManager = new PlaylistManager();
        currentPlaylist = FXCollections.observableArrayList();
        currentPlaylist.addAll(playlistManager.getCurrentPlaylist());

    }
    public ObservableList<Playlist> getObservablePlaylist()
    {
        return currentPlaylist;
    }
    public void createNewPlaylist(int id, String title, File songFile) throws Exception {
        Playlist playlist = playlistManager.createNewPlaylist(id, title, songFile);
        currentPlaylist.add(playlist);

    }

    public void deletePlaylistSongs(Playlist playlist) throws Exception {
        playlistManager.deletePlaylist(playlist);
        currentPlaylist.remove(playlist);
    }

    public Playlist getSelectedPlaylist() {
        return selectedPlaylist;
    }

    public void setSelectedPlaylist(Playlist selectedPlaylist) {
        this.selectedPlaylist = selectedPlaylist;
    }

    public void updateSong(Song updatedSong) throws Exception {
        songManager.updateMovie(updatedSong);

        // update ListView
        songsToBeViewed.clear();
        songsToBeViewed.addAll(songManager.getAllSongs());
    }
}
*/
