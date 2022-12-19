package easv_MTunes.DAL.db;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import easv_MTunes.BE.AllPlaylists;
import easv_MTunes.DAL.IAllPlaylistsDataAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AllPlaylistsDAO_DB implements IAllPlaylistsDataAccess {

    private DBConnector dbConnector;

    /**
     * Creates new dbConnector to be able to use the methods from the DBConnector class
     */
    public AllPlaylistsDAO_DB() {
        dbConnector = new DBConnector();
    }


    @Override
    public List<AllPlaylists> getAllPlaylists() throws SQLServerException {
        //Creates arraylist called playlists
        List<AllPlaylists> playLists = new ArrayList<>();
        //Create an SQL command
        String sql = "SELECT * FROM AllPlaylists;";
        //Get connection to database
        try (Connection connection = dbConnector.getConnection())
        {



            //Create a statements
            Statement statement = connection.createStatement();

            //Executes SQL command
            if(statement.execute(sql))
            {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next())
                {
                    //Gets the id's, names and songs and makes them into strings
                    int playlistId = resultSet.getInt("Id");
                    String playlistName = resultSet.getString("Name");
                    int playlistSongsNumber = resultSet.getInt("Songs");

                    //Adds values from database to playlists (Arraylist)
                    AllPlaylists allPlaylists = new AllPlaylists(playlistId, playlistName, playlistSongsNumber);
                    playLists.add(allPlaylists);
                }
            }
        } catch (SQLException e) {
        }
        return playLists;
    }
    @Override
    public AllPlaylists createPlaylist(String name) throws Exception {
       //Creates an SQL command
        String sql = "INSERT INTO AllPlaylists (Name) VALUES (?);";

        //Get connection to the database
        try (Connection connection = dbConnector.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Bind parameters
            stmt.setString(1,name);

            // Run the SQL statement
            stmt.executeUpdate();

            // Get the generated ID from the DB
            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;
            int songsNumber = 0;

            if (rs.next()) {
                id = rs.getInt(1);

            }
            // Create song object and send up the layers
            AllPlaylists allPlaylists = new AllPlaylists(id,name, songsNumber);
            return allPlaylists;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new Exception("Could not create a Song", ex);
        }
    }

    @Override
    public void deletePlaylist(AllPlaylists deletedPlaylist) throws Exception {
        //Creates an SQL command
        String sql = "DELETE FROM AllPlaylists WHERE Name = (?) AND Id = (?);";

        //Get connection to database
        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Bind parameters
            stmt.setString(1, deletedPlaylist.getPlaylistName());
            stmt.setInt(2, deletedPlaylist.getPlaylistId());

            //Run the SQL statement
            stmt.executeUpdate();


        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception( ex);
        }


    }


    @Override
    public void updatePlaylist(AllPlaylists updatedPlaylist) throws Exception {
        //Get connection to database
        try (Connection connection = dbConnector.getConnection()) {

            //Create an SQL command
            String sql = "UPDATE AllPlaylists SET Name = ? WHERE Id = ?";

            // Creates a statement
            PreparedStatement stmt = connection.prepareStatement(sql);

            // Bind parameters
            stmt.setString(1, updatedPlaylist.getPlaylistName());
            stmt.setInt(2, updatedPlaylist.getPlaylistId());

            // Run the SQL statement
            stmt.executeUpdate();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not update playlist", ex);
        }

    }
}
