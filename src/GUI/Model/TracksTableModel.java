/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Model;

import java.util.List;
import BLL.Tracks;
import javax.swing.table.AbstractTableModel;

public class TracksTableModel extends AbstractTableModel {
    
    List <Tracks> list;
    String[] cols = {"ID", "Title", "Artist", "Album", "Year", "Rating", "Genre" };
    
    public TracksTableModel() {}
     
    public TracksTableModel(List<Tracks> list) {
        this.list = list;
    }
    
    public void addList(List<Tracks> list) {
        this.list = list;
    }
    
    @Override
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
    
    public Tracks getTracks(int index) {
        return list.get(index);
    }

    @Override
    public int getColumnCount() {
        return cols.length;
    }

   @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Tracks t = list.get(rowIndex);
        
        switch(columnIndex) {
            case 0:
                return t.getId();
            case 1: 
                return t.getTitle();
            case 2: 
                return t.getArtist();
            case 3: 
                return t.getAlbum();
            case 4: 
                return t.getYear();
            case 5: 
                return t.getRating();
            case 6: 
                return t.getGenre();
            default:
                return null;
        }
    }
    
}
