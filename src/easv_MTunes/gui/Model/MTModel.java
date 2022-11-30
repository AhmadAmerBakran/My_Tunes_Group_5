package easv_MTunes.gui.Model;

import java.sql.SQLException;

public class MTModel {
    private SongModel songModel;

    public MTModel() throws SQLException {
        songModel = new SongModel();
    }

    public SongModel getSongModel()
    {
        return songModel;
    }

    public void setSongModel(SongModel songModel) {
        this.songModel = songModel;
    }
}
