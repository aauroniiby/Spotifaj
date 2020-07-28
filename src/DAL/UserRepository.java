/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;
import BLL.Users;
import java.util.List;
import javax.persistence.Query;

public class UserRepository extends EntMngClass implements UserInterface {
    

    @Override
    public void create(Users u) throws CrudFormException {
        try {
            em.getTransaction().begin();
            em.persist(u);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new CrudFormException("Msg \n" + e.getMessage());
        }
    }

    @Override
    public void edit(Users u) throws CrudFormException {
        try {
            em.getTransaction().begin();
            em.merge(u);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new CrudFormException("Msg \n" + e.getMessage());
        }
    }

    @Override
    public void delete(Users u) throws CrudFormException {
        try {
            em.getTransaction().begin();
            em.remove(u);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new CrudFormException("Msg \n" + e.getMessage());
        }
    }

    @Override
    public List<Users> findAll() throws CrudFormException {
         try {
            return em.createNamedQuery("Users.findAll").getResultList();
        } catch (Exception e) {
            throw new CrudFormException("Msg \n" + e.getMessage());
        }
    }

    @Override
    public Users findById(Integer ID) throws CrudFormException {
        try {
            Query query = em.createQuery("SELECT u FROM Users u WHERE u.id = :id");
            query.setParameter("id", ID);
            return (Users) query.getSingleResult();
            
        } catch (Exception e) {
            throw new CrudFormException("Msg \n" + e.getMessage());
        }
    }
    
    public void insertNewUser(String username, String password, Boolean role, String email) throws CrudFormException {
        try {
            Query query = em.createNativeQuery("INSERT INTO Users (username, password, role, email) VALUES (?, ?, ?, ?)");
            em.getTransaction().begin();
            query.setParameter(1, username);
            query.setParameter(2, password);
            
            if(role){
                Integer i = 1;
                query.setParameter(3, i);
            }else{
                Integer i = 2;
                query.setParameter(3, i);
            }
            query.setParameter(4, email);
            query.executeUpdate();
            em.getTransaction().commit();
          
        } catch (Exception e) {
           throw new CrudFormException("Msg \n" + e.getMessage());
        }
    }
    
    public void deleteUser(Integer id) throws CrudFormException {
        try {
            Query query = em.createNativeQuery("DELETE FROM Users u WHERE u.id = ?");
            em.getTransaction().begin();
            query.setParameter(1, id);
            query.executeUpdate();
            em.getTransaction().commit();
          
        } catch (Exception e) {
           throw new CrudFormException("Msg \n" + e.getMessage());
        }
    }
    
    public Users loginByUsernameAndPassword(String u, String p) throws CrudFormException {
        try {
            Query query = em.createQuery("SELECT u FROM Users u WHERE u.username = :us AND u.password = :psw");
            
            query.setParameter("us", u);
            query.setParameter("psw", p);
            return (Users) query.getSingleResult();
            
        } catch (Exception e) {
            throw new CrudFormException("Msg \n" + e.getMessage());
        }
    }
    
    public void updateUser(Integer userId, String firstName, String lastName) throws CrudFormException {
        try {
            Query query = em.createNativeQuery("UPDATE Users u SET u.firstName = ?, u.lastName = ? WHERE u.id = ?");
            em.getTransaction().begin();
            query.setParameter(1, firstName);
            query.setParameter(2, lastName);
            query.setParameter(3, userId);
            query.executeUpdate();
            em.getTransaction().commit();
            
        } catch (Exception e) {
            throw new CrudFormException("Msg \n" + e.getMessage());
        }
    }
}
