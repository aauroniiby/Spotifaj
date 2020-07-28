/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Model;

import BLL.Genres;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class GenreTableModel extends AbstractTableModel {
    List <Genres> list;
    String[] cols = {"Id", "Name"};
    
    public GenreTableModel() {}
     
    public GenreTableModel(List<Genres> list) {
        this.list = list;
    }
    
    public void addList(List<Genres> list) {
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
    
     public Genres getGenres(int index) {
        return list.get(index);
    }


    @Override
    public int getColumnCount() {
        return cols.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Genres g = list.get(rowIndex);
        
        switch(columnIndex) {
            case 0:
                return g.getId();
            case 1: 
                return g.getName();
            default:
                return null;
        }
    }
    
}
