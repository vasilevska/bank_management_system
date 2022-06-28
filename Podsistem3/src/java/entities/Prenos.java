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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author neven
 */
@Entity
@Table(name = "prenos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prenos.findAll", query = "SELECT p FROM Prenos p"),
    @NamedQuery(name = "Prenos.findByTransakcijaId", query = "SELECT p FROM Prenos p WHERE p.transakcijaId = :transakcijaId")})
public class Prenos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TransakcijaId")
    private Integer transakcijaId;
    @JoinColumn(name = "PrimalacId", referencedColumnName = "RacunId")
    @ManyToOne(optional = false)
    private Racun primalacId;
    @JoinColumn(name = "TransakcijaId", referencedColumnName = "TransakcijaId", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Transakcija transakcija;

    public Prenos() {
    }

    public Prenos(Integer transakcijaId) {
        this.transakcijaId = transakcijaId;
    }

    public Integer getTransakcijaId() {
        return transakcijaId;
    }

    public void setTransakcijaId(Integer transakcijaId) {
        this.transakcijaId = transakcijaId;
    }

    public Racun getPrimalacId() {
        return primalacId;
    }

    public void setPrimalacId(Racun primalacId) {
        this.primalacId = primalacId;
    }

    public Transakcija getTransakcija() {
        return transakcija;
    }

    public void setTransakcija(Transakcija transakcija) {
        this.transakcija = transakcija;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transakcijaId != null ? transakcijaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prenos)) {
            return false;
        }
        Prenos other = (Prenos) object;
        if ((this.transakcijaId == null && other.transakcijaId != null) || (this.transakcijaId != null && !this.transakcijaId.equals(other.transakcijaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Prenos[ transakcijaId=" + transakcijaId + " ]";
    }
    
}
