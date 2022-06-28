/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Komitent;
import entities.Racun;
import java.sql.Timestamp;
import javax.persistence.EntityManager;
import javax.xml.bind.JAXBContext;
import podsistem2.NemogucaOperacijaException;

/**
 *
 * @author neven
 */
public class OtvoriRacun extends Service{
    private int komitentId;
    private int mestoId;
    private int dozvMinus;
    private JAXBContext ctx = null;

    public void setKomitentId(int komitentId) {
        this.komitentId = komitentId;
    }

    public void setMestoId(int mestoId) {
        this.mestoId = mestoId;
    }

    public void setDozvMinus(int dozvMinus) {
        this.dozvMinus = dozvMinus;
    }
    
    @Override
    public Object execute() throws NemogucaOperacijaException {
        EntityManager em = super.getEM();
        Komitent k = em.find(Komitent.class, komitentId);
        if(k == null) throw new NemogucaOperacijaException("unet je neppostojeci ID komitenta");
        Racun r = new Racun();
        r.setDozvoljeniminus(dozvMinus);
        r.setKomitentId(k);
        r.setMestoId(mestoId);
        r.setDatumVreme(new Timestamp(System.currentTimeMillis()));
        r.setStatus((short)1);
        em.getTransaction().begin();
        em.persist(r);
        em.getTransaction().commit();
        return "Racun je otvoren";
    }

    @Override
    public JAXBContext getJAXBctx() {
        return ctx;
    }
    
}
