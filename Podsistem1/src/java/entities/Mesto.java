/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author neven
 */
@Entity
@Table(name = "mesto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mesto.findAll", query = "SELECT m FROM Mesto m"),
    @NamedQuery(name = "Mesto.findByMestoId", query = "SELECT m FROM Mesto m WHERE m.mestoId = :mestoId"),
    @NamedQuery(name = "Mesto.findByNaziv", query = "SELECT m FROM Mesto m WHERE m.naziv = :naziv"),
    @NamedQuery(name = "Mesto.findByPostanskiBr", query = "SELECT m FROM Mesto m WHERE m.postanskiBr = :postanskiBr")})
public class Mesto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MestoId")
    private Integer mestoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Naziv")
    private String naziv;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PostanskiBr")
    private int postanskiBr;
    @OneToMany(mappedBy = "mestoId")
    private List<Filijala> filijalaList;
    @OneToMany(mappedBy = "mestoId")
    private List<Komitent> komitentList;

    public Mesto() {
    }

    public Mesto(Integer mestoId) {
        this.mestoId = mestoId;
    }

    public Mesto(Integer mestoId, String naziv, int postanskiBr) {
        this.mestoId = mestoId;
        this.naziv = naziv;
        this.postanskiBr = postanskiBr;
    }

    public Integer getMestoId() {
        return mestoId;
    }

    public void setMestoId(Integer mestoId) {
        this.mestoId = mestoId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getPostanskiBr() {
        return postanskiBr;
    }

    public void setPostanskiBr(int postanskiBr) {
        this.postanskiBr = postanskiBr;
    }

    @XmlTransient
    public List<Filijala> getFilijalaList() {
        return filijalaList;
    }

    public void setFilijalaList(List<Filijala> filijalaList) {
        this.filijalaList = filijalaList;
    }

    @XmlTransient
    public List<Komitent> getKomitentList() {
        return komitentList;
    }

    public void setKomitentList(List<Komitent> komitentList) {
        this.komitentList = komitentList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mestoId != null ? mestoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mesto)) {
            return false;
        }
        Mesto other = (Mesto) object;
        if ((this.mestoId == null && other.mestoId != null) || (this.mestoId != null && !this.mestoId.equals(other.mestoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Mesto[ mestoId=" + mestoId + " ]";
    }
    
}
