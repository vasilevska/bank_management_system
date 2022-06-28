/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Racun;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import podsistem2.NemogucaOperacijaException;

/**
 *
 * @author neven
 */
public class DohvatiRacune extends Service {
    private int komitentId;

    public void setKomitentId(int komitentID) {
        this.komitentId = komitentID;
    }

    private JAXBContext ctx = null;
    
    @Override
    public Object execute() throws NemogucaOperacijaException {
        EntityManager em = super.getEM();
        TypedQuery<Racun> rs = em.createQuery("SELECT r FROM Racun r JOIN r.komitentId k WHERE k.komitentId = :ID", Racun.class);
        List<Racun> res = rs.setParameter("ID", komitentId).getResultList();
        return res;
    }

    @Override
    public JAXBContext getJAXBctx() {
        try {
            if(ctx == null) ctx = JAXBContext.newInstance(Racun.class);
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
        return ctx;
    }
    
}
