/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.util.List;
import BLL.Tracks;

public interface TracksInterface {
    void create(Tracks t) throws CrudFormException;
    void edit(Tracks t) throws CrudFormException;
    void delete(Tracks t) throws CrudFormException;
    
    List <Tracks> findAll() throws CrudFormException;
    Tracks findById(Integer ID) throws CrudFormException;
}
