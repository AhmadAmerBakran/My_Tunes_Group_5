package easv_MTunes.BE;


public class AllPlaylists {
    private String playlistName;
    private int playlistId;
    private AllPlaylists playlist;

    public AllPlaylists(int playlistId, String playlistName) {

        this.playlistName = playlistName;
        this.playlistId = playlistId;

    }

    /**
     *Getter for Playlist name
     */
    public String getPlaylistName() {
        return playlistName;
    }

    /**
     *Setter for playlist name
     */
    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    /**
     * Getter for playlist id
     */
    public int getPlaylistId() {
        return playlistId;
    }



    /**
     * Getter for the Playlists
     */
    public AllPlaylists getPlaylist() {

        return playlist;
    }

    /**
     * Creates string with playlist name, id, number and the playlist itself
     */
    @Override
    public String toString() {
        return "AllPlaylists{" +
                "playlistName='" + playlistName + '\'' +
                ", playlistId=" + playlistId +
                ", playlistSongsNumber=" +
                ", playlist=" + playlist +
                '}';
    }
}
