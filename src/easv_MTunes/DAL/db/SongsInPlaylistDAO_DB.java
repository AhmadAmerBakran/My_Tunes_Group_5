package easv_MTunes.DAL.db;


import easv_MTunes.BE.AllPlaylists;
import easv_MTunes.BE.Song;
import easv_MTunes.DAL.ISongsInPlaylistDataAccess;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;


public class SongsInPlaylistDAO_DB implements ISongsInPlaylistDataAccess {
    private DBConnector dbConnector;

    public SongsInPlaylistDAO_DB(){
        dbConnector = new DBConnector();
    }

    public ArrayList<Song> getAllPlaylistSongs(int playlistNumber) throws SQLException {
        //Create and return songs
        ArrayList<Song> allSongList = new ArrayList<>();

        //Get connection to database
        try (Connection connection = dbConnector.getConnection())
        {
            //Create an SQL command
            String sql = "SELECT *\n" +
                    "            FROM AllPlaylists pl, SongsInPlaylist sip, Song s \n" +
                    "            WHERE s.Id = sip.SongID and sip.PlaylistID =" + playlistNumber +
                    "            AND pl.Id = sip.PlaylistID;";

            //Create some statements
            Statement statement = connection.createStatement();

            //Do what you suppose to do
            if(statement.execute(sql))
            {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next())
                {
                    int id = resultSet.getInt("SongID");
                    String title = resultSet.getString("Title");
                    String artist = resultSet.getString("Artist");
                    String songPath = resultSet.getString("Path");
                    File songFile = new File(songPath);


                    Song song = new Song(id, title, artist, songFile);
                    allSongList.add(song);
                }
            }
        }
        return allSongList;
    }

    public void addSongToPlaylist(AllPlaylists selectedPlaylist, Song selectedSong, int size) throws Exception {
        // Create an SQL command
        String sql = "INSERT INTO [SongsInPlaylist] VALUES (?,?,?);";

        //Get connection to database
        try (Connection connection = dbConnector.getConnection()) {
            //Create a statement
            PreparedStatement stmt = connection.prepareStatement(sql);

            int selectedPlaylistID = selectedPlaylist.getPlaylistId();
            int selectedSongID = selectedSong.getId();
            int songRank = size + 1;
            // Bind parameters
            stmt.setInt(1, selectedPlaylistID);
            stmt.setInt(2, selectedSongID);
            stmt.setInt(3, songRank);

            // Run the SQL statement
            stmt.executeUpdate();


        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw new Exception("Could not add Song to playlist", ex);
        }
    }

    public int getRank(int songID, int playlistID){
        int rank = 0;

        //Create an SQL command
        String sql = "SELECT * FROM SongsInPlaylist SIP WHERE SIP.SongID =" + songID + " AND " + "SIP.PlaylistID=" + playlistID + ";";

        //Get connection to database
        try(Connection connection = dbConnector.getConnection()) {

            //Create a statement
            Statement stmt = connection.createStatement();

            //Run the SQL statement
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                rank = rs.getInt("Rank");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(songID);
        return rank;


    }

    public void deleteSongFromPlaylist(AllPlaylists selectedPlaylist, Song selectedSong, int selectedRank) throws Exception {

        //Create an SQL command
        String sql = "DELETE FROM SongsInPlaylist WHERE SongsInPlaylist.SongID = (?) AND SongsInPlaylist.PlaylistID = (?) and SongsInPlaylist.Rank = (?);";
        //Get connection to database
        try (Connection conn = dbConnector.getConnection()) {

            //Create a statement
            PreparedStatement stmt = conn.prepareStatement(sql);

            //Bind parameters
            stmt.setInt(1, selectedSong.getId());
            stmt.setInt(2, selectedPlaylist.getPlaylistId());
            stmt.setInt(3, selectedRank);
            //Run the SQL statement
            stmt.executeUpdate();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception(ex);
        }

    }
}