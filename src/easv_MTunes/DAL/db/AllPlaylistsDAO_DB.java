package easv_MTunes.DAL.db;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import easv_MTunes.BE.AllPlaylists;
import easv_MTunes.BE.Playlist;
import easv_MTunes.BE.Song;
import easv_MTunes.DAL.IAllPlaylistsDataAccess;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AllPlaylistsDAO_DB implements IAllPlaylistsDataAccess {

    private DBConnector dbConnector;

    public AllPlaylistsDAO_DB() {
        dbConnector = new DBConnector();
    }

    @Override
    public List<AllPlaylists> getAllPlaylists() throws SQLServerException {

        List<AllPlaylists> playLists = new ArrayList<>();

        //Get connection to database
        try (Connection connection = dbConnector.getConnection())
        {
            //Create an SQL command
            String sql = "SELECT * FROM AllPlaylists;";

            //Create some statements
            Statement statement = connection.createStatement();

            //Do what you suppose to do
            if(statement.execute(sql))
            {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next())
                {
                    int playlistId = resultSet.getInt("Id");
                    String playlistName = resultSet.getString("Name");
                    int playlistSongsNumber = resultSet.getInt("Songs");


                    AllPlaylists allPlaylists = new AllPlaylists(playlistId, playlistName, playlistSongsNumber);
                    playLists.add(allPlaylists);
                }
            }
        } catch (SQLException e) {
        }
        return playLists;
    }

    @Override
    public AllPlaylists addPlaylist(AllPlaylists newPlaylist) throws Exception {
        return null;
    }

    @Override
    public void deletePlaylist(AllPlaylists deletedPlaylist) throws Exception {
        try (Connection conn = dbConnector.getConnection()) {

            String sql = "DELETE FROM AllPlaylists WHERE Name = (?) AND Id = (?);";


            PreparedStatement stmt = conn.prepareStatement(sql);

            // Bind parameters
            stmt.setString(1, deletedPlaylist.getPlaylistName());
            stmt.setInt(2, deletedPlaylist.getPlaylistId());


            stmt.executeUpdate();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception( ex);
        }


    }


    @Override
    public void updatePlaylist(AllPlaylists updatedPlaylist) throws Exception {
        try (Connection connection = dbConnector.getConnection()) {

            String sql = "UPDATE AllPlaylists SET Name = ? WHERE Id = ?";

            PreparedStatement stmt = connection.prepareStatement(sql);

            // Bind parameters
            stmt.setString(1, updatedPlaylist.getPlaylistName());
            stmt.setInt(2, updatedPlaylist.getPlaylistId());

            stmt.executeUpdate();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not update playlist", ex);
        }

    }
}
