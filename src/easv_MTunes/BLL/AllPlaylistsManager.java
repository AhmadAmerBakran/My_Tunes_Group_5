package easv_MTunes.BLL;

import easv_MTunes.BE.AllPlaylists;
import easv_MTunes.DAL.IAllPlaylistsDataAccess;
import easv_MTunes.DAL.db.AllPlaylistsDAO_DB;

import java.sql.SQLException;
import java.util.List;

public class AllPlaylistsManager {

    IAllPlaylistsDataAccess allPlaylistsDAO_DB;

    /**
     * Creates AllPlaylistsDAO_DB to be able to use methods from AllPlaylistsDAO_DB class
     */
    public AllPlaylistsManager() {
        allPlaylistsDAO_DB = new AllPlaylistsDAO_DB();
    }

    /**
     * Returns value of the createNewPlaylist method from the AllPlaylistsDAO_DB class
     * @throws Exception
     */
    public AllPlaylists createNewPlaylist(String name) throws Exception {
        return allPlaylistsDAO_DB.createPlaylist(name);
    }

    /**
     * Returns the getAllPlaylists method from the AllPlaylistsDAO_DB class
     * @throws SQLException
     */
    public List<AllPlaylists> getAllPlaylists() throws SQLException {
        return allPlaylistsDAO_DB.getAllPlaylists();
    }

    /**
     *
     * Returns the value of the deletedPlaylist method from the AllPlaylistsDAO_DB class
     * @throws Exception
     */
    public void deletePlaylist (AllPlaylists deletedPlaylist) throws Exception {
        allPlaylistsDAO_DB.deletePlaylist(deletedPlaylist);
    }

    /**
     * Returns the value of updatedPlaylist method from the AllPlaylistsDAO_DB class
     * @throws Exception
     */
    public void updatePlaylist(AllPlaylists updatedPlaylist) throws Exception {
        allPlaylistsDAO_DB.updatePlaylist(updatedPlaylist);
    }
}
