package easv_MTunes.BLL;

import easv_MTunes.BE.AllPlaylists;
import easv_MTunes.BE.Song;
import easv_MTunes.DAL.db.SongsInPlaylistDAO_DB;

import java.util.List;

public class SongsInPlaylistManager {
    SongsInPlaylistDAO_DB songsInPlaylistDAO_DB;

    /**
     * Creates new songsInPlaylistDAO_DB to be able to use the methods from the SongsInPlaylistDAO_DB class
     */
    public SongsInPlaylistManager() {
        songsInPlaylistDAO_DB = new SongsInPlaylistDAO_DB();
    }

    /**
     *
     * Returns list of songs based on the songs that matches the playlist numbers from the playlistNumber metod from the SongsInPlaylistDAO_DB class
     * @throws Exception
     */
    public List<Song> getAllPlaylistSongs(int playlistNumber) throws Exception {
        return songsInPlaylistDAO_DB.getAllPlaylistSongs(playlistNumber);
    }

    /**
     * Gets the playlist, song and size from the addSongToPlaylist method from the SongsInPlaylistDAO_DB class
     * @throws Exception
     */
    public void addSongToPlaylist(AllPlaylists playlist, Song song, int size) throws Exception {
        songsInPlaylistDAO_DB.addSongToPlaylist(playlist,song, size);
    }

    /**
     *
     * Gets the selectedPlaylists, selectedSong and selectedRank from the deleteSongFromPlaylist method from the SongsInPlaylistDAO_DB class
     * @throws Exception
     */
    public void deleteSongFromPlaylist(AllPlaylists selectedPlaylists, Song selectedSong, int selectedRank) throws Exception {
        songsInPlaylistDAO_DB.deleteSongFromPlaylist(selectedPlaylists, selectedSong, selectedRank);
    }

    /**
     * Gets the songID and playlistID from the getRank method from the SongsInPlaylistDAO_DB class
     */
    public int getRank(int songID, int playlistID){
        return songsInPlaylistDAO_DB.getRank(songID, playlistID);
    }

}