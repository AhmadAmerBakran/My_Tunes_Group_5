package easv_MTunes.gui.Model;

import easv_MTunes.BE.AllPlaylists;
import easv_MTunes.BE.Song;
import easv_MTunes.BLL.AllPlaylistsManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;


public class AllPlaylistsModel {
    private AllPlaylistsManager allPlaylistsManager;
    private ObservableList<AllPlaylists> allPlaylistsToBeViewed;
    private AllPlaylists selectedPlaylist;

    /**
     * Getter for the selected playlist
     */
    public AllPlaylists getSelectedPlaylist() {
        return selectedPlaylist;
    }

    /**
     * Setter for the  selected playlist
     */
    public void setSelectedPlaylist(AllPlaylists selectedPlaylist) {
        this.selectedPlaylist = selectedPlaylist;
    }

    /**
     * Creates a new AllPLaylistsManager to be able to use the methods from the AllPlaylistsManager class
     *
     * @throws SQLException
     */
    public AllPlaylistsModel() throws SQLException {
        allPlaylistsManager = new AllPlaylistsManager();
        allPlaylistsToBeViewed = FXCollections.observableArrayList();
        allPlaylistsToBeViewed.addAll(allPlaylistsManager.getAllPlaylists());
    }

    /**
     * Creates a List with all songs from the allPlaylistsToBeViewed method
     */
    public ObservableList<AllPlaylists> getObservableAllPlaylists()
    {
        return allPlaylistsToBeViewed;
    }

    /**
     * Gets value of deletedPlaylist from the allPlaylistsManager class
     * Remove value from allPlaylistsToBeViewed
     * @throws Exception
     */
    public void deletePlaylist(AllPlaylists deletedPlaylist) throws Exception {
        allPlaylistsManager.deletePlaylist(deletedPlaylist);
        allPlaylistsToBeViewed.remove(deletedPlaylist);
    }

    /**
     *
     * Get the updatedPlaylist from the allPlaylistsManager class
     * @throws Exception
     */
    public void updatePlaylist(AllPlaylists updatedPlaylist) throws Exception {
        allPlaylistsManager.updatePlaylist(updatedPlaylist);

        // update ListView
        allPlaylistsToBeViewed.clear();
        allPlaylistsToBeViewed.addAll(allPlaylistsManager.getAllPlaylists());
    }

    /**
     * Get the name value from createNewPlaylist method in the allPlaylistsManager class
     * @throws Exception
     */
    public void createNewPlaylist(String name) throws Exception {
        AllPlaylists allPlaylists = allPlaylistsManager.createNewPlaylist(name);
        allPlaylistsToBeViewed.add(allPlaylists);

    }

}
