package easv_MTunes.DAL;

import easv_MTunes.BE.AllPlaylists;
import easv_MTunes.BE.Song;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ISongsInPlaylistDataAccess  {
    public ArrayList<Song> getAllPlaylistSongs()throws SQLException;
    public Song addSongToPlaylist(AllPlaylists playlist, Song song) throws Exception;
    public void deleteSongFromPlaylist(Song deletedSong, String playlistName) throws Exception;

}