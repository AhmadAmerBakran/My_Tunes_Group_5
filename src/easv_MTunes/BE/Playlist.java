package easv_MTunes.BE;

import java.util.List;

public class Playlist {
private List<Song> playlistSongs;

    public Playlist(List<Song> playlistSongs){
        this.playlistSongs = playlistSongs;

    }

    public List<Song> getPlaylistSongs() {
        return playlistSongs;
    }

    public void setPlaylistSongs(List<Song> playlistSongs) {
        this.playlistSongs = playlistSongs;
    }
}
