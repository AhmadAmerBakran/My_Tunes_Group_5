package easv_MTunes.gui.Controller;

import easv_MTunes.BE.AllPlaylists;
import easv_MTunes.BE.Song;
import easv_MTunes.gui.Model.AllPlaylistsModel;
import easv_MTunes.gui.Model.SongModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

//PlaylistView is a controller used to control the PlaylistView window.
public class PlaylistsView extends ControllerManager {

    @FXML
    TextField txtPlaylistName;
    private AllPlaylistsModel allPlaylistsModel;
    private SongModel songModel;


    //Constructor for PlaylistView
    public PlaylistsView() {
        try {
           //Instantiate variables
            allPlaylistsModel = new AllPlaylistsModel();
           songModel = new SongModel();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*updatePlaylist is used when you click the update button in the PlaylistView window
    The method updates the selected playlist
     */

    public void updatePlaylist(ActionEvent actionEvent) throws Exception {
        //Gets the text from the text field and sets the variable to it
        String updatedPlaylistName = txtPlaylistName.getText();
        //Sets the updatedPlaylist variable to the selected playlist
        AllPlaylists updatedPlaylist = new AllPlaylists(allPlaylistsModel.getSelectedPlaylist().getPlaylistId(), updatedPlaylistName);
        //Calls a method from another class
        allPlaylistsModel.updatePlaylist(updatedPlaylist);

        //Closes the window
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    /*savePlaylist is used when you click the save button in the PlaylistView window
    The method creates a new playlist with the given title
     */
    public void savePlaylist(ActionEvent actionEvent) {
        //Gets the text from the text field and sets the variable to it
        String playlistName = txtPlaylistName.getText();
        Song song =songModel.getSelectedSong();

        try {
            //Calls a method from another class
            allPlaylistsModel.createNewPlaylist(playlistName);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //Closes the window
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    /*cancelEditting is used when you click the cancel button in the PlaylistView window
    The method closes the window
     */
    public void cancelEditting(ActionEvent actionEvent) {
        //Closes the window
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    @Override
    public void setup() {
        allPlaylistsModel = getModel().getAllPlaylistsModel();

    }
}
