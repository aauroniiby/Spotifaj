/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vetonbytyci
 */
@Entity
@Table(name = "Playlists")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Playlists.findAll", query = "SELECT p FROM Playlists p"),
    @NamedQuery(name = "Playlists.findById", query = "SELECT p FROM Playlists p WHERE p.id = :id"),
    @NamedQuery(name = "Playlists.findByName", query = "SELECT p FROM Playlists p WHERE p.name = :name")})
public class Playlists implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @JoinTable(name = "User_Playlists", joinColumns = {
        @JoinColumn(name = "playlistId", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "userId", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Users> usersCollection;

    public Playlists() {
    }

    public Playlists(Integer id) {
        this.id = id;
    }

    public Playlists(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
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
        if (!(object instanceof Playlists)) {
            return false;
        }
        Playlists other = (Playlists) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BLL.Playlists[ id=" + id + " ]";
    }
    
}
