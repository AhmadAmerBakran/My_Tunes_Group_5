package easv_MTunes.gui.Controller;

import easv_MTunes.gui.Model.SongModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class SongCrud extends SongViewController implements Initializable {
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public void chooseFile(ActionEvent actionEvent) {
        Stage stage = new Stage();
        FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog(stage);
        txtFile.setText(file.toString());
        System.out.println(txtFile.getText());
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


    }

    public void cancel(ActionEvent actionEvent) {
    }

}
