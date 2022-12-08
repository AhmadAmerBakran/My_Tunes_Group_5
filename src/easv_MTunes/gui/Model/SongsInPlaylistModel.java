package easv_MTunes.gui.Model;

import easv_MTunes.BE.AllPlaylists;
import easv_MTunes.BE.Song;
import easv_MTunes.BLL.SongsInPlaylistManager;
import easv_MTunes.BLL.SongManager;
import easv_MTunes.gui.Controller.SongViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

public class SongsInPlaylistModel {
    private ObservableList<Song> songsToBeViewed;
    private SongsInPlaylistManager songsInPlaylistManager;
    private AllPlaylists selectedPlaylist;
    public SongsInPlaylistModel() throws Exception {
        songsInPlaylistManager = new SongsInPlaylistManager();
        songsToBeViewed = FXCollections.observableArrayList();
        if(getSelectedPlaylist()!=null)
        {songsToBeViewed.addAll(songsInPlaylistManager.getAllPlaylistSongs(getSelectedPlaylist().getPlaylistName()));}
    }
    public AllPlaylists getSelectedPlaylist()
    {
        return selectedPlaylist;
    }


    public ObservableList<Song> getObservableSongs() {
        return songsToBeViewed;
    }


    public void addSongToPlaylist(AllPlaylists playlist, Song song) throws Exception {
        songsInPlaylistManager.addSongToPlaylist(playlist, song);
        songsToBeViewed.add(song);


    }
    public void deleteSongFromPlaylist(Song song, String playListName) throws Exception {
        songsInPlaylistManager.deleteSongFromPlaylist(song, playListName);
        songsToBeViewed.remove(song);
    }


}