/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Racun;
import entities.Transakcija;
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
public class DohvatiTransakcije extends Service{
    private int racunId;

    public void setRacunId(int racunId) {
        this.racunId = racunId;
    }
    
    private JAXBContext ctx = null;
    
    @Override
    public Object execute() throws NemogucaOperacijaException {
        EntityManager em = super.getEM();
        TypedQuery<Transakcija> rs = em.createQuery("SELECT t FROM Transakcija t JOIN t.racunId r WHERE r.racunId = :ID", Transakcija.class);
        List<Transakcija> res = rs.setParameter("ID", racunId).getResultList();
        return res;
    }

    @Override
    public JAXBContext getJAXBctx() {
        try {
            if(ctx == null) ctx = JAXBContext.newInstance(Transakcija.class);
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
        return ctx;
    }
    
}
