/**package easv_MTunes.BLL;

import easv_MTunes.BE.Song;
import easv_MTunes.DAL.db.PlaylistDAO_DB;

import java.util.List;

public class PlaylistManager {
    PlaylistDAO_DB playlistDAO_DB;

    public PlaylistManager() {
        playlistDAO_DB = new PlaylistDAO_DB();
    }
    public List<Song> getAllPlaylistSongs(String playlistName) throws Exception {
        return playlistDAO_DB.getAllPlaylistSongs(playlistName);
    }
    public Song addSongToPlaylist(Song addedSong, String playlistName) throws Exception {
        return playlistDAO_DB.addSongToPlaylist(addedSong, playlistName);
    }
    public void deleteSongFromPlaylist(Song song, String playlistName) throws Exception {
        playlistDAO_DB.deleteSongFromPlaylist(song, playlistName);
    }
}
*/