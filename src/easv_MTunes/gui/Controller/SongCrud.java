package easv_MTunes.gui.Controller;

import easv_MTunes.BE.Song;
import easv_MTunes.gui.Model.SongModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class SongCrud extends ControllerManager{
    @FXML
    private TextField txtCategory, txtTime, txtArtist, txtFile, txtTitle;


    private SongModel model;
    public SongCrud() {
        try {
            model = new SongModel();
        }catch (Exception e){

            e.printStackTrace();
        }
    }


    public void chooseFile(ActionEvent actionEvent) {
        Stage stage = new Stage();
        FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog(stage);
        if(file != null) {
            txtFile.setText(file.toString());
        }
    }

    public void save(ActionEvent actionEvent) {
        String title = txtTitle.getText();
        String artist = txtArtist.getText();
        File file = new File(txtFile.getText());

        try {
            model.createNewSong(title, artist, file);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    @Override
    public void setup() {
        model = getModel().getSongModel();

        txtTitle.setText(model.getSelectedSong().getTitle());
        txtArtist.setText(model.getSelectedSong().getArtist());

    }



    public void updateSong(ActionEvent actionEvent) throws Exception {
        String updatedTitle = txtTitle.getText();
        String updatedArtist = txtArtist.getText();
        Song updatedSong = new Song(model.getSelectedSong().getId(), updatedTitle, updatedArtist, model.getSelectedSong().getSongFile());
        model.updateSong(updatedSong);
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();

    }
}
