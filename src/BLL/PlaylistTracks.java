/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vetonbytyci
 */
@Entity
@Table(name = "Playlist_Tracks")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlaylistTracks.findAll", query = "SELECT p FROM PlaylistTracks p"),
    @NamedQuery(name = "PlaylistTracks.findByPlaylistId", query = "SELECT p FROM PlaylistTracks p WHERE p.playlistTracksPK.playlistId = :playlistId"),
    @NamedQuery(name = "PlaylistTracks.findByTrackId", query = "SELECT p FROM PlaylistTracks p WHERE p.playlistTracksPK.trackId = :trackId")})
public class PlaylistTracks implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PlaylistTracksPK playlistTracksPK;

    public PlaylistTracks() {
    }

    public PlaylistTracks(PlaylistTracksPK playlistTracksPK) {
        this.playlistTracksPK = playlistTracksPK;
    }

    public PlaylistTracks(int playlistId, int trackId) {
        this.playlistTracksPK = new PlaylistTracksPK(playlistId, trackId);
    }

    public PlaylistTracksPK getPlaylistTracksPK() {
        return playlistTracksPK;
    }

    public void setPlaylistTracksPK(PlaylistTracksPK playlistTracksPK) {
        this.playlistTracksPK = playlistTracksPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (playlistTracksPK != null ? playlistTracksPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlaylistTracks)) {
            return false;
        }
        PlaylistTracks other = (PlaylistTracks) object;
        if ((this.playlistTracksPK == null && other.playlistTracksPK != null) || (this.playlistTracksPK != null && !this.playlistTracksPK.equals(other.playlistTracksPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BLL.PlaylistTracks[ playlistTracksPK=" + playlistTracksPK + " ]";
    }
    
}
