/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Filijala;
import entities.Komitent;
import entities.Mesto;
import javax.persistence.EntityManager;
import javax.xml.bind.JAXBContext;
import podsistem1.NemogucaOperacijaException;

/**
 *
 * @author neven
 */
public class KreirajKomitenta extends Service{
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
    public Object execute() throws NemogucaOperacijaException{
        EntityManager em = super.getEM();
        Mesto m = em.find(Mesto.class, mestoId);
        if(m == null) throw new NemogucaOperacijaException("unet je nepravilan ID mesta");
        Komitent k = new Komitent();
        k.setAdresa(adresa);
        k.setNaziv(naziv);
        k.setMesto(m);
        em.getTransaction().begin();
        em.persist(k);
        em.getTransaction().commit();
        return "Napravljen komitent"; 
    }

    @Override
    public JAXBContext getJAXBctx() {
        return null;
    }
    
}
