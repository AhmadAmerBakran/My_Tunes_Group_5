package easv_MTunes.DAL.db;


import com.microsoft.sqlserver.jdbc.SQLServerException;
import easv_MTunes.BE.AllPlaylists;
import easv_MTunes.BE.Song;
import easv_MTunes.DAL.ISongsInPlaylistDataAccess;


import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


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
                    "            WHERE pl.Id = sip.PlaylistID and sip.PlaylistID =" + playlistNumber +
                    "            AND s.Id = sip.SongID;";

            //Create some statements
            Statement statement = connection.createStatement();

            //Do what you suppose to do
            if(statement.execute(sql))
            {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next())
                {
                    int id = resultSet.getInt("Id");
                    String title = resultSet.getString("Title");



                    Song song = new Song(id, title);
                    allSongList.add(song);
                }
            }
        }
        return allSongList;
    }

    public void addSongToPlaylist(AllPlaylists selectedPlaylist, Song selectedSong, int size) throws Exception {
        // Dynamic SQL

        String sql = "INSERT INTO [SongsInPlaylist] VALUES (?,?,?);";

        try (Connection connection = dbConnector.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);

            int selectedPlaylistID = selectedPlaylist.getPlaylistId();
            int selectedSongID = selectedSong.getId();
            int songRank = size + 1;
            // Bind parameters
            stmt.setInt(1, selectedPlaylistID);
            stmt.setInt(2, selectedSongID);
            stmt.setInt(3, songRank);

            // Run the specified SQL statement
            stmt.executeUpdate();

            // Get the generated ID from the DB
            /*ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;*/


            /*if (rs.next()) {
                id = rs.getInt(1);
            }*/

            // Create song object and send up the layers
            //String title = song.getTitle();

            //Song songInPlaylist = new Song(id, title, song.getArtist(), song.getSongFile());
            //return songInPlaylist;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw new Exception("Could not add Song to playlist", ex);
        }
    }

    public int getRank(int songID, int playlistID){
        int rank = 0;

        String sql = "SELECT * FROM SongsInPlaylist SIP WHERE SIP.SongID =" + songID + " AND " + "SIP.PlaylistID=" + playlistID + ";";

        try(Connection connection = dbConnector.getConnection()) {
            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                rank = rs.getInt("Rank");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rank;

    }

    public void deleteSongFromPlaylist(AllPlaylists selectedPlaylist, Song selectedSong, int selectedRank) throws Exception {

        String sql = "DELETE FROM SongsInPlaylist WHERE SongID = (?) AND PlaylistID = (?) and Rank = (?);";
        try (Connection conn = dbConnector.getConnection()) {

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, selectedSong.getId());
            stmt.setInt(2, selectedPlaylist.getPlaylistId());
            stmt.setInt(3, selectedRank);

            stmt.executeUpdate();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception(ex);
        }

    }
}