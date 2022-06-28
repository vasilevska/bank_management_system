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
@Table(name = "uplata_isplata")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UplataIsplata.findAll", query = "SELECT u FROM UplataIsplata u"),
    @NamedQuery(name = "UplataIsplata.findByTransakcijaId", query = "SELECT u FROM UplataIsplata u WHERE u.transakcijaId = :transakcijaId"),
    @NamedQuery(name = "UplataIsplata.findByFilijalaId", query = "SELECT u FROM UplataIsplata u WHERE u.filijalaId = :filijalaId"),
    @NamedQuery(name = "UplataIsplata.findByTip", query = "SELECT u FROM UplataIsplata u WHERE u.tip = :tip")})
public class UplataIsplata implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TransakcijaId")
    private Integer transakcijaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FilijalaId")
    private int filijalaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Tip")
    private Character tip;
    @JoinColumn(name = "TransakcijaId", referencedColumnName = "TransakcijaId", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Transakcija transakcija;

    public UplataIsplata() {
    }

    public UplataIsplata(Integer transakcijaId) {
        this.transakcijaId = transakcijaId;
    }

    public UplataIsplata(Integer transakcijaId, int filijalaId, Character tip) {
        this.transakcijaId = transakcijaId;
        this.filijalaId = filijalaId;
        this.tip = tip;
    }

    public Integer getTransakcijaId() {
        return transakcijaId;
    }

    public void setTransakcijaId(Integer transakcijaId) {
        this.transakcijaId = transakcijaId;
    }

    public int getFilijalaId() {
        return filijalaId;
    }

    public void setFilijalaId(int filijalaId) {
        this.filijalaId = filijalaId;
    }

    public Character getTip() {
        return tip;
    }

    public void setTip(Character tip) {
        this.tip = tip;
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
        if (!(object instanceof UplataIsplata)) {
            return false;
        }
        UplataIsplata other = (UplataIsplata) object;
        if ((this.transakcijaId == null && other.transakcijaId != null) || (this.transakcijaId != null && !this.transakcijaId.equals(other.transakcijaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.UplataIsplata[ transakcijaId=" + transakcijaId + " ]";
    }
    
}
