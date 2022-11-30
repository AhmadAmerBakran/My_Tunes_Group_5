package easv_MTunes.BE;

import java.util.List;

public class Playlist {
private int id;
private List<Song> listPlaylist;
private String songTitle;

    public Playlist(int id, List<Song> songs, String songTitle){
        this.id =id;
        this.listPlaylist = songs;
        this.songTitle = songTitle;

    }

    public String getSongTitle(){return songTitle;}

    public void setPlaylistTitle(String playlistTitle){this.songTitle = songTitle;}

    public int getId(){return id;}

    public void setId(int id){this.id = id;}

    public List<Song> getListPlaylist(){return listPlaylist;}

    public void setListPlaylist(List<Song> listPlaylist){this.listPlaylist = listPlaylist;}

}
