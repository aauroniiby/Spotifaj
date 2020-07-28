/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BLL.Users;
import java.util.List;

public interface UserInterface {
    void create(Users u) throws CrudFormException;
    void edit(Users u) throws CrudFormException;
    void delete(Users u) throws CrudFormException;
    
    List <Users> findAll() throws CrudFormException;
    Users findById(Integer ID) throws CrudFormException;
}
