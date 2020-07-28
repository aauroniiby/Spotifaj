/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BLL.Roles;
import java.util.List;

public interface RolesInterface {
    void create(Roles r) throws CrudFormException;
    void edit(Roles r) throws CrudFormException;
    void delete(Roles r) throws CrudFormException;
    
    List <Roles> findAll() throws CrudFormException;
    Roles findById(Integer ID) throws CrudFormException;
}
