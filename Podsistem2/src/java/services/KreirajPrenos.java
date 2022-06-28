/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Prenos;
import entities.Racun;
import entities.Transakcija;
import java.sql.Timestamp;
import javax.persistence.EntityManager;
import javax.xml.bind.JAXBContext;
import podsistem2.NemogucaOperacijaException;

/**
 *
 * @author neven
 */
public class KreirajPrenos extends Service{
    private int racunId;
    private int primalacId;
    private int iznos;

    public void setRacunId(int racunId) {
        this.racunId = racunId;
    }

    public void setPrimalacId(int primalacId) {
        this.primalacId = primalacId;
    }

    public void setIznos(int iznos) {
        this.iznos = iznos;
    }

    public void setSvrha(String svrha) {
        this.svrha = svrha;
    }
    private String svrha;
    private JAXBContext ctx = null;
    
    @Override
    public Object execute() throws NemogucaOperacijaException {
        EntityManager em = super.getEM();
        Racun r = em.find(Racun.class, racunId);
        Racun p = em.find(Racun.class, primalacId);
        if(r == null || p == null) throw new NemogucaOperacijaException("unet je neppostojeci ID racuna");

        em.getTransaction().begin();
        double stanje = r.getStanje();
        double dozvMinus = r.getDozvoljeniminus();
        if(stanje - iznos < -dozvMinus){
            r.setStatus((short)0);
        }
        r.setStanje(stanje - iznos);
        int brTr = r.getBrTransakcija()+1;
        r.setBrTransakcija(brTr);
        stanje = p.getStanje();
        dozvMinus = p.getDozvoljeniminus();
        if(stanje + iznos > -dozvMinus){
            p.setStatus((short)1);
        }
        p.setStanje(stanje + iznos);
        
        Transakcija t = new Transakcija();
        t.setDatumVreme(new Timestamp(System.currentTimeMillis()));
        t.setIznos(iznos);
        t.setRacunId(r);
        t.setSvrha(svrha);
        t.setRedniBr(brTr);

        Prenos prenos = new Prenos();
        
        prenos.setPrimalacId(p);
        
        
        
        em.persist(r);
        em.persist(p);
        em.persist(t);
        em.flush();
        prenos.setTransakcija(t);
        prenos.setTransakcijaId(t.getTransakcijaId());
        em.persist(prenos);
        t.setPrenos(prenos);
        em.persist(t);
        em.getTransaction().commit();
        return "Prenos je kreiran";
    }

    @Override
    public JAXBContext getJAXBctx() {
        return ctx;
    }
    
}
