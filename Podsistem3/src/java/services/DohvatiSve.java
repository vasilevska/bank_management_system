/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.xml.bind.JAXBContext;
import podsistem3.NemogucaOperacijaException;
import entities.*;
import java.io.ByteArrayOutputStream;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author neven
 */
public class DohvatiSve extends Service{
    
    private JAXBContext ctx = null;
    private Marshaller marshaller = null;
    
    @Override
    public Object execute() throws NemogucaOperacijaException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            EntityManager em = super.getEM();
            TypedQuery<Komitent> rs = em.createQuery("SELECT k FROM Komitent k", Komitent.class);
            List<Komitent> podaci = rs.getResultList();
            
            ctx = JAXBContext.newInstance(Komitent.class);
            marshaller = ctx.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            for(Object obj: podaci){
                marshaller.marshal(obj, baos);
            }
            
            TypedQuery<Filijala> rs1 = em.createQuery("SELECT k FROM Filijala k", Filijala.class);
            List<Filijala>podaci1 = rs1.getResultList();
            ctx = JAXBContext.newInstance(Filijala.class);
            marshaller = ctx.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            for(Object obj: podaci1){
                marshaller.marshal(obj, baos);
            }
            
            TypedQuery<Mesto> rs2 = em.createQuery("SELECT k FROM Mesto k", Mesto.class);
            List<Mesto>podaci2 = rs2.getResultList();
            ctx = JAXBContext.newInstance(Mesto.class);
            marshaller = ctx.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            for(Object obj: podaci2){
                marshaller.marshal(obj, baos);
            }
            
            TypedQuery<Racun> rs3 = em.createQuery("SELECT k FROM Racun k", Racun.class);
            List<Racun>podaci3 = rs3.getResultList();
            ctx = JAXBContext.newInstance(Racun.class);
            marshaller = ctx.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            for(Object obj: podaci3){
                marshaller.marshal(obj, baos);
            }
            
            TypedQuery<Transakcija> rs4 = em.createQuery("SELECT k FROM Transakcija k", Transakcija.class);
            List<Transakcija>podaci4 = rs4.getResultList();
            ctx = JAXBContext.newInstance(Transakcija.class);
            marshaller = ctx.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            for(Object obj: podaci4){
                marshaller.marshal(obj, baos);
            }
            
            TypedQuery<Komitent2> rs5 = em.createQuery("SELECT k FROM Komitent2 k", Komitent2.class);
            List<Komitent2> podaci5 = rs5.getResultList();
            
            ctx = JAXBContext.newInstance(Komitent2.class);
            marshaller = ctx.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            for(Object obj: podaci5){
                marshaller.marshal(obj, baos);
            }
                   

        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
        return baos;
    }
    
}
