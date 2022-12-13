package easv_MTunes.DAL;

import easv_MTunes.BE.AllPlaylists;
import easv_MTunes.BE.Song;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ISongsInPlaylistDataAccess  {
    public ArrayList<Song> getAllPlaylistSongs(int playlistNumber)throws SQLException;
    public void addSongToPlaylist(AllPlaylists playlist, Song song, int size) throws Exception;
    public void deleteSongFromPlaylist(AllPlaylists selectedPlaylist, Song selectedSong, int selectedRank) throws Exception;
}
