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
@Table(name = "filijala")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Filijala.findAll", query = "SELECT f FROM Filijala f"),
    @NamedQuery(name = "Filijala.findByFilijalaId", query = "SELECT f FROM Filijala f WHERE f.filijalaId = :filijalaId"),
    @NamedQuery(name = "Filijala.findByNaziv", query = "SELECT f FROM Filijala f WHERE f.naziv = :naziv"),
    @NamedQuery(name = "Filijala.findByAdresa", query = "SELECT f FROM Filijala f WHERE f.adresa = :adresa")})
public class Filijala implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "FilijalaId")
    private Integer filijalaId;
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

    public Filijala() {
    }

    public Filijala(Integer filijalaId) {
        this.filijalaId = filijalaId;
    }

    public Filijala(Integer filijalaId, String naziv, String adresa) {
        this.filijalaId = filijalaId;
        this.naziv = naziv;
        this.adresa = adresa;
    }

    public Integer getFilijalaId() {
        return filijalaId;
    }

    public void setFilijalaId(Integer filijalaId) {
        this.filijalaId = filijalaId;
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

    public Mesto getMestoId() {
        return mestoId;
    }

    public void setMestoId(Mesto mestoId) {
        this.mestoId = mestoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (filijalaId != null ? filijalaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Filijala)) {
            return false;
        }
        Filijala other = (Filijala) object;
        if ((this.filijalaId == null && other.filijalaId != null) || (this.filijalaId != null && !this.filijalaId.equals(other.filijalaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Filijala[ filijalaId=" + filijalaId + " ]";
    }
    
}
