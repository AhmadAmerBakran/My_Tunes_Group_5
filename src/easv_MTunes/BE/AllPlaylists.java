package easv_MTunes.BE;

import java.util.List;

public class AllPlaylists {
    private String playlistName;
    private List<Playlist> playlists;
    private int time;

    public AllPlaylists(String playlistName, List<Song> songs, int time){
        this.playlistName = playlistName;
        this.playlists = playlists;
        this.time = time;

    }
    public String getPlaylistName(){return playlistName;}

    public void setPlaylistName(String playlistName){this.playlistName = playlistName;}

    public List<Song> getSongs(){return songs;}

    public void setSongs(List<Song> songs){this.songs = songs;}

    public int getTime() {return time;}

    public void setTime(int time) {this.time = time;}
}
