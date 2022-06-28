/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "komitent2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Komitent2.findAll", query = "SELECT k FROM Komitent2 k"),
    @NamedQuery(name = "Komitent2.findByKomitentId", query = "SELECT k FROM Komitent2 k WHERE k.komitentId = :komitentId"),
    @NamedQuery(name = "Komitent2.findByNaziv", query = "SELECT k FROM Komitent2 k WHERE k.naziv = :naziv"),
    @NamedQuery(name = "Komitent2.findByAdresa", query = "SELECT k FROM Komitent2 k WHERE k.adresa = :adresa"),
    @NamedQuery(name = "Komitent2.findByMestoId", query = "SELECT k FROM Komitent2 k WHERE k.mestoId = :mestoId")})
public class Komitent2 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "KomitentId")
    private Integer komitentId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Naziv")
    private String naziv;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Adresa")
    private String adresa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "MestoId")
    private String mestoId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "komitentId")
    private List<Racun> racunList;

    public Komitent2() {
    }

    public Komitent2(Integer komitentId) {
        this.komitentId = komitentId;
    }

    public Komitent2(Integer komitentId, String naziv, String adresa, String mestoId) {
        this.komitentId = komitentId;
        this.naziv = naziv;
        this.adresa = adresa;
        this.mestoId = mestoId;
    }

    public Integer getKomitentId() {
        return komitentId;
    }

    public void setKomitentId(Integer komitentId) {
        this.komitentId = komitentId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getMestoId() {
        return mestoId;
    }

    public void setMestoId(String mestoId) {
        this.mestoId = mestoId;
    }

    @XmlTransient
    public List<Racun> getRacunList() {
        return racunList;
    }

    public void setRacunList(List<Racun> racunList) {
        this.racunList = racunList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (komitentId != null ? komitentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Komitent2)) {
            return false;
        }
        Komitent2 other = (Komitent2) object;
        if ((this.komitentId == null && other.komitentId != null) || (this.komitentId != null && !this.komitentId.equals(other.komitentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Komitent2[ komitentId=" + komitentId + " ]";
    }
    
}
