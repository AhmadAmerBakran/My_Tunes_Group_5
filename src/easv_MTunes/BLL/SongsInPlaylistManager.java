package easv_MTunes.BLL;

import easv_MTunes.BE.AllPlaylists;
import easv_MTunes.BE.Song;
import easv_MTunes.DAL.db.SongsInPlaylistDAO_DB;

import java.util.List;

public class SongsInPlaylistManager {
    SongsInPlaylistDAO_DB songsInPlaylistDAO_DB;

    public SongsInPlaylistManager() {
        songsInPlaylistDAO_DB = new SongsInPlaylistDAO_DB();
    }
    public List<Song> getAllPlaylistSongs(int playlistNumber) throws Exception {
        return songsInPlaylistDAO_DB.getAllPlaylistSongs(playlistNumber);
    }
    public void addSongToPlaylist(AllPlaylists playlist, Song song, int size) throws Exception {
        songsInPlaylistDAO_DB.addSongToPlaylist(playlist,song, size);
    }
    public void deleteSongFromPlaylist(AllPlaylists selectedPlaylists, Song selectedSong, int selectedRank) throws Exception {
        songsInPlaylistDAO_DB.deleteSongFromPlaylist(selectedPlaylists, selectedSong, selectedRank);
    }
    public int getRank(int songID, int playlistID){
        return songsInPlaylistDAO_DB.getRank(songID, playlistID);
    }

}