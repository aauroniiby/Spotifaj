/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.util.List;
import BLL.Playlists;
import BLL.Tracks;
import javax.persistence.Query;

public class PlaylistsRepository extends EntMngClass implements PlaylistsInterface {

    @Override
    public void create(Playlists p) throws CrudFormException {
        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new CrudFormException("Msg \n" + e.getMessage());
        }
    }

    @Override
    public void edit(Playlists p) throws CrudFormException {
        try {
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new CrudFormException("Msg \n" + e.getMessage());
        }
    }

    @Override
    public void delete(Playlists p) throws CrudFormException {
        try {
            em.getTransaction().begin();
            em.remove(p);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new CrudFormException("Msg \n" + e.getMessage());
        }
    }

    @Override
    public List<Playlists> findAll() throws CrudFormException {
        try {
            return em.createNamedQuery("Playlists.findAll").getResultList();
        } catch (Exception e) {
            throw new CrudFormException("Msg \n" + e.getMessage());
        }
    }

    @Override
    public Playlists findById(Integer ID) throws CrudFormException {
        try {
            Query query = em.createQuery("SELECT p FROM Playlists WHERE p.id = :id");
            query.setParameter("id", ID);
            return (Playlists) query.getSingleResult();
        } catch (Exception e) {
           throw new CrudFormException("Msg \n" + e.getMessage());
        }
    }
    
    public void insertNewPlaylist(String name) throws CrudFormException {
        try {
            Query query = em.createNativeQuery("INSERT INTO Playlists (name) VALUES (?)");
            em.getTransaction().begin();
            query.setParameter(1, name);
            query.executeUpdate();
            em.getTransaction().commit();
          
        } catch (Exception e) {
           throw new CrudFormException("Msg \n" + e.getMessage());
        }
    }
    
    public void deletePlaylist(Integer id) throws CrudFormException {
        try {
            Query query = em.createNativeQuery("DELETE FROM Playlists p WHERE p.id = ?");
            em.getTransaction().begin();
            query.setParameter(1, id);
            query.executeUpdate();
            em.getTransaction().commit();
          
        } catch (Exception e) {
           throw new CrudFormException("Msg \n" + e.getMessage());
        }
    }
    
    public List<Object> fetchAllPlaylists() throws CrudFormException {
        try {
            Query query = em.createQuery("SELECT p.id, p.name FROM Playlists p");
           
            return query.getResultList();
            
        } catch (Exception e) {
           throw new CrudFormException("Msg \n" + e.getMessage());
        }
    }
    
    public List fetchPlaylistTracks(Integer playlistId) throws CrudFormException {
        try {
             
            Query query;
            query = em.createNativeQuery("SELECT t.* FROM Playlists p INNER JOIN Playlist_Tracks pt ON p.id = pt.playlistId INNER JOIN Tracks t ON pt.trackId = t.id WHERE p.id =" + playlistId);
            query.setParameter("playlistId", playlistId);
            return query.getResultList();
            
        } catch (Exception e) {
           throw new CrudFormException("Msg \n" + e.getMessage());
        }
    }
}
