/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author neven
 */
@Entity
@Table(name = "transakcija")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transakcija.findAll", query = "SELECT t FROM Transakcija t"),
    @NamedQuery(name = "Transakcija.findByTransakcijaId", query = "SELECT t FROM Transakcija t WHERE t.transakcijaId = :transakcijaId"),
    @NamedQuery(name = "Transakcija.findByDatumVreme", query = "SELECT t FROM Transakcija t WHERE t.datumVreme = :datumVreme"),
    @NamedQuery(name = "Transakcija.findByIznos", query = "SELECT t FROM Transakcija t WHERE t.iznos = :iznos"),
    @NamedQuery(name = "Transakcija.findByRedniBr", query = "SELECT t FROM Transakcija t WHERE t.redniBr = :redniBr"),
    @NamedQuery(name = "Transakcija.findBySvrha", query = "SELECT t FROM Transakcija t WHERE t.svrha = :svrha")})
public class Transakcija implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TransakcijaId")
    private Integer transakcijaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DatumVreme")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumVreme;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Iznos")
    private double iznos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RedniBr")
    private int redniBr;
    @Size(max = 45)
    @Column(name = "Svrha")
    private String svrha;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "transakcija")
    private UplataIsplata uplataIsplata;
    @JoinColumn(name = "RacunId", referencedColumnName = "RacunId")
    @ManyToOne(optional = false)
    private Racun racunId;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "transakcija")
    private Prenos prenos;

    public Transakcija() {
    }

    public Transakcija(Integer transakcijaId) {
        this.transakcijaId = transakcijaId;
    }

    public Transakcija(Integer transakcijaId, Date datumVreme, double iznos, int redniBr) {
        this.transakcijaId = transakcijaId;
        this.datumVreme = datumVreme;
        this.iznos = iznos;
        this.redniBr = redniBr;
    }

    public Integer getTransakcijaId() {
        return transakcijaId;
    }

    public void setTransakcijaId(Integer transakcijaId) {
        this.transakcijaId = transakcijaId;
    }

    public Date getDatumVreme() {
        return datumVreme;
    }

    public void setDatumVreme(Date datumVreme) {
        this.datumVreme = datumVreme;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public int getRedniBr() {
        return redniBr;
    }

    public void setRedniBr(int redniBr) {
        this.redniBr = redniBr;
    }

    public String getSvrha() {
        return svrha;
    }

    public void setSvrha(String svrha) {
        this.svrha = svrha;
    }

    public UplataIsplata getUplataIsplata() {
        return uplataIsplata;
    }

    public void setUplataIsplata(UplataIsplata uplataIsplata) {
        this.uplataIsplata = uplataIsplata;
    }

    public Racun getRacunId() {
        return racunId;
    }

    public void setRacunId(Racun racunId) {
        this.racunId = racunId;
    }

    public Prenos getPrenos() {
        return prenos;
    }

    public void setPrenos(Prenos prenos) {
        this.prenos = prenos;
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
        if (!(object instanceof Transakcija)) {
            return false;
        }
        Transakcija other = (Transakcija) object;
        if ((this.transakcijaId == null && other.transakcijaId != null) || (this.transakcijaId != null && !this.transakcijaId.equals(other.transakcijaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Transakcija[ transakcijaId=" + transakcijaId + " ]";
    }
    
}
