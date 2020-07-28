/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BLL.PlaylistTracks;
import java.util.List;

public interface PlaylistTracksInterface {
    void create(PlaylistTracks p) throws CrudFormException;
    void edit(PlaylistTracks p) throws CrudFormException;
    void delete(PlaylistTracks p) throws CrudFormException;
    
    List <PlaylistTracks> findAll() throws CrudFormException;
    PlaylistTracks findById(Integer ID) throws CrudFormException;
}

