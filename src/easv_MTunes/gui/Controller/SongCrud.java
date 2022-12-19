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
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

//SongCrud is a controller used to control the SongCrud window.
public class SongCrud extends ControllerManager implements Initializable{
    @FXML
    private TextField txtCategory, txtTime, txtArtist, txtFile, txtTitle;

    private File file;

    private String targetString = "SongFiles";
    private Path target = Paths.get(targetString);

    private SongModel model;
    //Constructor for SongCrud.
    public SongCrud() {
        try {
            model = new SongModel();
        }catch (Exception e){

            e.printStackTrace();
        }
    }


    /*chooseFile is used when the user clicks the choose button in the SongCrud window.
    When the button is clicked a new window is opened where the user can choose a song to add to the song-list.
     */
    public void chooseFile(ActionEvent actionEvent) {
        //Instantiate variables
        Stage stage = new Stage();
        FileChooser fc = new FileChooser();
        //Opens a new window with file explorer.
        file = fc.showOpenDialog(stage);
        if(file != null) {
            //Gets the path of the file and copies it to a text field.
            txtFile.setText(file.toURI().toString());
        }
    }

    /*save is used when the user clicks the save button in the SongCrud window.
    It creates a new song in the song list based on the text written in the 2 text fields and the file path.
     */
    public void save(ActionEvent actionEvent) {
        //Instantiate variables
        String title = txtTitle.getText();
        String artist = txtArtist.getText();
        //Copy the song to the SongFiles in the project
        try {
            Files.copy(file.toPath(), target.resolve(file.toPath().getFileName()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        file = new File(targetString + "/" + file.getName());
        try {
            //Calls a method from the SongModel
            model.createNewSong(title, artist, file);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //Closes the window
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    /*cancel is used when the user clicks the cancel button in the SongCrud window
    The method closes the window
     */
    public void cancel(ActionEvent actionEvent) {
        //Closes the window
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    //setup is to set the model variable to the current SongModel
    @Override
    public void setup() {
        model = getModel().getSongModel();

        if(model.getSelectedSong() != null) {
            txtTitle.setText(model.getSelectedSong().getTitle());
            txtArtist.setText(model.getSelectedSong().getArtist());
        }
    }


    /*updateSong is used when te user clicks the update button in the SongCrud window
    It updates the selected song with the values written in the text fields
     */
    public void updateSong(ActionEvent actionEvent) throws Exception {
        //Instantiates variables
        String updatedTitle = txtTitle.getText();
        String updatedArtist = txtArtist.getText();
        Song updatedSong = new Song(model.getSelectedSong().getId(), updatedTitle, updatedArtist, model.getSelectedSong().getSongFile());
        //Calls the updateSong method from the SongModel
        model.updateSong(updatedSong);
        //Closes window
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
