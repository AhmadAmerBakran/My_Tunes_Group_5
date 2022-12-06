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

import java.awt.*;
import java.util.ArrayList;

public class PlaylistsView extends ControllerManager {
    ArrayList<String> userListName = new ArrayList<String>();
    public ArrayList<String> givePlayListname(){

        return userListName;

    }


    @FXML
    TextField txtPlaylistName;
    private AllPlaylistsModel allPlaylistsModel;

    public PlaylistsView() {
        try {
           allPlaylistsModel = new AllPlaylistsModel();
        }catch (Exception e){

            e.printStackTrace();
        }
    }

    public void updatePlaylist(ActionEvent actionEvent) throws Exception {
        String updatedPlaylistName = txtPlaylistName.getText();
        AllPlaylists updatedPlaylist = new AllPlaylists(allPlaylistsModel.getSelectedPlaylist().getPlaylistId(), updatedPlaylistName, allPlaylistsModel.getSelectedPlaylist().getPlaylistSongsNumber());
        allPlaylistsModel.updatePlaylist(updatedPlaylist);
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void savePlaylist(ActionEvent actionEvent) {

        String playlistName = txtPlaylistName.getText();

        givePlayListname().add(playlistName);

        try {

            allPlaylistsModel.createNewPlaylist(playlistName);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }


    public void cancelEditting(ActionEvent actionEvent) {
    }

    @Override
    public void setup() {
        allPlaylistsModel = getModel().getAllPlaylistsModel();

        txtPlaylistName.setText(allPlaylistsModel.getSelectedPlaylist().getPlaylistName());

    }

    public void setup2(){
        allPlaylistsModel = getModel().getAllPlaylistsModel();
    }
}
