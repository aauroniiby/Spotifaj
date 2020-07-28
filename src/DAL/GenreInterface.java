/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BLL.Genres;
import java.util.List;

public interface GenreInterface {
    void create(Genres g) throws CrudFormException;
    void edit(Genres g) throws CrudFormException;
    void delete(Genres g) throws CrudFormException;
    
    List <Genres> findAll() throws CrudFormException;
    Genres findById(Integer ID) throws CrudFormException;
}
