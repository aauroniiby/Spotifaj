/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.View;

import BLL.Tracks;
import DAL.CrudFormException;
import DAL.GenreRepository;
import DAL.PlaylistTracksRepository;
import DAL.PlaylistsRepository;
import DAL.TracksRepository;
import GUI.Model.TracksTableModel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ItemEvent;
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

public class TracksForm extends javax.swing.JInternalFrame {
    
     /**
     * Creates new form TracksForm
     */
    
    TracksRepository tr = new TracksRepository();
    TracksTableModel ttm = new TracksTableModel();
    GenreRepository gr = new GenreRepository();
    PlaylistsRepository pr = new PlaylistsRepository();
    PlaylistTracksRepository ptr = new PlaylistTracksRepository();
    
    public Integer userId;
    public Integer selectedRating = 0;
    public Integer selectedGenre = 0;
    public Integer selectedPlaylist = 0;
    public Integer selectedTrackId = 0;
    public Integer selectedTrackIndex = 0;
    public Boolean filterZeroRating, filterOneRating, filterTwoRating, filterThreeRating, filterFourRating, filterFiveRating;
    
    public TracksForm() {
        initComponents();
//        loadTable();
        loadGenreList();
        loadPlaylistList();
        removeBordersAndMenu();
        setTheme();
        tableSelectedIndexChange();
    }
    
    public TracksForm(Integer userId) {
        
        if(userId != 0) {
            this.userId = userId;
        }
        System.out.println(3);
        initComponents();
        loadTable(userId);
        loadGenreList();
        loadPlaylistList();
        removeBordersAndMenu();
        setTheme();
        tableSelectedIndexChange();
    }
    
    private void setTheme() {
        addTrackPanel.setVisible(false);
        addTrackLabel.setBackground(Color.decode("#303030"));
        addTrackLabel.setHorizontalAlignment(SwingConstants.CENTER);
        addTrackLabel.setVerticalAlignment(SwingConstants.CENTER);
        addTrackLabel.setOpaque(true);
        
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
        
        filterLabel.setBackground(Color.decode("#303030"));
        filterLabel.setHorizontalAlignment(SwingConstants.CENTER);
        filterLabel.setVerticalAlignment(SwingConstants.CENTER);
        filterLabel.setOpaque(true);
        
        filterClearLabel.setBackground(Color.decode("#303030"));
        filterClearLabel.setHorizontalAlignment(SwingConstants.CENTER);
        filterClearLabel.setVerticalAlignment(SwingConstants.CENTER);
        filterClearLabel.setOpaque(true);
               
        zeroStarRadioButton.setSelected(true);
    }
    
