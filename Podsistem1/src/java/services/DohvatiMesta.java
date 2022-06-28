/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Mesto;
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
public class DohvatiMesta extends Service{
    private JAXBContext ctx = null;
    
    @Override
    public Object execute() throws NemogucaOperacijaException {
        EntityManager em = super.getEM();
        TypedQuery<Mesto> rs = em.createQuery("SELECT m FROM Mesto m", Mesto.class);
        List<Mesto> res = rs.getResultList();
        return res;
    }

    @Override
    public JAXBContext getJAXBctx() {
        try {
            if(ctx == null) ctx = JAXBContext.newInstance(Mesto.class);
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
        return ctx;
    }
    
}
