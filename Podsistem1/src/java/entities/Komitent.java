/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author neven
 */
@Entity
@Table(name = "komitent")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Komitent.findAll", query = "SELECT k FROM Komitent k"),
    @NamedQuery(name = "Komitent.findByKomitentId", query = "SELECT k FROM Komitent k WHERE k.komitentId = :komitentId"),
    @NamedQuery(name = "Komitent.findByNaziv", query = "SELECT k FROM Komitent k WHERE k.naziv = :naziv"),
    @NamedQuery(name = "Komitent.findByAdresa", query = "SELECT k FROM Komitent k WHERE k.adresa = :adresa")})
public class Komitent implements Serializable {

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
    @JoinColumn(name = "MestoId", referencedColumnName = "MestoId")
    @ManyToOne
    private Mesto mestoId;

    public Komitent() {
    }

    public Komitent(Integer komitentId) {
        this.komitentId = komitentId;
    }

    public Komitent(Integer komitentId, String naziv, String adresa) {
        this.komitentId = komitentId;
        this.naziv = naziv;
        this.adresa = adresa;
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

    public Mesto getMesto() {
        return mestoId;
    }

    public void setMesto(Mesto mesto) {
        this.mestoId = mesto;
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
        if (!(object instanceof Komitent)) {
            return false;
        }
        Komitent other = (Komitent) object;
        if ((this.komitentId == null && other.komitentId != null) || (this.komitentId != null && !this.komitentId.equals(other.komitentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Komitent[ komitentId=" + komitentId + " ]";
    }
    
}
