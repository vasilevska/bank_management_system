/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Filijala;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import podsistem1.NemogucaOperacijaException;

/**
 *
 * @author neven
 */
public class DohvatiFilijale extends Service{

    private JAXBContext ctx = null;
    
    @Override
    public Object execute() throws NemogucaOperacijaException {
        EntityManager em = super.getEM();
        TypedQuery<Filijala> rs = em.createQuery("SELECT f FROM Filijala f", Filijala.class);
        List<Filijala> res = rs.getResultList();
        return res;
    }

    @Override
    public JAXBContext getJAXBctx() {
        try {
            if(ctx == null) ctx = JAXBContext.newInstance(Filijala.class);
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
        return ctx;
    }
    
}
