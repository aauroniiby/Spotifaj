/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.View;

import BLL.Playlists;
import BLL.Tracks;
import DAL.CrudFormException;
import DAL.PlaylistTracksRepository;
import DAL.PlaylistsRepository;
import DAL.TracksRepository;
import GUI.Model.PlaylistsTableModel;
import GUI.Model.TracksTableModel;
import java.awt.Color;
import java.awt.Cursor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;

public class PlaylistForm extends javax.swing.JInternalFrame {

    /**
     * Creates new form PlaylistForm
     */
    
    PlaylistsRepository pr = new PlaylistsRepository();
    PlaylistsTableModel ptm = new PlaylistsTableModel();
    TracksTableModel ttm = new TracksTableModel();
    TracksRepository tr = new TracksRepository();
    
    public Integer selectedPlaylistId = 0;
    public Integer selectedPlaylistIndex = 0;
    public Integer selectedTrackId = 0;
    public Integer selectedTrackIndex = 0;
    
    
    public PlaylistForm() {
        initComponents();
        loadTable();
        removeBordersAndMenu();
        setTheme();
        tableSelectedIndexChange();
        tracksTableSelectedIndexChange();
    }
    
    private void setTheme() {
        addPlaylistPanel.setVisible(false);
        tracksOfPlaylist.setVisible(false);
        
        addPlaylistLabel.setBackground(Color.decode("#303030"));
        addPlaylistLabel.setHorizontalAlignment(SwingConstants.CENTER);
        addPlaylistLabel.setVerticalAlignment(SwingConstants.CENTER);
        addPlaylistLabel.setOpaque(true);
        
        saveLabel.setBackground(Color.decode("#303030"));
        saveLabel.setHorizontalAlignment(SwingConstants.CENTER);
        saveLabel.setVerticalAlignment(SwingConstants.CENTER);
        saveLabel.setOpaque(true);
        
        clearLabel.setBackground(Color.decode("#303030"));
        clearLabel.setHorizontalAlignment(SwingConstants.CENTER);
        clearLabel.setVerticalAlignment(SwingConstants.CENTER);
        clearLabel.setOpaque(true);
        
        closeLabel.setBackground(Color.decode("#303030"));
        closeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        closeLabel.setVerticalAlignment(SwingConstants.CENTER);
        closeLabel.setOpaque(true);
        
        deleteLabel.setBackground(Color.decode("#303030"));
        deleteLabel.setHorizontalAlignment(SwingConstants.CENTER);
        deleteLabel.setVerticalAlignment(SwingConstants.CENTER);
        deleteLabel.setOpaque(true);
        deleteLabel.setVisible(false);
        
        editLabel.setBackground(Color.decode("#303030"));
        editLabel.setHorizontalAlignment(SwingConstants.CENTER);
        editLabel.setVerticalAlignment(SwingConstants.CENTER);
        editLabel.setOpaque(true);
        editLabel.setVisible(false);
        
        deleteFromPlaylistLabel.setBackground(Color.decode("#303030"));
        deleteFromPlaylistLabel.setHorizontalAlignment(SwingConstants.CENTER);
        deleteFromPlaylistLabel.setVerticalAlignment(SwingConstants.CENTER);
        deleteFromPlaylistLabel.setOpaque(true);
        deleteFromPlaylistLabel.setVisible(false);
    }
    
