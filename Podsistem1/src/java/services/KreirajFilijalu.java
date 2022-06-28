/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Filijala;
import entities.Mesto;
import javax.persistence.EntityManager;
import javax.xml.bind.JAXBContext;
import podsistem1.NemogucaOperacijaException;

/**
 *
 * @author neven
 */
public class KreirajFilijalu extends Service{
    private String naziv;
    private int mestoId;
    private String adresa;

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setMestoId(int mestoId) {
        this.mestoId = mestoId;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }
    
    @Override
    public Object execute() throws NemogucaOperacijaException {
        EntityManager em = super.getEM();
        Mesto m = em.find(Mesto.class, mestoId);
        if(m == null)throw new NemogucaOperacijaException("unet je nepravilan ID mesta");
        Filijala f = new Filijala();
        f.setAdresa(adresa);
        f.setNaziv(naziv);
        f.setMesto(m);
        em.getTransaction().begin();
        em.persist(f);
        em.getTransaction().commit();
        return "Napravljena Filijala"; 
    }

    @Override
    public JAXBContext getJAXBctx() {
        return null;
    }
    
}
