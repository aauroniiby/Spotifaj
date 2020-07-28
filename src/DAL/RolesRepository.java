/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BLL.Roles;
import java.util.List;
import javax.persistence.Query;

public class RolesRepository extends EntMngClass implements RolesInterface {

    @Override
    public void create(Roles r) throws CrudFormException {
        try {
            em.getTransaction().begin();
            em.persist(r);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new CrudFormException("Msg \n" + e.getMessage());
        }
    }

    @Override
    public void edit(Roles r) throws CrudFormException {
        try {
            em.getTransaction().begin();
            em.merge(r);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new CrudFormException("Msg \n" + e.getMessage());
        }
    }

    @Override
    public void delete(Roles r) throws CrudFormException {
        try {
            em.getTransaction().begin();
            em.remove(r);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new CrudFormException("Msg \n" + e.getMessage());
        }
    }

    @Override
    public List<Roles> findAll() throws CrudFormException {
         try {
            return em.createNamedQuery("Roles.findAll").getResultList();
        } catch (Exception e) {
            throw new CrudFormException("Msg \n" + e.getMessage());
        }
    }

    @Override
    public Roles findById(Integer ID) throws CrudFormException {
        try {
            Query query = em.createQuery("SELECT r FROM Roles WHERE r.id = :id");
            query.setParameter("id", ID);
            return (Roles) query.getSingleResult();
        } catch (Exception e) {
           throw new CrudFormException("Msg \n" + e.getMessage());
        }
    }
    
}
