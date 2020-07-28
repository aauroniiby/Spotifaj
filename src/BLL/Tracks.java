/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vetonbytyci
 */
@Entity
@Table(name = "Tracks")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tracks.findAll", query = "SELECT t FROM Tracks t"),
    @NamedQuery(name = "Tracks.findById", query = "SELECT t FROM Tracks t WHERE t.id = :id"),
    @NamedQuery(name = "Tracks.findByTitle", query = "SELECT t FROM Tracks t WHERE t.title = :title"),
    @NamedQuery(name = "Tracks.findByArtist", query = "SELECT t FROM Tracks t WHERE t.artist = :artist"),
    @NamedQuery(name = "Tracks.findByAlbum", query = "SELECT t FROM Tracks t WHERE t.album = :album"),
    @NamedQuery(name = "Tracks.findByYear", query = "SELECT t FROM Tracks t WHERE t.year = :year"),
    @NamedQuery(name = "Tracks.findByRating", query = "SELECT t FROM Tracks t WHERE t.rating = :rating"),
    @NamedQuery(name = "Tracks.findByArtwork", query = "SELECT t FROM Tracks t WHERE t.artwork = :artwork"),
    @NamedQuery(name = "Tracks.findByGenre", query = "SELECT t FROM Tracks t WHERE t.genre = :genre")})
public class Tracks implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @Column(name = "artist")
    private String artist;
    @Column(name = "album")
    private String album;
    @Column(name = "year")
    private String year;
    @Column(name = "rating")
    private Integer rating;
    @Column(name = "artwork")
    private String artwork;
    @Basic(optional = false)
    @Column(name = "genre")
    private int genre;
    @JoinColumn(name = "owner", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users owner;

    public Tracks() {
    }

    public Tracks(Integer id) {
        this.id = id;
    }

    public Tracks(Integer id, String title, String artist, int genre) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.genre = genre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getArtwork() {
        return artwork;
    }

    public void setArtwork(String artwork) {
        this.artwork = artwork;
    }

    public int getGenre() {
        return genre;
    }

    public void setGenre(int genre) {
        this.genre = genre;
    }

    public Users getOwner() {
        return owner;
    }

    public void setOwner(Users owner) {
        this.owner = owner;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tracks)) {
            return false;
        }
        Tracks other = (Tracks) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BLL.Tracks[ id=" + id + " ]";
    }
    
}