    private void removeBordersAndMenu() {
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI)this.getUI();
        bi.setNorthPane(null);
    }
    
    private void loadTable() {
        try {
          List<Playlists> list = pr.findAll();
          
          ptm.addList(list);
          table.setModel(ptm);
          ptm.fireTableDataChanged();
        } catch (CrudFormException ex) {
            Logger.getLogger(TracksForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   public void loadTracksTable() {
        try {
            List<Object> res = pr.fetchPlaylistTracks(this.selectedPlaylistId);
            List<Tracks> list= new ArrayList<Tracks>();
            Iterator it = res.iterator();
            
            while(it.hasNext()){
                
                Object[] line = (Object[]) it.next();
                
                Tracks t = new Tracks();
                t.setId((Integer) line[0]);
                t.setTitle((String) line[1]);
                t.setArtist((String) line[2]);
                t.setAlbum((String) line[3]);
                t.setYear((String) line[4]);
                t.setRating((Integer) line[5]);
                t.setGenre((Integer) line[7]);

                list.add(t);
            }

          list.forEach(System.out::println);
          ttm.addList(list);
          tracksOfPlaylist.setModel(ttm);
          ttm.fireTableDataChanged();
        } catch (CrudFormException ex) {
            Logger.getLogger(TracksForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void clear() {
        table.clearSelection();
        nameTxtField.setText("");
    }
    
    private void tableSelectedIndexChange() {
        final ListSelectionModel rowSM = table.getSelectionModel();
        rowSM.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(e.getValueIsAdjusting()){
                    return;
                }
                
                ListSelectionModel rowSM = (ListSelectionModel) e.getSource();
                int selectedIndex = rowSM.getAnchorSelectionIndex();
                deleteLabel.setVisible(true);
                editLabel.setVisible(true);
                tracksOfPlaylist.setVisible(true);
                

                if(selectedIndex > -1) {
                    Playlists p = ptm.getPlaylists(selectedIndex);
                    selectedPlaylistId = p.getId();
                    selectedPlaylistIndex = selectedIndex;
                    loadTracksTable();
                } 
            }
        });
    }
    
    private void tracksTableSelectedIndexChange() {
        final ListSelectionModel rowSM = tracksOfPlaylist.getSelectionModel();
        rowSM.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(e.getValueIsAdjusting()){
                    return;
                }
                
                ListSelectionModel rowSM = (ListSelectionModel) e.getSource();
                int selectedIndex = rowSM.getAnchorSelectionIndex();
               
                if(selectedIndex > -1) {
                    Tracks t = ttm.getTracks(selectedIndex);
                    
                    selectedTrackId = t.getId();
                    selectedTrackIndex = selectedIndex;
                    deleteFromPlaylistLabel.setVisible(true);
                } 
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        addPlaylistPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        closeLabel = new javax.swing.JLabel();
        saveLabel = new javax.swing.JLabel();
        clearLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        nameTxtField = new javax.swing.JTextField();
        editLabel = new javax.swing.JLabel();
        addPlaylistLabel = new javax.swing.JLabel();
        deleteLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tracksOfPlaylist = new javax.swing.JTable();
        deleteFromPlaylistLabel = new javax.swing.JLabel();

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(table);

        closeLabel.setForeground(new java.awt.Color(255, 255, 255));
        closeLabel.setText("Close");
        closeLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeLabelMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                closeLabelMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                closeLabelMouseEntered(evt);
            }
        });

        saveLabel.setForeground(new java.awt.Color(255, 255, 255));
        saveLabel.setText("Save");
        saveLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveLabelMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                saveLabelMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                saveLabelMouseEntered(evt);
            }
        });

        clearLabel.setForeground(new java.awt.Color(255, 255, 255));
        clearLabel.setText("Clear");
        clearLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clearLabelMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                clearLabelMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                clearLabelMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saveLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clearLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(closeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(saveLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(closeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(clearLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        nameLabel.setText("Name:");

        javax.swing.GroupLayout addPlaylistPanelLayout = new javax.swing.GroupLayout(addPlaylistPanel);
        addPlaylistPanel.setLayout(addPlaylistPanelLayout);
        addPlaylistPanelLayout.setHorizontalGroup(
            addPlaylistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addPlaylistPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 262, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        addPlaylistPanelLayout.setVerticalGroup(
            addPlaylistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addPlaylistPanelLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(addPlaylistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel)
                    .addComponent(nameTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(addPlaylistPanelLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        editLabel.setForeground(new java.awt.Color(255, 255, 255));
        editLabel.setText("Edit");
        editLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                editLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                editLabelMouseExited(evt);
            }
        });

        addPlaylistLabel.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        addPlaylistLabel.setForeground(new java.awt.Color(255, 255, 255));
        addPlaylistLabel.setText("+");
        addPlaylistLabel.setFocusTraversalPolicyProvider(true);
        addPlaylistLabel.setMaximumSize(new java.awt.Dimension(25, 25));
        addPlaylistLabel.setMinimumSize(new java.awt.Dimension(25, 25));
        addPlaylistLabel.setPreferredSize(new java.awt.Dimension(25, 25));
        addPlaylistLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addPlaylistLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addPlaylistLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addPlaylistLabelMouseExited(evt);
            }
        });

        deleteLabel.setForeground(new java.awt.Color(255, 255, 255));
        deleteLabel.setText("Delete");
        deleteLabel.setFocusTraversalPolicyProvider(true);
        deleteLabel.setMaximumSize(new java.awt.Dimension(25, 25));
        deleteLabel.setMinimumSize(new java.awt.Dimension(25, 25));
        deleteLabel.setPreferredSize(new java.awt.Dimension(25, 25));
        deleteLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                deleteLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                deleteLabelMouseExited(evt);
            }
        });

        tracksOfPlaylist.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Title", "Artist", "Album", "Year", "Rating", "Genre", "null"
            }
        ));
        jScrollPane2.setViewportView(tracksOfPlaylist);

        deleteFromPlaylistLabel.setForeground(new java.awt.Color(255, 255, 255));
        deleteFromPlaylistLabel.setText("Delete From Playlist");
        deleteFromPlaylistLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteFromPlaylistLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                deleteFromPlaylistLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                deleteFromPlaylistLabelMouseExited(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(addPlaylistPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(addPlaylistLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteFromPlaylistLabel)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addPlaylistLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(editLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(deleteFromPlaylistLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0)
                .addComponent(addPlaylistPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeLabelMouseClicked
        addPlaylistPanel.setVisible(false);
        clear();
    }//GEN-LAST:event_closeLabelMouseClicked

    private void closeLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeLabelMouseExited
        closeLabel.setBackground(Color.decode("#303030"));
    }//GEN-LAST:event_closeLabelMouseExited

    private void closeLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeLabelMouseEntered
        closeLabel.setBackground(Color.decode("#282828"));
        closeLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_closeLabelMouseEntered

    private void saveLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveLabelMouseClicked
        try {
            int row = table.getSelectedRow();
            if(row == -1) {
                pr.insertNewPlaylist(nameTxtField.getText());
                clear();
                loadTable();
            } else {
                Playlists p = ptm.getPlaylists(row);
                p.setName(nameTxtField.getText());
                pr.edit(p);
                clear();
                loadTable();
            }

        } catch (CrudFormException ex) {
            JOptionPane.showMessageDialog(this, "Record already exists!");
        }
    }//GEN-LAST:event_saveLabelMouseClicked

    private void saveLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveLabelMouseExited
        saveLabel.setBackground(Color.decode("#303030"));
    }//GEN-LAST:event_saveLabelMouseExited

    private void saveLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveLabelMouseEntered
        saveLabel.setBackground(Color.decode("#282828"));
        saveLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_saveLabelMouseEntered

    private void clearLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearLabelMouseClicked
        clear();
    }//GEN-LAST:event_clearLabelMouseClicked

    private void clearLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearLabelMouseExited
        clearLabel.setBackground(Color.decode("#303030"));
    }//GEN-LAST:event_clearLabelMouseExited

    private void clearLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearLabelMouseEntered
        clearLabel.setBackground(Color.decode("#282828"));
        clearLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_clearLabelMouseEntered

    private void editLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editLabelMouseClicked
        addPlaylistPanel.setVisible(true);

        if(selectedPlaylistId != 0) {
            Playlists p = ptm.getPlaylists(this.selectedPlaylistIndex);
            nameTxtField.setText(p.getName());
        }
    }//GEN-LAST:event_editLabelMouseClicked

    private void editLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editLabelMouseExited
        editLabel.setBackground(Color.decode("#303030"));
    }//GEN-LAST:event_editLabelMouseExited

    private void editLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editLabelMouseEntered
        editLabel.setBackground(Color.decode("#282828"));
        editLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_editLabelMouseEntered

    private void addPlaylistLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addPlaylistLabelMouseEntered
        // TODO add your handling code here:
        addPlaylistLabel.setBackground(Color.decode("#282828"));
        addPlaylistLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_addPlaylistLabelMouseEntered

    private void deleteLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteLabelMouseClicked
        try {
            if(selectedPlaylistId != 0) {
                pr.deletePlaylist(this.selectedPlaylistId);
                loadTable();
                this.selectedPlaylistId = 0;
            }
        } catch (CrudFormException ex) {
            Logger.getLogger(TracksForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deleteLabelMouseClicked

    private void deleteLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteLabelMouseExited
        deleteLabel.setBackground(Color.decode("#303030"));
    }//GEN-LAST:event_deleteLabelMouseExited

    private void deleteLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteLabelMouseEntered
        deleteLabel.setBackground(Color.decode("#282828"));
        deleteLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_deleteLabelMouseEntered

    private void addPlaylistLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addPlaylistLabelMouseExited
        addPlaylistLabel.setBackground(Color.decode("#303030"));
    }//GEN-LAST:event_addPlaylistLabelMouseExited

    private void addPlaylistLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addPlaylistLabelMouseClicked
        addPlaylistPanel.setVisible(true);
    }//GEN-LAST:event_addPlaylistLabelMouseClicked

    private void deleteFromPlaylistLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteFromPlaylistLabelMouseClicked
        PlaylistTracksRepository ptr = new PlaylistTracksRepository();
        try {
            ptr.deleteTrackFromPlaylist(selectedPlaylistId, selectedTrackId);
            loadTracksTable();
            this.selectedTrackId = 0;
            this.selectedTrackIndex = 0;
        } catch (CrudFormException ex) {
            Logger.getLogger(PlaylistForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deleteFromPlaylistLabelMouseClicked

    private void deleteFromPlaylistLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteFromPlaylistLabelMouseExited
        deleteFromPlaylistLabel.setBackground(Color.decode("#303030"));
    }//GEN-LAST:event_deleteFromPlaylistLabelMouseExited

    private void deleteFromPlaylistLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteFromPlaylistLabelMouseEntered
        deleteFromPlaylistLabel.setBackground(Color.decode("#282828"));
        deleteFromPlaylistLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_deleteFromPlaylistLabelMouseEntered


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addPlaylistLabel;
    private javax.swing.JPanel addPlaylistPanel;
    private javax.swing.JLabel clearLabel;
    private javax.swing.JLabel closeLabel;
    private javax.swing.JLabel deleteFromPlaylistLabel;
    private javax.swing.JLabel deleteLabel;
    private javax.swing.JLabel editLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTxtField;
    private javax.swing.JLabel saveLabel;
    private javax.swing.JTable table;
    private javax.swing.JTable tracksOfPlaylist;
    // End of variables declaration//GEN-END:variables
}
