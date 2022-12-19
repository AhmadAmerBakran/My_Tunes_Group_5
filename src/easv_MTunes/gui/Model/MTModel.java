package easv_MTunes.gui.Model;

import java.sql.SQLException;

public class MTModel {
    private SongModel songModel;
    private AllPlaylistsModel allPlaylistsModel;

    public MTModel() throws Exception {
        songModel = new SongModel();
        allPlaylistsModel = new AllPlaylistsModel();
    }

    /**
     * Returns song model
     */
    public SongModel getSongModel()
    {
        return songModel;
    }

    /**
     * Setter for the songModel
     */
    public void setSongModel(SongModel songModel) {
        this.songModel = songModel;
    }

    /**
     * Returns the allPlaylistsModel
     */
    public AllPlaylistsModel getAllPlaylistsModel() {
        return allPlaylistsModel;
    }

    /**
     * Setter for the allPlaylistsModel
     */
    public void setAllPlaylistsModel(AllPlaylistsModel allPlaylistsModel) {
        this.allPlaylistsModel = allPlaylistsModel;
    }

}
