/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author neven
 */
@Entity
@Table(name = "racun")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Racun.findAll", query = "SELECT r FROM Racun r"),
    @NamedQuery(name = "Racun.findByRacunId", query = "SELECT r FROM Racun r WHERE r.racunId = :racunId"),
    @NamedQuery(name = "Racun.findByBrTransakcija", query = "SELECT r FROM Racun r WHERE r.brTransakcija = :brTransakcija"),
    @NamedQuery(name = "Racun.findByStatus", query = "SELECT r FROM Racun r WHERE r.status = :status"),
    @NamedQuery(name = "Racun.findByDozvoljeniMinus", query = "SELECT r FROM Racun r WHERE r.dozvoljeniMinus = :dozvoljeniMinus"),
    @NamedQuery(name = "Racun.findByStanje", query = "SELECT r FROM Racun r WHERE r.stanje = :stanje"),
    @NamedQuery(name = "Racun.findByMestoId", query = "SELECT r FROM Racun r WHERE r.mestoId = :mestoId"),
    @NamedQuery(name = "Racun.findByDatumVreme", query = "SELECT r FROM Racun r WHERE r.datumVreme = :datumVreme")})
public class Racun implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RacunId")
    private Integer racunId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BrTransakcija")
    private int brTransakcija;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Status")
    private short status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DozvoljeniMinus")
    private double dozvoljeniMinus;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Stanje")
    private double stanje;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MestoId")
    private int mestoId;
    @Column(name = "DatumVreme")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumVreme;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "racunId")
    private List<Transakcija> transakcijaList;
    @JoinColumn(name = "KomitentId", referencedColumnName = "KomitentId")
    @ManyToOne(optional = false)
    private Komitent2 komitentId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "primalacId")
    private List<Prenos> prenosList;

    public Racun() {
    }

    public Racun(Integer racunId) {
        this.racunId = racunId;
    }

    public Racun(Integer racunId, int brTransakcija, short status, double dozvoljeniMinus, double stanje, int mestoId) {
        this.racunId = racunId;
        this.brTransakcija = brTransakcija;
        this.status = status;
        this.dozvoljeniMinus = dozvoljeniMinus;
        this.stanje = stanje;
        this.mestoId = mestoId;
    }

    public Integer getRacunId() {
        return racunId;
    }

    public void setRacunId(Integer racunId) {
        this.racunId = racunId;
    }

    public int getBrTransakcija() {
        return brTransakcija;
    }

    public void setBrTransakcija(int brTransakcija) {
        this.brTransakcija = brTransakcija;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public double getDozvoljeniMinus() {
        return dozvoljeniMinus;
    }

    public void setDozvoljeniMinus(double dozvoljeniMinus) {
        this.dozvoljeniMinus = dozvoljeniMinus;
    }

    public double getStanje() {
        return stanje;
    }

    public void setStanje(double stanje) {
        this.stanje = stanje;
    }

    public int getMestoId() {
        return mestoId;
    }

    public void setMestoId(int mestoId) {
        this.mestoId = mestoId;
    }

    public Date getDatumVreme() {
        return datumVreme;
    }

    public void setDatumVreme(Date datumVreme) {
        this.datumVreme = datumVreme;
    }

    @XmlTransient
    public List<Transakcija> getTransakcijaList() {
        return transakcijaList;
    }

    public void setTransakcijaList(List<Transakcija> transakcijaList) {
        this.transakcijaList = transakcijaList;
    }

    public Komitent2 getKomitentId() {
        return komitentId;
    }

    public void setKomitentId(Komitent2 komitentId) {
        this.komitentId = komitentId;
    }

    @XmlTransient
    public List<Prenos> getPrenosList() {
        return prenosList;
    }

    public void setPrenosList(List<Prenos> prenosList) {
        this.prenosList = prenosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (racunId != null ? racunId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Racun)) {
            return false;
        }
        Racun other = (Racun) object;
        if ((this.racunId == null && other.racunId != null) || (this.racunId != null && !this.racunId.equals(other.racunId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Racun[ racunId=" + racunId + " ]";
    }
    
}
