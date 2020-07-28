/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Model;
import BLL.Users;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class UsersTableModel extends AbstractTableModel{
    
    List <Users> list;
    String[] cols = {"id", "name", "role"};
    
    public UsersTableModel() {}
     
    public UsersTableModel(List<Users> list) {
        this.list = list;
    }
    
    public void addList(List<Users> list) {
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
    
    public Users getRoles(int index) {
        return list.get(index);
    }
    
    public Users getUsers(int index){
        return list.get(index);
    }

    @Override
    public int getColumnCount() {
        return cols.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Users u = list.get(rowIndex);
        
        switch(columnIndex) {
            case 0:
                return u.getId();
            case 1: 
                return u.getUsername();
            case 2: 
                return u.getRole().getName();
            default:
                return null;
        }
    }
    
}
