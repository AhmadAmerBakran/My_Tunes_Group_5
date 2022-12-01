package easv_MTunes.DAL;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import easv_MTunes.BE.AllPlaylists;
import easv_MTunes.BE.Playlist;
import java.util.List;



public interface IAllPlaylistsDataAccess {
    public List<AllPlaylists> getAllPlaylists() throws SQLServerException;
    public AllPlaylists addPlaylist(AllPlaylists newPlaylist) throws Exception;
    public void deletePlaylist(AllPlaylists deletedPlaylist) throws Exception;
    public void updatePlaylist(AllPlaylists updatedPlaylist) throws Exception;
}
