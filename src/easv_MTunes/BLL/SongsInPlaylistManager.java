package easv_MTunes.BLL;

import easv_MTunes.BE.AllPlaylists;
import easv_MTunes.BE.Song;
import easv_MTunes.DAL.db.SongsInPlaylistDAO_DB;


import java.util.ArrayList;
import java.util.List;

public class SongsInPlaylistManager {
    SongsInPlaylistDAO_DB songsInPlaylistDAO_DB;

    public SongsInPlaylistManager() {
        songsInPlaylistDAO_DB = new SongsInPlaylistDAO_DB();
    }
    public List<Song> getAllPlaylistSongs(String playlistName) throws Exception {
        return songsInPlaylistDAO_DB.getAllPlaylistSongs();
    }
    public ArrayList<Song> addSongToPlaylist() throws Exception {
        return songsInPlaylistDAO_DB.addSongToPlaylist();
    }
    public void deleteSongFromPlaylist(Song song, String playlistName) throws Exception {
        songsInPlaylistDAO_DB.deleteSongFromPlaylist(song, playlistName);
    }
}