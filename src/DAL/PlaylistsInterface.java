/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;
import java.util.List;
import BLL.Playlists;

public interface PlaylistsInterface {
    void create(Playlists t) throws CrudFormException;
    void edit(Playlists t) throws CrudFormException;
    void delete(Playlists t) throws CrudFormException;
    
    List <Playlists> findAll() throws CrudFormException;
    Playlists findById(Integer ID) throws CrudFormException;
}
