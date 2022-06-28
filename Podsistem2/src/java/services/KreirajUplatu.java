/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Racun;
import entities.Transakcija;
import entities.UplataIsplata;
import java.sql.Timestamp;
import javax.persistence.EntityManager;
import javax.xml.bind.JAXBContext;
import podsistem2.NemogucaOperacijaException;

/**
 *
 * @author neven
 */
public class KreirajUplatu extends Service{

    private int racunId;
    private int iznos;
    private int filijalaId;
    private String svrha;
    
    private JAXBContext ctx = null;
    
    @Override
    public Object execute() throws NemogucaOperacijaException {
        EntityManager em = super.getEM();
        Racun r = em.find(Racun.class, racunId);
        if(r == null) throw new NemogucaOperacijaException("unet je neppostojeci ID racuna");

        em.getTransaction().begin();
        double stanje = r.getStanje();
        double dozvMinus = r.getDozvoljeniminus();
        if(stanje + iznos > -dozvMinus){
            r.setStatus((short)1);
        }
        r.setStanje(stanje + iznos);
        int brTr = r.getBrTransakcija()+1;
        r.setBrTransakcija(brTr);
                
        Transakcija t = new Transakcija();
        t.setDatumVreme(new Timestamp(System.currentTimeMillis()));
        t.setIznos(iznos);
        t.setRacunId(r);
        t.setSvrha(svrha);
        t.setRedniBr(brTr);
        
        UplataIsplata i = new UplataIsplata();
        i.setFilijalaId(filijalaId);
        i.setTip('U');
        
        //i.setTransakcija(t);
        
        
        em.persist(r);
        em.persist(t);
        em.flush();
        i.setTransakcijaId(t.getTransakcijaId());
        em.persist(i);
        t.setUplataIsplata(i);
        em.persist(t);
        em.getTransaction().commit();
        return "Izvrsena je uplata";
    }

    @Override
    public JAXBContext getJAXBctx() {
        return ctx;
    }

    public void setRacunId(int racunId) {
        this.racunId = racunId;
    }

    public void setIznos(int iznos) {
        this.iznos = iznos;
    }

    public void setFilijalaId(int filijalaId) {
        this.filijalaId = filijalaId;
    }

    public void setSvrha(String svrha) {
        this.svrha = svrha;
    }
    
}
