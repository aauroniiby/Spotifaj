/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BLL.PlaylistTracks;
import java.util.List;
import javax.persistence.Query;

public class PlaylistTracksRepository extends EntMngClass implements PlaylistTracksInterface {

    @Override
    public void create(PlaylistTracks p) throws CrudFormException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(PlaylistTracks p) throws CrudFormException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(PlaylistTracks p) throws CrudFormException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PlaylistTracks> findAll() throws CrudFormException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PlaylistTracks findById(Integer ID) throws CrudFormException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void insertTrackToPlaylist(Integer playlistId, Integer trackId) throws CrudFormException {
        try {
            Query query = em.createNativeQuery("INSERT INTO Playlist_tracks (playlistId, trackId) VALUES (?, ?)");
            em.getTransaction().begin();
            query.setParameter(1, playlistId);
            query.setParameter(2, trackId);
            
            query.executeUpdate();
            em.getTransaction().commit();
          
        } catch (Exception e) {
           throw new CrudFormException("Msg \n" + e.getMessage());
        }
    }
    
    public void deleteTrackFromPlaylist(Integer playlistId, Integer trackId) throws CrudFormException {
        try {
            Query query = em.createNativeQuery("DELETE FROM Playlist_Tracks pt WHERE pt.playlistId = ? AND pt.trackId = ?");
            em.getTransaction().begin();
            query.setParameter(1, playlistId);
            query.setParameter(2, trackId);
            query.executeUpdate();
            em.getTransaction().commit();
          
        } catch (Exception e) {
           throw new CrudFormException("Msg \n" + e.getMessage());
        }
    }
    
}
