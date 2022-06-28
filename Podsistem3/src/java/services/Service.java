/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBContext;
import podsistem3.NemogucaOperacijaException;

/**
 *
 * @author neven
 */
public abstract class Service implements Serializable, Ps3IDs {
    private static EntityManager em = null;
   
    public EntityManager getEM(){
        if(em == null){
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("Podsistem3PU");
            em = emf.createEntityManager();
        }
        return em;   
    }
    
    public abstract Object execute() throws NemogucaOperacijaException;

}
