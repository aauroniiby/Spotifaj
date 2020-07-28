/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Model;

import javax.swing.table.AbstractTableModel;
import BLL.Roles;
import java.util.List;

public class RolesTableModel extends AbstractTableModel{

    List <Roles> list;
    String[] cols = {"id", "name"};
    
    public RolesTableModel() {}
     
    public RolesTableModel(List<Roles> list) {
        this.list = list;
    }
    
    public void addList(List<Roles> list) {
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
    
    public Roles getRoles(int index) {
        return list.get(index);
    }

    @Override
    public int getColumnCount() {
        return cols.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Roles r = list.get(rowIndex);
        
        switch(columnIndex) {
            case 0:
                return r.getId();
            case 1: 
                return r.getName();
            default:
                return null;
        }
    }
    
}
