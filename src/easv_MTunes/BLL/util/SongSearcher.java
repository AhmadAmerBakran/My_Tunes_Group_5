package easv_MTunes.BLL.util;

import easv_MTunes.BE.Song;

import java.util.ArrayList;
import java.util.List;

public class SongSearcher {
    /**
     * Creates arraylist (searchResult)
     * Checks for matches between user input and the song title and the song artist
     * Returns all matches
     */
    public List<Song> search(List<Song> searchBase, String query) {
        List<Song> searchResult = new ArrayList<>();

        for (Song song : searchBase) {
            if(compareToSongTitle(query, song) || compareToSongArtist(query, song))
            {
                searchResult.add(song);
            }
        }

        return searchResult;
    }

    /**
     * Checks if the user input matches any song titles
     */
    private boolean compareToSongTitle(String query, Song song) {
        return song.getTitle().contains(query);
    }

    /**
     * Checks if the user input matches any of the artists
     */
    private boolean compareToSongArtist(String query, Song song) {
        return song.getArtist().toLowerCase().contains(query.toLowerCase());
    }
}
