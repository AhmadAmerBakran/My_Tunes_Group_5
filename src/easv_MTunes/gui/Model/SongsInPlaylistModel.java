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


        songsToBeViewed.addAll(songsInPlaylistManager.getAllPlaylistSongs(77));
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
    public void deleteSongFromPlaylist(AllPlaylists playlists, Song song) throws Exception {
        songsInPlaylistManager.deleteSongFromPlaylist(playlists, song);
        songsToBeViewed.remove(song);
    }

    public void showList(int playlistNumber) throws Exception {
        //Update the listview
        songsToBeViewed.clear();

        songsToBeViewed.addAll(songsInPlaylistManager.getAllPlaylistSongs(playlistNumber));
    }


}