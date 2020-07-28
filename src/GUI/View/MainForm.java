/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.View;

import DAL.CrudFormException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class MainForm extends javax.swing.JFrame {

    /**
     * Creates new form MainForm
     */
    
    TracksForm tf;
    HomeForm hf = new HomeForm();
    PlaylistForm pf = new PlaylistForm();
    UsersForm uf = new UsersForm();
    GenreForm gf = new GenreForm();
    AllTracksForm atf = new AllTracksForm();
    ProfileForm prf;
    
    String activeForm = "home";
    Integer userId = 1;
    
    public MainForm() {
        initComponents();
        initForms();
        setSidebarBgColor();
        scaleImage();
    }
    
    public MainForm(Integer userRole, Integer userId) {
        initComponents();
        setLocationRelativeTo(null);
        
        setSidebarBgColor();
        scaleImage();
        if(userRole != 1) {
            usersLabel.hide();
            genreLabel.hide();
            allTracksLabel.hide();
        }
        
        if(userId != 0) {
            try {
                System.out.println("asdasdasd");
                initFormsWithUser(userId);
            } catch (CrudFormException ex) {
                
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.userId = userId;
        }
    }
    
    private void initFormsWithUser(Integer userId) throws CrudFormException {
        this.tf = new TracksForm(userId);
        this.prf = new ProfileForm(userId);
        mainPane.add(hf);
        mainPane.add(pf);
        mainPane.add(uf);
        mainPane.add(gf);      
        mainPane.add(tf);
        mainPane.add(prf);
        mainPane.add(atf);
        hf.show();
    }
    
    private void initForms() {
        this.prf = new ProfileForm();
        this.tf = new TracksForm();
        mainPane.add(hf);
        mainPane.add(pf);
        mainPane.add(uf);
        mainPane.add(gf);
        mainPane.add(tf);
        mainPane.add(prf);
        mainPane.add(atf);
        hf.show();
    }
    
    
    public void setActiveForm(String activeForm) {
        this.activeForm = activeForm;
        
        switch(activeForm) {
            case "home":
              homeLabel.setBackground(Color.decode("#282828"));
              tracksLabel.setBackground(Color.decode("#303030"));
              playlistsLabel.setBackground(Color.decode("#303030"));
              usersLabel.setBackground(Color.decode("#303030"));
              genreLabel.setBackground(Color.decode("#303030"));
              profileLabel.setBackground(Color.decode("#303030"));
              allTracksLabel.setBackground(Color.decode("#303030"));
              break;
            case "tracks":
              tracksLabel.setBackground(Color.decode("#282828"));
              homeLabel.setBackground(Color.decode("#303030"));
              playlistsLabel.setBackground(Color.decode("#303030"));
              usersLabel.setBackground(Color.decode("#303030"));
              genreLabel.setBackground(Color.decode("#303030"));
              profileLabel.setBackground(Color.decode("#303030"));
              allTracksLabel.setBackground(Color.decode("#303030"));
              break;
            case "playlists":
              playlistsLabel.setBackground(Color.decode("#282828"));
              tracksLabel.setBackground(Color.decode("#303030"));
              homeLabel.setBackground(Color.decode("#303030"));
              usersLabel.setBackground(Color.decode("#303030"));
              genreLabel.setBackground(Color.decode("#303030"));
              profileLabel.setBackground(Color.decode("#303030"));
              allTracksLabel.setBackground(Color.decode("#303030"));
              break;
            case "users":
              usersLabel.setBackground(Color.decode("#282828"));
              playlistsLabel.setBackground(Color.decode("#303030"));
              tracksLabel.setBackground(Color.decode("#303030"));
              homeLabel.setBackground(Color.decode("#303030"));
              genreLabel.setBackground(Color.decode("#303030"));
              profileLabel.setBackground(Color.decode("#303030"));
              allTracksLabel.setBackground(Color.decode("#303030"));
              break;
            case "genres":
              genreLabel.setBackground(Color.decode("#282828"));
              usersLabel.setBackground(Color.decode("#303030"));
              playlistsLabel.setBackground(Color.decode("#303030"));
              tracksLabel.setBackground(Color.decode("#303030"));
              homeLabel.setBackground(Color.decode("#303030"));
              profileLabel.setBackground(Color.decode("#303030"));
              allTracksLabel.setBackground(Color.decode("#303030"));
              break;
            case "profile":
              profileLabel.setBackground(Color.decode("#282828"));
              genreLabel.setBackground(Color.decode("#303030"));
              usersLabel.setBackground(Color.decode("#303030"));
              playlistsLabel.setBackground(Color.decode("#303030"));
              tracksLabel.setBackground(Color.decode("#303030"));
              homeLabel.setBackground(Color.decode("#303030"));
              allTracksLabel.setBackground(Color.decode("#303030"));
                break;
            case "all-tracks":
              allTracksLabel.setBackground(Color.decode("#282828"));
              profileLabel.setBackground(Color.decode("#303030"));
              genreLabel.setBackground(Color.decode("#303030"));
              usersLabel.setBackground(Color.decode("#303030"));
              playlistsLabel.setBackground(Color.decode("#303030"));
              tracksLabel.setBackground(Color.decode("#303030"));
              homeLabel.setBackground(Color.decode("#303030"));
                break;
            default:
              // code block
        }
    }
    
    private void setSidebarBgColor() {
        mainPane.setBackground(Color.decode("#282828"));
        sidebarPanel.setBackground(Color.decode("#303030"));
        
        homeLabel.setBackground(Color.decode("#282828"));
        homeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        homeLabel.setVerticalAlignment(SwingConstants.CENTER);
        homeLabel.setOpaque(true);
        
        tracksLabel.setBackground(Color.decode("#303030"));
        tracksLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tracksLabel.setVerticalAlignment(SwingConstants.CENTER);
        tracksLabel.setOpaque(true);
        
        playlistsLabel.setBackground(Color.decode("#303030"));
        playlistsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        playlistsLabel.setVerticalAlignment(SwingConstants.CENTER);
        playlistsLabel.setOpaque(true);
        
        usersLabel.setBackground(Color.decode("#303030"));
        usersLabel.setHorizontalAlignment(SwingConstants.CENTER);
        usersLabel.setVerticalAlignment(SwingConstants.CENTER);
        usersLabel.setOpaque(true);
        
        genreLabel.setBackground(Color.decode("#303030"));
        genreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        genreLabel.setVerticalAlignment(SwingConstants.CENTER);
        genreLabel.setOpaque(true);
        
        profileLabel.setBackground(Color.decode("#303030"));
        profileLabel.setHorizontalAlignment(SwingConstants.CENTER);
        profileLabel.setVerticalAlignment(SwingConstants.CENTER);
        profileLabel.setOpaque(true);
        
        allTracksLabel.setBackground(Color.decode("#303030"));
        allTracksLabel.setHorizontalAlignment(SwingConstants.CENTER);
        allTracksLabel.setVerticalAlignment(SwingConstants.CENTER);
        allTracksLabel.setOpaque(true);
        
        logoutLabel.setBackground(Color.decode("#303030"));
        logoutLabel.setHorizontalAlignment(SwingConstants.CENTER);
        logoutLabel.setVerticalAlignment(SwingConstants.CENTER);
        logoutLabel.setOpaque(true);
        
//        TracksPanel.hide();
    }
    
    private void scaleImage() {
        ImageIcon icon = new ImageIcon("/Users/vetonbytyci/Desktop/spotify-logo-png-7053.png");        
        
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(iconLabel.getWidth(), iconLabel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        iconLabel.setIcon(scaledIcon);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        sidebarPane = new javax.swing.JDesktopPane();
        sidebarPanel = new javax.swing.JPanel();
        iconLabel = new javax.swing.JLabel();
        homeLabel = new javax.swing.JLabel();
        tracksLabel = new javax.swing.JLabel();
        playlistsLabel = new javax.swing.JLabel();
        usersLabel = new javax.swing.JLabel();
        genreLabel = new javax.swing.JLabel();
        profileLabel = new javax.swing.JLabel();
        allTracksLabel = new javax.swing.JLabel();
        logoutLabel = new javax.swing.JLabel();
        mainPane = new javax.swing.JDesktopPane();

        jDesktopPane1.setLayout(null);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(750, 500));
        setMinimumSize(new java.awt.Dimension(750, 500));

        sidebarPane.setBackground(new java.awt.Color(51, 51, 51));
        sidebarPane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        iconLabel.setMaximumSize(new java.awt.Dimension(40, 40));
        iconLabel.setMinimumSize(new java.awt.Dimension(30, 30));

        homeLabel.setBackground(new java.awt.Color(49, 49, 49));
        homeLabel.setForeground(new java.awt.Color(255, 255, 255));
        homeLabel.setText("Home");
        homeLabel.setToolTipText("");
        homeLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeLabelMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                homeLabelMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                homeLabelMouseEntered(evt);
            }
        });

        tracksLabel.setBackground(new java.awt.Color(49, 49, 49));
        tracksLabel.setForeground(new java.awt.Color(255, 255, 255));
        tracksLabel.setText("Tracks");
        tracksLabel.setToolTipText("");
        tracksLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tracksLabelMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tracksLabelMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tracksLabelMouseEntered(evt);
            }
        });

        playlistsLabel.setBackground(new java.awt.Color(49, 49, 49));
        playlistsLabel.setForeground(new java.awt.Color(255, 255, 255));
        playlistsLabel.setText("Playlists");
        playlistsLabel.setToolTipText("");
        playlistsLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                playlistsLabelMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                playlistsLabelMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                playlistsLabelMouseEntered(evt);
            }
        });

        usersLabel.setBackground(new java.awt.Color(49, 49, 49));
        usersLabel.setForeground(new java.awt.Color(255, 255, 255));
        usersLabel.setText("Users");
        usersLabel.setToolTipText("");
        usersLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usersLabelMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                usersLabelMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                usersLabelMouseEntered(evt);
            }
        });

        genreLabel.setBackground(new java.awt.Color(49, 49, 49));
        genreLabel.setForeground(new java.awt.Color(255, 255, 255));
        genreLabel.setText("Genres");
        genreLabel.setToolTipText("");
        genreLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                genreLabelMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                genreLabelMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                genreLabelMouseEntered(evt);
            }
        });

        profileLabel.setBackground(new java.awt.Color(49, 49, 49));
        profileLabel.setForeground(new java.awt.Color(255, 255, 255));
        profileLabel.setText("Profile");
        profileLabel.setToolTipText("");
        profileLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profileLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                profileLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                profileLabelMouseExited(evt);
            }
        });

        allTracksLabel.setBackground(new java.awt.Color(49, 49, 49));
        allTracksLabel.setForeground(new java.awt.Color(255, 255, 255));
        allTracksLabel.setText("All Tracks");
        allTracksLabel.setToolTipText("");
        allTracksLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                allTracksLabelMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                allTracksLabelMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                allTracksLabelMouseEntered(evt);
            }
        });

        logoutLabel.setBackground(new java.awt.Color(49, 49, 49));
        logoutLabel.setForeground(new java.awt.Color(255, 255, 255));
        logoutLabel.setText("Log Out");
        logoutLabel.setToolTipText("");
        logoutLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logoutLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                logoutLabelMouseExited(evt);
            }
        });

        javax.swing.GroupLayout sidebarPanelLayout = new javax.swing.GroupLayout(sidebarPanel);
        sidebarPanel.setLayout(sidebarPanelLayout);
        sidebarPanelLayout.setHorizontalGroup(
            sidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(iconLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(homeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tracksLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
            .addComponent(playlistsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
            .addComponent(usersLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
            .addComponent(genreLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
            .addComponent(profileLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
            .addComponent(allTracksLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
            .addComponent(logoutLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
        );
        sidebarPanelLayout.setVerticalGroup(
            sidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarPanelLayout.createSequentialGroup()
                .addComponent(iconLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(homeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(tracksLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(playlistsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(usersLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(genreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(allTracksLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(283, 283, 283)
                .addComponent(profileLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(logoutLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        sidebarPane.add(sidebarPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, 780));

        mainPane.setLayout(new javax.swing.OverlayLayout(mainPane));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(sidebarPane, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(mainPane, javax.swing.GroupLayout.PREFERRED_SIZE, 734, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sidebarPane)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPane, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void homeLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeLabelMouseEntered

        homeLabel.setBackground(Color.decode("#282828"));
        homeLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_homeLabelMouseEntered

    private void homeLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeLabelMouseExited

        if(!"home".equals(activeForm)) {
            homeLabel.setBackground(Color.decode("#303030"));
        }
    }//GEN-LAST:event_homeLabelMouseExited

    private void tracksLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tracksLabelMouseExited
        if(!"tracks".equals(activeForm)) {
            tracksLabel.setBackground(Color.decode("#303030"));
        }
       
    }//GEN-LAST:event_tracksLabelMouseExited

    private void tracksLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tracksLabelMouseEntered

        tracksLabel.setBackground(Color.decode("#282828"));
        tracksLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
    }//GEN-LAST:event_tracksLabelMouseEntered

    private void tracksLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tracksLabelMouseClicked
        // TODO add your handling code here:
        if(!"tracks".equals(activeForm)) {
            this.tf.show();
            setActiveForm("tracks");
        }
        
        if("tracks".equals(activeForm)) {
            this.hf.hide();
            this.pf.hide();
            this.gf.hide();
            this.uf.hide();
            this.prf.hide();
            this.atf.hide();
        }
    }//GEN-LAST:event_tracksLabelMouseClicked

    private void homeLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeLabelMouseClicked
        // TODO add your handling code here:
        if(!"home".equals(activeForm)) {
            this.hf.show();
            setActiveForm("home");
        }
        
        if("home".equals(activeForm)) {
            this.tf.hide();
            this.pf.hide();
            this.uf.hide();
            this.gf.hide();
            this.prf.hide();
            this.atf.hide();
        }
    }//GEN-LAST:event_homeLabelMouseClicked

    private void playlistsLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playlistsLabelMouseClicked
        // TODO add your handling code here:
        if(!"playlists".equals(activeForm)) {
            this.pf.show();
            setActiveForm("playlists");
        }
        
        if("playlists".equals(activeForm)) {
             this.tf.hide();
             this.hf.hide();
             this.uf.hide();
             this.gf.hide();
             this.prf.hide();
             this.atf.hide();
        }
    }//GEN-LAST:event_playlistsLabelMouseClicked

    private void playlistsLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playlistsLabelMouseExited
        // TODO add your handling code here:
        if(!"playlists".equals(activeForm)) {
            playlistsLabel.setBackground(Color.decode("#303030"));
        }
    }//GEN-LAST:event_playlistsLabelMouseExited

    private void playlistsLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playlistsLabelMouseEntered
        // TODO add your handling code here:
        playlistsLabel.setBackground(Color.decode("#282828"));
        playlistsLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_playlistsLabelMouseEntered

    private void usersLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usersLabelMouseClicked
        if(!"users".equals(activeForm)) {
            this.uf.show();
            setActiveForm("users");
        }
        
        if("users".equals(activeForm)) {
             this.tf.hide();
             this.hf.hide();
             this.pf.hide();
             this.gf.hide();
             this.prf.hide();
             this.atf.hide();
        }
    }//GEN-LAST:event_usersLabelMouseClicked

    private void usersLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usersLabelMouseExited
     
        if(!"users".equals(activeForm)) {
            usersLabel.setBackground(Color.decode("#303030"));
        }
    }//GEN-LAST:event_usersLabelMouseExited

    private void usersLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usersLabelMouseEntered
        
        usersLabel.setBackground(Color.decode("#282828"));
        usersLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_usersLabelMouseEntered

    private void genreLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_genreLabelMouseClicked
        if(!"genres".equals(activeForm)) {
            this.gf.show();
            setActiveForm("genres");
        }
        
        if("genres".equals(activeForm)) {
             this.tf.hide();
             this.hf.hide();
             this.pf.hide();
             this.uf.hide();
             this.prf.hide();
             this.atf.hide();
        }
    }//GEN-LAST:event_genreLabelMouseClicked

    private void genreLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_genreLabelMouseExited
        if(!"genres".equals(activeForm)) {
            genreLabel.setBackground(Color.decode("#303030"));
        }
    }//GEN-LAST:event_genreLabelMouseExited

    private void genreLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_genreLabelMouseEntered
        genreLabel.setBackground(Color.decode("#282828"));
        genreLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_genreLabelMouseEntered

    private void profileLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profileLabelMouseClicked
        if(!"profile".equals(activeForm)) {
            this.prf.show();
            setActiveForm("profile");
        }
        
        if("profile".equals(activeForm)) {
             this.tf.hide();
             this.atf.hide();
             this.hf.hide();
             this.pf.hide();
             this.uf.hide();
        }
    }//GEN-LAST:event_profileLabelMouseClicked

    private void profileLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profileLabelMouseEntered
        profileLabel.setBackground(Color.decode("#282828"));
        profileLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));    }//GEN-LAST:event_profileLabelMouseEntered

    private void profileLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profileLabelMouseExited
        if(!"profile".equals(activeForm)) {
            profileLabel.setBackground(Color.decode("#303030"));
        }
    }//GEN-LAST:event_profileLabelMouseExited

    private void allTracksLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_allTracksLabelMouseClicked
        if(!"all-tracks".equals(activeForm)) {
            this.atf.show();
            setActiveForm("all-tracks");
        }
        
        if("all-tracks".equals(activeForm)) {
             this.tf.hide();
             this.hf.hide();
             this.pf.hide();
             this.uf.hide();
        }
    }//GEN-LAST:event_allTracksLabelMouseClicked

    private void allTracksLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_allTracksLabelMouseExited
        if(!"all-tracks".equals(activeForm)) {
            allTracksLabel.setBackground(Color.decode("#303030"));
        }
    }//GEN-LAST:event_allTracksLabelMouseExited

    private void allTracksLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_allTracksLabelMouseEntered
        allTracksLabel.setBackground(Color.decode("#282828"));
        allTracksLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_allTracksLabelMouseEntered

    private void logoutLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutLabelMouseClicked
         new LoginForm().setVisible(true);
                this.setVisible(false);
    }//GEN-LAST:event_logoutLabelMouseClicked

    private void logoutLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutLabelMouseEntered
        logoutLabel.setBackground(Color.decode("#282828"));
        logoutLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_logoutLabelMouseEntered

    private void logoutLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutLabelMouseExited
        logoutLabel.setBackground(Color.decode("#303030"));
    }//GEN-LAST:event_logoutLabelMouseExited

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel allTracksLabel;
    private javax.swing.JLabel genreLabel;
    private javax.swing.JLabel homeLabel;
    private javax.swing.JLabel iconLabel;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel logoutLabel;
    private javax.swing.JDesktopPane mainPane;
    private javax.swing.JLabel playlistsLabel;
    private javax.swing.JLabel profileLabel;
    private javax.swing.JDesktopPane sidebarPane;
    private javax.swing.JPanel sidebarPanel;
    private javax.swing.JLabel tracksLabel;
    private javax.swing.JLabel usersLabel;
    // End of variables declaration//GEN-END:variables
}
