/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BLL.Genres;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


public class GenreRepository extends EntMngClass implements GenreInterface {

    @Override
    public void create(Genres g) throws CrudFormException {
        try {
            em.getTransaction().begin();
            em.persist(g);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new CrudFormException("Msg \n" + e.getMessage());
        }
    }

    @Override
    public void edit(Genres g) throws CrudFormException {
        try {
            em.getTransaction().begin();
            em.merge(g);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new CrudFormException("Msg \n" + e.getMessage());
        }
    }

    @Override
    public void delete(Genres g) throws CrudFormException {
        try {
            em.getTransaction().begin();
            em.remove(g);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new CrudFormException("Msg \n" + e.getMessage());
        }
    }

    @Override
    public List<Genres> findAll() throws CrudFormException {
        try {
            return em.createNamedQuery("Genres.findAll").getResultList();
        } catch (Exception e) {
            throw new CrudFormException("Msg \n" + e.getMessage());
        }
    }

    @Override
    public Genres findById(Integer ID) throws CrudFormException {
        try {
            Query query = em.createQuery("SELECT g FROM Genres WHERE g.id = :id");
            query.setParameter("id", ID);
            return (Genres) query.getSingleResult();
        } catch (Exception e) {
           throw new CrudFormException("Msg \n" + e.getMessage());
        }
    }
   
    public List<Object> fetchAllGenres() throws CrudFormException {
        try {
            Query query = em.createQuery("SELECT g.id, g.name FROM Genres g");
           
            return query.getResultList();
            
        } catch (Exception e) {
           throw new CrudFormException("Msg \n" + e.getMessage());
        }
        
    }
}
