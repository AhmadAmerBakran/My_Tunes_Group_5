package easv_MTunes.DAL.db;

import easv_MTunes.BE.Playlist;
import easv_MTunes.BE.Song;
import easv_MTunes.DAL.IPlaylistDataAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO_DB implements IPlaylistDataAccess {
    private DBConnector dbConnector;

    public PlaylistDAO_DB() throws SQLException {dbConnector = new DBConnector();}

    @Override
    public ArrayList<Playlist> getPlaylistSongs() throws Exception {
        String songTitle;
        {
            ArrayList<Playlist> allPlaylistSongs = new ArrayList<>();

            Playlist playlist = null;
            try (Connection connection = dbConnector.getConnection()) {
                String sql = "SELECT * FROM Playlist";

                Statement statement = connection.createStatement();

                if (statement.execute(sql)) {
                    ResultSet resultSet = statement.getResultSet();
                    while (resultSet.next()) {
                        int id = resultSet.getInt("Id");
                        songTitle = resultSet.getString("Title");
                        ArrayList listPlaylist = new ArrayList<>(allPlaylistSongs);

                        playlist = new Playlist(id, listPlaylist, songTitle);
                        allPlaylistSongs.add(playlist);
                    }
                }
            }
            return allPlaylistSongs;
        }

        /**public Playlist createPlaylist(int id, String songTitle) throws Exception {

            String sql = "INSERT INTO Playlist (Id, playlistTitle) VALUES (?,?);";

            try (Connection connection = dbConnector.getConnection()) {
                PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                stmt.setInt(1, id);
                stmt.setString(2, songTitle);

                stmt.executeUpdate();

                ResultSet rs = stmt.getGeneratedKeys();
                int id = 0;

                if (rs.next()){
                    id = rs.getInt(1);
                }
                Playlist playlist = new Playlist(id,List<Song>,songTitle);
                return playlist;
            }
            catch (SQLException ex){
                ex.printStackTrace();
                throw new Exception("Could not create playlist", ex);
            }
        }*/
    }}
