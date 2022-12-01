package easv_MTunes.BE;

import java.util.List;

public class Playlist {
private int id;
private List<Song> listPlaylist;
private String playlistSongTitle;

    public Playlist(int id, List<Song> songs, String playlistSongTitle){
        this.id =id;
        this.listPlaylist = songs;
        this.playlistSongTitle = playlistSongTitle;

    }

    public String getPlaylistSongTitle(){return playlistSongTitle;}

    public void setPlaylistSongsTitle(String playlistTitle){this.playlistSongTitle = playlistTitle;}

    public int getId(){return id;}

    public void setId(int id){this.id = id;}

    public List<Song> getListPlaylist(){return listPlaylist;}

    public void setListPlaylist(List<Song> listPlaylist){this.listPlaylist = listPlaylist;}

}
