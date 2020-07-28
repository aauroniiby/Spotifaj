/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.util.List;
import BLL.Tracks;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.Query;

public class TracksRepository extends EntMngClass implements TracksInterface{

    @Override
    public void create(Tracks t) throws CrudFormException {
        try {
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new CrudFormException("Msg \n" + e.getMessage());
        }
    }

    @Override
    public void edit(Tracks t) throws CrudFormException {
        try {
            em.getTransaction().begin();
            em.merge(t);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new CrudFormException("Msg \n" + e.getMessage());
        }
    }

    @Override
    public void delete(Tracks t) throws CrudFormException {
        try {
            em.getTransaction().begin();
            em.remove(t);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new CrudFormException("Msg \n" + e.getMessage());
        }
    }

    @Override
    public List<Tracks> findAll() throws CrudFormException {
        try {
            return em.createNamedQuery("Tracks.findAll").getResultList();
        } catch (Exception e) {
            throw new CrudFormException("Msg \n" + e.getMessage());
        }
    }

    @Override
    public Tracks findById(Integer ID) throws CrudFormException {
        try {
            Query query = em.createQuery("SELECT t FROM Tracks WHERE t.id = :id");
            query.setParameter("id", ID);
            return (Tracks) query.getSingleResult();
        } catch (Exception e) {
           throw new CrudFormException("Msg \n" + e.getMessage());
        }
    }
    
  
    public void insertNewTrack(String title, String artist, String album, String year, String rating, String genre) throws CrudFormException {
        try {
            Query query = em.createNativeQuery("INSERT INTO Tracks (title, artist, album, year, rating, genre) VALUES (?, ?, ?, ?, ?, ?)");
            em.getTransaction().begin();
            query.setParameter(1, title);
            query.setParameter(2, artist);
            query.setParameter(3, album);
            query.setParameter(4, year);
            query.setParameter(5, rating);
            query.setParameter(6, genre);
            query.executeUpdate();
            em.getTransaction().commit();
          
        } catch (Exception e) {
           throw new CrudFormException("Msg \n" + e.getMessage());
        }
    }
    
    public Integer fetchLastInsertId() throws CrudFormException {
        try {
            Query query = em.createNativeQuery("SELECT id FROM Tracks ORDER BY id DESC LIMIT 1");
            
            return (Integer) query.getSingleResult();
        }   catch (Exception e) {
           throw new CrudFormException("Msg \n" + e.getMessage());
        }
    }
    
    public void deleteTrack(Integer id) throws CrudFormException {
        try {
            Query query = em.createNativeQuery("DELETE FROM Tracks t WHERE t.id = ?");
            em.getTransaction().begin();
            query.setParameter(1, id);
            query.executeUpdate();
            em.getTransaction().commit();
          
        } catch (Exception e) {
           throw new CrudFormException("Msg \n" + e.getMessage());
        }
    }
    
    public List fetchOwnedTracks(Integer userId) throws CrudFormException {
        try {
            
            Query query = em.createNativeQuery("SELECT * FROM Tracks t WHERE t.owner = ?");
            
            query.setParameter(1, userId);
            
            return query.getResultList();
        } catch (Exception e) {
            throw new CrudFormException("Msg \n" + e.getMessage());
        }
    }
    
    public List filterOwnTracks(
            String title, 
            String artist, 
            Integer genre, 
            Boolean zeroStarRating, 
            Boolean oneStarRating, 
            Boolean twoStarRating, 
            Boolean threeStarRating, 
            Boolean fourStarRating,
            Boolean fiveStarRating,
            Integer userId
            )  throws CrudFormException {
        try {
            String conditions = "";
            if(!"".equals(title)) {
                conditions += " title LIKE \"%" + title + "%\"";
            }
            
            if(!"".equals(artist)) {
                if(!"".equals(conditions)) {
                    conditions += " OR artist LIKE \"%" + artist + "%\"";
                } else {
                    conditions += " artist LIKE\"%" + artist + "%\"";
                }
            }
            
            if(genre != 0) {
                if(!"".equals(conditions)) {
                    conditions += " AND genre=" + genre;
                } else {
                    conditions += " genre=" + genre;
                }
            }
            
            if(oneStarRating || zeroStarRating || twoStarRating || threeStarRating || fourStarRating || fiveStarRating) {
                conditions += " AND FIND_IN_SET(rating," ;
                
                Set<Integer> ratings = new HashSet<>();
                
                
                if(zeroStarRating) {
                    ratings.add(0);
                }
                if(oneStarRating) {
                    ratings.add(1);
                }
                if(twoStarRating) {
                    ratings.add(2);
                }
                if(threeStarRating) {
                    ratings.add(3);
                }
                if(fourStarRating) {
                    ratings.add(4);
                }
                if(fiveStarRating) {
                    ratings.add(5);
                }
                
                conditions += "'";  
                int i = 0;
                for (Integer rating: ratings) {
                    
                    if(i++ == ratings.size() - 1){
                        conditions += rating;
                    } else {
                        conditions += rating + ",";
                    }
                }
                
                conditions += "')";
            }
            
            
            Query query = em.createNativeQuery("SELECT * FROM Tracks t WHERE" + conditions + " AND owner = " + userId);
            
            return query.getResultList();
        } catch (Exception e) {
            throw new CrudFormException("Msg \n" + e.getMessage());
        }
    }
}