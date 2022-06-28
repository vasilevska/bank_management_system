/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Racun;
import javax.persistence.EntityManager;
import javax.xml.bind.JAXBContext;
import podsistem2.NemogucaOperacijaException;

/**
 *
 * @author neven
 */
public class ZatvoriRacun extends Service{

    private int racunId;
    private JAXBContext ctx = null;
    
    @Override
    public Object execute() throws NemogucaOperacijaException {
        EntityManager em = super.getEM();
        Racun k = em.find(Racun.class, racunId);
        if(k == null) throw new NemogucaOperacijaException("unet je neppostojeci ID racuna");
        em.getTransaction().begin();
        em.remove(k);
        em.getTransaction().commit();
        return "Racun je zatvoren";
    }

    public void setRacunId(int racunId) {
        this.racunId = racunId;
    }

    @Override
    public JAXBContext getJAXBctx() {
        return ctx;
    }
    
}
