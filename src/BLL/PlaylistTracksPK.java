/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author vetonbytyci
 */
@Embeddable
public class PlaylistTracksPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "playlistId")
    private int playlistId;
    @Basic(optional = false)
    @Column(name = "trackId")
    private int trackId;

    public PlaylistTracksPK() {
    }

    public PlaylistTracksPK(int playlistId, int trackId) {
        this.playlistId = playlistId;
        this.trackId = trackId;
    }

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) playlistId;
        hash += (int) trackId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlaylistTracksPK)) {
            return false;
        }
        PlaylistTracksPK other = (PlaylistTracksPK) object;
        if (this.playlistId != other.playlistId) {
            return false;
        }
        if (this.trackId != other.trackId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BLL.PlaylistTracksPK[ playlistId=" + playlistId + ", trackId=" + trackId + " ]";
    }
    
}
