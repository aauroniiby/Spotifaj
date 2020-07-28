/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Model;

import java.util.List;
import BLL.Playlists;
import javax.swing.table.AbstractTableModel;

public class PlaylistsTableModel extends AbstractTableModel {

    List <Playlists> list;
    String[] cols = {"id", "Name"};
    
    public PlaylistsTableModel() {}
     
    public PlaylistsTableModel(List<Playlists> list) {
        this.list = list;
    }
    
    public void addList(List<Playlists> list) {
        this.list = list;
    }
    
    public String getColumnName(int column) {
        return cols[column];
    }
     
    @Override
    public int getRowCount() {
        return list.size();
    }
    
    public void remove(int row) {
        list.remove(row);
    }
    
     public Playlists getPlaylists(int index) {
        return list.get(index);
    }


    @Override
    public int getColumnCount() {
        return cols.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Playlists p = list.get(rowIndex);
        
        switch(columnIndex) {
            case 0:
                return p.getId();
            case 1: 
                return p.getName();
            default:
                return null;
        }
    }
}
