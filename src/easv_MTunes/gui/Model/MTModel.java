package easv_MTunes.gui.Model;

import java.sql.SQLException;

public class MTModel {
    private SongModel songModel;
    private AllPlaylistsModel allPlaylistsModel;

    public MTModel() throws SQLException {
        songModel = new SongModel();
        allPlaylistsModel = new AllPlaylistsModel();
    }

    public SongModel getSongModel()
    {
        return songModel;
    }

    public void setSongModel(SongModel songModel) {
        this.songModel = songModel;
    }

    public AllPlaylistsModel getAllPlaylistsModel() {
        return allPlaylistsModel;
    }

    public void setAllPlaylistsModel(AllPlaylistsModel allPlaylistsModel) {
        this.allPlaylistsModel = allPlaylistsModel;
    }
}