    private void removeBordersAndMenu() {
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI)this.getUI();
        bi.setNorthPane(null);
    }
    
    private void loadGenreList() {
        genreCmbBox.removeAllItems();
        genreCmbBox.addItem("Select...");
        filterGenreCmbBox.removeAllItems();
        filterGenreCmbBox.addItem("Select...");
        
        try {
            List<Object> genreList = gr.fetchAllGenres();
            for (int i=0; i<genreList.size(); i++){
               Object[] row = (Object[]) genreList.get(i);
               
               String rowId = row[0].toString();
               String rowName = row[1].toString();
               
               genreCmbBox.addItem(rowId + " " + rowName);
               filterGenreCmbBox.addItem(rowId + " " + rowName);
            }              
        } catch (CrudFormException ex) {
            Logger.getLogger(TracksForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadPlaylistList() {
        playlistCmbBox.removeAllItems();
        playlistCmbBox.addItem("Select...");
        
        try {
            List<Object> playlistList = pr.fetchAllPlaylists();
            for (int i=0; i<playlistList.size(); i++){
               Object[] row = (Object[]) playlistList.get(i);
               
               String rowId = row[0].toString();
               String rowName = row[1].toString();
               
               playlistCmbBox.addItem(rowId + " " + rowName);
            }              
        } catch (CrudFormException ex) {
            Logger.getLogger(TracksForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadTable(Integer userId) {
        try {
            if(userId != 0) {
                
                List<Object> res = tr.fetchOwnedTracks(userId);
                List<Tracks> list= new ArrayList<>();
                
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
                
                ttm.addList(list);
                table.setModel(ttm);
                ttm.fireTableDataChanged();
            }
        } catch (CrudFormException ex) {
            Logger.getLogger(TracksForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void clear() {
        table.clearSelection();
        
        titleTxtField.setText("");
        artistTxtField.setText("");
        albumTxtField.setText("");
        yearTxtField.setText("");
        this.selectedRating = 0;
        this.selectedGenre = 0;
        genreCmbBox.setSelectedIndex(0);
        setRadioButtonsState(0);
        
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
        addTrackLabel = new javax.swing.JLabel();
        addTrackPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        saveLabel = new javax.swing.JLabel();
        closeLabel = new javax.swing.JLabel();
        clearLabel = new javax.swing.JLabel();
        artistLabel = new javax.swing.JLabel();
        titleLabel = new javax.swing.JLabel();
        albumLabel = new javax.swing.JLabel();
        yearLabel = new javax.swing.JLabel();
        ratingLabel = new javax.swing.JLabel();
        genreLabel = new javax.swing.JLabel();
        genreCmbBox = new javax.swing.JComboBox<>();
        titleTxtField = new javax.swing.JTextField();
        artistTxtField = new javax.swing.JTextField();
        albumTxtField = new javax.swing.JTextField();
        yearTxtField = new javax.swing.JTextField();
        zeroStarRadioButton = new javax.swing.JRadioButton();
        oneStarRadioButton = new javax.swing.JRadioButton();
        twoStarRadioButton = new javax.swing.JRadioButton();
        threeStarRadioButton = new javax.swing.JRadioButton();
        fourStarRadioButton = new javax.swing.JRadioButton();
        fiveStarRadioButton = new javax.swing.JRadioButton();
        playlistCmbBox = new javax.swing.JComboBox<>();
        playlistLabel = new javax.swing.JLabel();
        deleteLabel = new javax.swing.JLabel();
        editLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        filterTitleTxtField = new javax.swing.JTextField();
        filterArtistTxtField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        filterGenreCmbBox = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        zeroRatingCheckBox = new javax.swing.JCheckBox();
        oneRatingCheckBox = new javax.swing.JCheckBox();
        twoRatingCheckBox = new javax.swing.JCheckBox();
        threeRatingCheckBox = new javax.swing.JCheckBox();
        fourRatingCheckBox = new javax.swing.JCheckBox();
        fiveRatingCheckBox = new javax.swing.JCheckBox();
        filterLabel = new javax.swing.JLabel();
        filterClearLabel = new javax.swing.JLabel();

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
        table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setInheritsPopupMenu(true);
        jScrollPane1.setViewportView(table);

        addTrackLabel.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        addTrackLabel.setForeground(new java.awt.Color(255, 255, 255));
        addTrackLabel.setText("+");
        addTrackLabel.setFocusTraversalPolicyProvider(true);
        addTrackLabel.setMaximumSize(new java.awt.Dimension(25, 25));
        addTrackLabel.setMinimumSize(new java.awt.Dimension(25, 25));
        addTrackLabel.setPreferredSize(new java.awt.Dimension(25, 25));
        addTrackLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addTrackLabelMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addTrackLabelMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addTrackLabelMouseEntered(evt);
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saveLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clearLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(closeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 7, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(saveLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(closeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(clearLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        artistLabel.setText("Artist:");

        titleLabel.setText("Title:");

        albumLabel.setText("Album:");

        yearLabel.setText("Year:");

        ratingLabel.setText("Rating:");

        genreLabel.setText("Genre:");

        genreCmbBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        genreCmbBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                genreCmbBoxItemStateChanged(evt);
            }
        });

        zeroStarRadioButton.setText("no rating");
        zeroStarRadioButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                zeroStarRadioButtonMouseClicked(evt);
            }
        });

        oneStarRadioButton.setText("*");
        oneStarRadioButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                oneStarRadioButtonMouseClicked(evt);
            }
        });

        twoStarRadioButton.setText("**");
        twoStarRadioButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                twoStarRadioButtonMouseClicked(evt);
            }
        });

        threeStarRadioButton.setText("***");
        threeStarRadioButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                threeStarRadioButtonMouseClicked(evt);
            }
        });

        fourStarRadioButton.setText("****");
        fourStarRadioButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fourStarRadioButtonMouseClicked(evt);
            }
        });

        fiveStarRadioButton.setText("*****");
        fiveStarRadioButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fiveStarRadioButtonMouseClicked(evt);
            }
        });

        playlistCmbBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        playlistCmbBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                playlistCmbBoxItemStateChanged(evt);
            }
        });

        playlistLabel.setText("Playlist:");

        javax.swing.GroupLayout addTrackPanelLayout = new javax.swing.GroupLayout(addTrackPanel);
        addTrackPanel.setLayout(addTrackPanelLayout);
        addTrackPanelLayout.setHorizontalGroup(
            addTrackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addTrackPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(addTrackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(addTrackPanelLayout.createSequentialGroup()
                        .addComponent(titleLabel)
                        .addGap(6, 6, 6)
                        .addComponent(titleTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(artistLabel)
                        .addGap(12, 12, 12)
                        .addComponent(artistTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(albumLabel)
                        .addGap(6, 6, 6)
                        .addComponent(albumTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(yearLabel)
                        .addGap(6, 6, 6)
                        .addComponent(yearTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(addTrackPanelLayout.createSequentialGroup()
                        .addComponent(ratingLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(addTrackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addTrackPanelLayout.createSequentialGroup()
                                .addGroup(addTrackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(addTrackPanelLayout.createSequentialGroup()
                                        .addComponent(zeroStarRadioButton)
                                        .addGap(75, 75, 75)
                                        .addComponent(genreLabel)
                                        .addGap(6, 6, 6)
                                        .addComponent(genreCmbBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(oneStarRadioButton)
                                    .addComponent(twoStarRadioButton)
                                    .addComponent(threeStarRadioButton))
                                .addGap(21, 21, 21)
                                .addComponent(playlistLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(playlistCmbBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(addTrackPanelLayout.createSequentialGroup()
                                .addGroup(addTrackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fourStarRadioButton)
                                    .addComponent(fiveStarRadioButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        addTrackPanelLayout.setVerticalGroup(
            addTrackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addTrackPanelLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(addTrackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titleTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(artistTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(albumTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yearTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(addTrackPanelLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(addTrackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(titleLabel)
                            .addComponent(artistLabel)
                            .addComponent(albumLabel)
                            .addComponent(yearLabel))))
                .addGap(18, 18, 18)
                .addGroup(addTrackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(addTrackPanelLayout.createSequentialGroup()
                        .addGroup(addTrackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addTrackPanelLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(genreLabel))
                            .addComponent(genreCmbBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(addTrackPanelLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(addTrackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(playlistCmbBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(playlistLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(27, 27, 27))
                    .addGroup(addTrackPanelLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(addTrackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ratingLabel)
                            .addComponent(zeroStarRadioButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(oneStarRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(twoStarRadioButton)
                .addGap(6, 6, 6)
                .addComponent(threeStarRadioButton)
                .addGap(6, 6, 6)
                .addGroup(addTrackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addTrackPanelLayout.createSequentialGroup()
                        .addComponent(fourStarRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fiveStarRadioButton))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 133, Short.MAX_VALUE))
        );

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
            public void mouseExited(java.awt.event.MouseEvent evt) {
                deleteLabelMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                deleteLabelMouseEntered(evt);
            }
        });

        editLabel.setForeground(new java.awt.Color(255, 255, 255));
        editLabel.setText("Edit");
        editLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editLabelMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                editLabelMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                editLabelMouseEntered(evt);
            }
        });

        jLabel1.setText("Title:");

        jLabel2.setText("Artist:");

        jLabel3.setText("Genre:");

        filterGenreCmbBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setText("Rating:");

        zeroRatingCheckBox.setText("no rating");

        oneRatingCheckBox.setText("*");

        twoRatingCheckBox.setText("**");

        threeRatingCheckBox.setText("***");

        fourRatingCheckBox.setText("****");

        fiveRatingCheckBox.setText("*****");

        filterLabel.setForeground(new java.awt.Color(255, 255, 255));
        filterLabel.setText("Filter");
        filterLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                filterLabelMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                filterLabelMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                filterLabelMouseEntered(evt);
            }
        });

        filterClearLabel.setForeground(new java.awt.Color(255, 255, 255));
        filterClearLabel.setText("Clear");
        filterClearLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                filterClearLabelMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                filterClearLabelMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                filterClearLabelMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(addTrackLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deleteLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(filterTitleTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(filterArtistTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(filterGenreCmbBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(zeroRatingCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(oneRatingCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(twoRatingCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(threeRatingCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fourRatingCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fiveRatingCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(filterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filterClearLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addTrackPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(filterTitleTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(filterArtistTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(filterGenreCmbBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(zeroRatingCheckBox)
                    .addComponent(oneRatingCheckBox)
                    .addComponent(twoRatingCheckBox)
                    .addComponent(threeRatingCheckBox)
                    .addComponent(fourRatingCheckBox)
                    .addComponent(fiveRatingCheckBox)
                    .addComponent(filterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(filterClearLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addTrackLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addTrackPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addTrackLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addTrackLabelMouseClicked
        addTrackPanel.setVisible(true);
    }//GEN-LAST:event_addTrackLabelMouseClicked

    private void saveLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveLabelMouseEntered
        saveLabel.setBackground(Color.decode("#282828"));
        saveLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_saveLabelMouseEntered

    private void saveLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveLabelMouseExited
        saveLabel.setBackground(Color.decode("#303030"));
    }//GEN-LAST:event_saveLabelMouseExited

    private void clearLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearLabelMouseEntered
        clearLabel.setBackground(Color.decode("#282828"));
        clearLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_clearLabelMouseEntered

    private void clearLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearLabelMouseExited
        clearLabel.setBackground(Color.decode("#303030"));
    }//GEN-LAST:event_clearLabelMouseExited

    private void closeLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeLabelMouseEntered
        closeLabel.setBackground(Color.decode("#282828"));
        closeLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_closeLabelMouseEntered

    private void closeLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeLabelMouseExited
        closeLabel.setBackground(Color.decode("#303030"));
    }//GEN-LAST:event_closeLabelMouseExited

    private void addTrackLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addTrackLabelMouseEntered
        // TODO add your handling code here:
        addTrackLabel.setBackground(Color.decode("#282828"));
        addTrackLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_addTrackLabelMouseEntered

    private void addTrackLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addTrackLabelMouseExited
        addTrackLabel.setBackground(Color.decode("#303030"));
    }//GEN-LAST:event_addTrackLabelMouseExited

    private void zeroStarRadioButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_zeroStarRadioButtonMouseClicked
        setRadioButtonsState(0);
    }//GEN-LAST:event_zeroStarRadioButtonMouseClicked

    private void oneStarRadioButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_oneStarRadioButtonMouseClicked
        setRadioButtonsState(1);
    }//GEN-LAST:event_oneStarRadioButtonMouseClicked

    private void twoStarRadioButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_twoStarRadioButtonMouseClicked
        setRadioButtonsState(2);
    }//GEN-LAST:event_twoStarRadioButtonMouseClicked

    private void threeStarRadioButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_threeStarRadioButtonMouseClicked
        setRadioButtonsState(3);
    }//GEN-LAST:event_threeStarRadioButtonMouseClicked

    private void fourStarRadioButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fourStarRadioButtonMouseClicked
        setRadioButtonsState(4);
    }//GEN-LAST:event_fourStarRadioButtonMouseClicked

    private void fiveStarRadioButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fiveStarRadioButtonMouseClicked
        setRadioButtonsState(5);
    }//GEN-LAST:event_fiveStarRadioButtonMouseClicked

    private void genreCmbBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_genreCmbBoxItemStateChanged
       if (evt.getStateChange() == ItemEvent.SELECTED) {
          Object item = evt.getItem();
          if(item != "Select...") {
              this.selectedGenre = Integer. parseInt(item.toString().split(" ", 0)[0]);
          } else {
              this.selectedGenre = 0;
          }
       }
    }//GEN-LAST:event_genreCmbBoxItemStateChanged

    private void clearLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearLabelMouseClicked
        clear();
    }//GEN-LAST:event_clearLabelMouseClicked

    private void closeLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeLabelMouseClicked
        addTrackPanel.setVisible(false);
        deleteLabel.setVisible(false);
        editLabel.setVisible(false);
        clear();
    }//GEN-LAST:event_closeLabelMouseClicked

    private void saveLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveLabelMouseClicked
        try {
            int row = table.getSelectedRow();
            if(row == -1) {
                tr.insertNewTrack(titleTxtField.getText(), artistTxtField.getText(), albumTxtField.getText(), yearTxtField.getText(), this.selectedRating.toString(), this.selectedGenre.toString());
                
                if(this.selectedPlaylist != 0) {
                    Integer trackId = tr.fetchLastInsertId();
                    System.out.println(selectedPlaylist);
                    if(trackId > 0) {
                        ptr.insertTrackToPlaylist(this.selectedPlaylist, trackId);
                    }
                }
                
                clear();
                loadTable(this.userId);
            } else {
                Tracks t = ttm.getTracks(row);
                t.setTitle(titleTxtField.getText());
                t.setArtist(artistTxtField.getText());
                t.setAlbum(albumTxtField.getText());
                t.setYear(yearTxtField.getText());
                t.setRating(this.selectedRating);
                t.setGenre(genreCmbBox.getSelectedIndex());
                tr.edit(t);
                
                 if(this.selectedPlaylist != 0) {
                    Integer trackId = t.getId();
                    if(trackId > 0) {
                        ptr.insertTrackToPlaylist(this.selectedPlaylist, trackId);
                    }
                }
                clear();
                loadTable(this.userId);
            }
            
        } catch (CrudFormException ex) {
            JOptionPane.showMessageDialog(this, "Record already exists!");
        }
    }//GEN-LAST:event_saveLabelMouseClicked

    private void deleteLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteLabelMouseClicked
        try {
            if(selectedTrackId != 0) {
                tr.deleteTrack(this.selectedTrackId);
                loadTable(this.userId);
                this.selectedTrackId = 0;
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

    private void editLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editLabelMouseEntered
        editLabel.setBackground(Color.decode("#282828"));
        editLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_editLabelMouseEntered

    private void editLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editLabelMouseExited
        editLabel.setBackground(Color.decode("#303030"));
    }//GEN-LAST:event_editLabelMouseExited

    private void editLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editLabelMouseClicked
        addTrackPanel.setVisible(true);
        
        if(selectedTrackId != 0) {
            Tracks t = ttm.getTracks(this.selectedTrackIndex);
            titleTxtField.setText(t.getTitle());
            artistTxtField.setText(t.getArtist());
            albumTxtField.setText(t.getAlbum());
            yearTxtField.setText(t.getYear());
            setRadioButtonsState(t.getRating());
            genreCmbBox.setSelectedIndex(t.getGenre());
        }
    }//GEN-LAST:event_editLabelMouseClicked

    private void playlistCmbBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_playlistCmbBoxItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
          Object item = evt.getItem();
          if(item != "Select...") {
              this.selectedPlaylist = Integer.parseInt(item.toString().split(" ", 0)[0]);
          } else {
              this.selectedPlaylist = 0;
          }
       }
    }//GEN-LAST:event_playlistCmbBoxItemStateChanged

    private void filterLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_filterLabelMouseClicked
        
        try {
            if(userId != 0) {
                
                List<Object> res = tr.filterOwnTracks(
                    filterTitleTxtField.getText(),
                    filterArtistTxtField.getText(),
                    Integer.parseInt(filterGenreCmbBox.getSelectedItem().toString().split(" ", 0)[0]),
                    zeroRatingCheckBox.isSelected(),
                    oneRatingCheckBox.isSelected(),
                    twoRatingCheckBox.isSelected(),
                    threeRatingCheckBox.isSelected(),
                    fourRatingCheckBox.isSelected(),
                    fiveRatingCheckBox.isSelected(),
                    userId
                );
                List<Tracks> list= new ArrayList<>();
                
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
                
                ttm.addList(list);
                table.setModel(ttm);
                ttm.fireTableDataChanged();
            }
        } catch (CrudFormException ex) {
            Logger.getLogger(TracksForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_filterLabelMouseClicked

    private void filterLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_filterLabelMouseEntered
        filterLabel.setBackground(Color.decode("#282828"));
        filterLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_filterLabelMouseEntered

    private void filterLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_filterLabelMouseExited
        filterLabel.setBackground(Color.decode("#303030"));
    }//GEN-LAST:event_filterLabelMouseExited

    private void filterClearLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_filterClearLabelMouseClicked
        loadTable(userId);
    }//GEN-LAST:event_filterClearLabelMouseClicked

    private void filterClearLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_filterClearLabelMouseExited
        filterClearLabel.setBackground(Color.decode("#303030"));
    }//GEN-LAST:event_filterClearLabelMouseExited

    private void filterClearLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_filterClearLabelMouseEntered
        filterClearLabel.setBackground(Color.decode("#282828"));
        filterClearLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        
        filterTitleTxtField.setText("");
        filterArtistTxtField.setText("");
        filterGenreCmbBox.setSelectedIndex(0);
        filterGenreCmbBox.setSelectedIndex(0);
        zeroRatingCheckBox.setSelected(false);
        oneRatingCheckBox.setSelected(false);
        twoRatingCheckBox.setSelected(false);
        threeRatingCheckBox.setSelected(false);
        fourRatingCheckBox.setSelected(false);
        fiveRatingCheckBox.setSelected(false);
    }//GEN-LAST:event_filterClearLabelMouseEntered

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
                if(selectedIndex > -1) {
                    Tracks t = ttm.getTracks(selectedIndex);
                    selectedTrackId = t.getId();
                    selectedTrackIndex = selectedIndex;
                } 
            }  
        });
    }
    
    public void setRadioButtonsState(Integer selectedRating) {
        
        switch(selectedRating) {
            case 0:
                zeroStarRadioButton.setSelected(true);
                oneStarRadioButton.setSelected(false);
                twoStarRadioButton.setSelected(false);
                threeStarRadioButton.setSelected(false);
                fourStarRadioButton.setSelected(false);
                fiveStarRadioButton.setSelected(false);
                break;
            case 1:
                oneStarRadioButton.setSelected(true);
                zeroStarRadioButton.setSelected(false);
                twoStarRadioButton.setSelected(false);
                threeStarRadioButton.setSelected(false);
                fourStarRadioButton.setSelected(false);
                fiveStarRadioButton.setSelected(false);
                break;
            case 2:
                twoStarRadioButton.setSelected(true);
                zeroStarRadioButton.setSelected(false);
                oneStarRadioButton.setSelected(false);
                threeStarRadioButton.setSelected(false);
                fourStarRadioButton.setSelected(false);
                fiveStarRadioButton.setSelected(false);
                break;
            case 3:
                threeStarRadioButton.setSelected(true);
                zeroStarRadioButton.setSelected(false);
                oneStarRadioButton.setSelected(false);
                twoStarRadioButton.setSelected(false);
                fourStarRadioButton.setSelected(false);
                fiveStarRadioButton.setSelected(false);
                break;
            case 4:
                fourStarRadioButton.setSelected(true);
                zeroStarRadioButton.setSelected(false);
                oneStarRadioButton.setSelected(false);
                twoStarRadioButton.setSelected(false);
                threeStarRadioButton.setSelected(false);
                fiveStarRadioButton.setSelected(false);
                break;
            case 5:
                fiveStarRadioButton.setSelected(true);
                zeroStarRadioButton.setSelected(false);
                oneStarRadioButton.setSelected(false);
                twoStarRadioButton.setSelected(false);
                threeStarRadioButton.setSelected(false);
                fourStarRadioButton.setSelected(false);
                break;
            default:
            // code block
                
        }
        
        this.selectedRating = selectedRating;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addTrackLabel;
    private javax.swing.JPanel addTrackPanel;
    private javax.swing.JLabel albumLabel;
    private javax.swing.JTextField albumTxtField;
    private javax.swing.JLabel artistLabel;
    private javax.swing.JTextField artistTxtField;
    private javax.swing.JLabel clearLabel;
    private javax.swing.JLabel closeLabel;
    private javax.swing.JLabel deleteLabel;
    private javax.swing.JLabel editLabel;
    private javax.swing.JTextField filterArtistTxtField;
    private javax.swing.JLabel filterClearLabel;
    private javax.swing.JComboBox<String> filterGenreCmbBox;
    private javax.swing.JLabel filterLabel;
    private javax.swing.JTextField filterTitleTxtField;
    private javax.swing.JCheckBox fiveRatingCheckBox;
    private javax.swing.JRadioButton fiveStarRadioButton;
    private javax.swing.JCheckBox fourRatingCheckBox;
    private javax.swing.JRadioButton fourStarRadioButton;
    private javax.swing.JComboBox<String> genreCmbBox;
    private javax.swing.JLabel genreLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox oneRatingCheckBox;
    private javax.swing.JRadioButton oneStarRadioButton;
    private javax.swing.JComboBox<String> playlistCmbBox;
    private javax.swing.JLabel playlistLabel;
    private javax.swing.JLabel ratingLabel;
    private javax.swing.JLabel saveLabel;
    private javax.swing.JTable table;
    private javax.swing.JCheckBox threeRatingCheckBox;
    private javax.swing.JRadioButton threeStarRadioButton;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JTextField titleTxtField;
    private javax.swing.JCheckBox twoRatingCheckBox;
    private javax.swing.JRadioButton twoStarRadioButton;
    private javax.swing.JLabel yearLabel;
    private javax.swing.JTextField yearTxtField;
    private javax.swing.JCheckBox zeroRatingCheckBox;
    private javax.swing.JRadioButton zeroStarRadioButton;
    // End of variables declaration//GEN-END:variables
}
