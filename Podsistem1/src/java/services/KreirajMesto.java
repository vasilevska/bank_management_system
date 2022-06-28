/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Mesto;
import javax.persistence.EntityManager;
import javax.xml.bind.JAXBContext;
import podsistem1.NemogucaOperacijaException;

/**
 *
 * @author neven
 */
public class KreirajMesto extends Service {

    private String naziv;
    private int postanskiBr;

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setPostanskiBr(int postanskiBr) {
        this.postanskiBr = postanskiBr;
    }
    
    @Override
    public Object execute() throws NemogucaOperacijaException {
        Mesto m = new Mesto();
        m.setNaziv(naziv);
        m.setPostanskiBr(postanskiBr);
        EntityManager em = super.getEM();
        em.getTransaction().begin();
        em.persist(m);
        em.getTransaction().commit();
        return "Dodato mesto";
    }

    @Override
    public JAXBContext getJAXBctx() {
        return null;
    }
    
}
