package easv_MTunes.BE;

import java.io.File;

public class Song {
    private int id;
    private String title, artist;
    private File songFile;


    public Song(int id, String title, String artist, File songFile) {
        this.id = id;
        this.title = title;
        this.songFile = songFile;
        this.artist = artist;

    }
    /*public  Song(int id, String title){
        this.id = id;
        this.title = title;
    }*/

    /**
     * Getter for artist of the song
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Setter for the artist
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }
    /**
    * Getter for the song file
    */
    public File getSongFile() {
        return songFile;
    }

    /**
     * Setter for the song file
     */
    public void setSongFile(File songFile) {
        this.songFile = songFile;
    }

    /**
     * Getter for the songs id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for the songs id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for the song title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for the song title
     */
    public void setTitle(String title) {
        this.title = title;
    }


    /**
     * Creates string with song id, title and artist of the song
     */
    @Override
    public String toString() {
        return id + "       " +'\'' + title + '\'' + "      " + artist;
    }
}
