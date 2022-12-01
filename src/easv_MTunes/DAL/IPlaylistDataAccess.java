package easv_MTunes.DAL;

import easv_MTunes.BE.Playlist;
import easv_MTunes.BE.Song;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface IPlaylistDataAccess {
    public List<Song> getPlaylistSongs() throws Exception;

    public Playlist addSongToPlaylist(Song addedSong) throws Exception;


    public void deleteSongFromPlaylist(Song deletedSong) throws Exception;
}
