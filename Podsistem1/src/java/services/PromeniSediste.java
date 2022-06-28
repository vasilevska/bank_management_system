/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Komitent;
import javax.persistence.EntityManager;
import javax.xml.bind.JAXBContext;
import podsistem1.NemogucaOperacijaException;

/**
 *
 * @author neven
 */
public class PromeniSediste extends Service {
    private int komitentId;
    private String adresa;

    public void setKomitentId(int komitentId) {
        this.komitentId = komitentId;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }
    
    @Override
    public Object execute() throws NemogucaOperacijaException{
        EntityManager em = super.getEM();
        Komitent k = em.find(Komitent.class, komitentId);
        if(k == null)throw new NemogucaOperacijaException("Dat je nepostojeci ID komitenta " + komitentId);
        k.setAdresa(adresa);
        em.getTransaction().begin();
        em.persist(k);
        em.getTransaction().commit();
        return "Izmenjeno sediste komitenta"; 
    }

    @Override
    public JAXBContext getJAXBctx() {
        return null;
    }
    
}
