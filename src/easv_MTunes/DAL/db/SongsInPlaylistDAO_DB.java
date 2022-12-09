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

    public ArrayList<Song> getAllPlaylistSongs() throws SQLException {
        //Create and return songs
        ArrayList<Song> allSongList = new ArrayList<>();

        //Get connection to database
        try (Connection connection = dbConnector.getConnection())
        {
            //Create an SQL command
            String sql = "SELECT *\n" +
                    "            FROM AllPlaylists pl, SongsInPlaylist sip, Song s \n" +
                    "            WHERE pl.Id = sip.PlaylistID\n" +
                    "            AND s.Id = sip.SongID\n" +
                    "            ORDER BY pl.Name";

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

    public Song addSongToPlaylist(AllPlaylists playlist, Song song) throws Exception {
        // Dynamic SQL

        String sql = "INSERT INTO [SongsInPlaylist] VALUES (?,?);";

        try (Connection connection = dbConnector.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Bind parameters
            stmt.setInt(1, playlist.getPlaylistId());
            stmt.setInt(2, song.getId());

            // Run the specified SQL statement
            stmt.executeUpdate();

            // Get the generated ID from the DB
            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;


            if (rs.next()) {
                id = rs.getInt(1);
            }

            // Create song object and send up the layers
            String title = song.getTitle();

            Song songInPlaylist = new Song(id, title, song.getArtist(), song.getSongFile());
            return songInPlaylist;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw new Exception("Could not add Song to playlist", ex);
        }

    }
    public void deleteSongFromPlaylist(AllPlaylists playlist, Song song) throws Exception {

        try (Connection conn = dbConnector.getConnection()) {

            String sql = "DELETE FROM SongsInPlaylist WHERE SongID = (?);";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, song.getId());

            stmt.executeUpdate();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception(ex);
        }

    }

    }