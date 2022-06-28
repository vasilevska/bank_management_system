/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package podsistem2;


import java.io.ByteArrayOutputStream;
import java.util.List;
import javax.annotation.Resource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.jms.*;
import services.*;
import static services.Ps2IDs.*;

/**
 *
 * @author neven
 */
public class Main {

    @Resource(lookup = "bankaConnFactory")
    public static ConnectionFactory connectionFactory;
    
    @Resource(lookup = "pods2Queue")
    public static Queue myQueue;
    
    @Resource(lookup = "p12Queue")
    public static Queue comQueue;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JMSContext context = connectionFactory.createContext();
        context.setClientID("Podsistem2");
        JMSConsumer consumer = context.createConsumer(myQueue, "ID IS NOT NULL");
        JMSProducer producer = context.createProducer();
        JAXBContext ctx = null;
       
        Service service = null;
        
        while(true){
            try {
                System.out.println("Waiting for new message...");
                TextMessage msg = (TextMessage)consumer.receive();
                
                System.out.println("received: " + msg.getText()+ " " + msg.getIntProperty("ID"));
                
                switch (msg.getIntProperty("ID")) {                                       // Delegate service
                    case(OTVORI_RACUN):{
                        System.out.println("Message: otvori racun");
                        OtvoriRacun r = new OtvoriRacun();
                        int kid = msg.getIntProperty("int1");
                        int mid  = msg.getIntProperty("int2");
                        int minus  = msg.getIntProperty("int3");
                        r.setKomitentId(kid);
                        r.setMestoId(mid);
                        r.setDozvMinus(minus);
                        service = r;
                        break;
                    }
                    case(ZATVORI_RACUN):{
                        System.out.println("Message: zatvori racun");
                        ZatvoriRacun r = new ZatvoriRacun();
                        int rid = msg.getIntProperty("int1");
                        r.setRacunId(rid);
                        service = r;
                        break;
                    }
                    case(KREIRAJ_PRENOS):{
                        System.out.println("Message: kreiraj prenos");
                        KreirajPrenos r = new KreirajPrenos();
                        String svrha = msg.getStringProperty("str1");
                        int rid = msg.getIntProperty("int1");
                        int pid = msg.getIntProperty("int2");
                        int iznos = msg.getIntProperty("int3");
                        r.setIznos(iznos);
                        r.setPrimalacId(pid);
                        r.setRacunId(rid);
                        r.setSvrha(svrha);
                        service = r;
                        break;
                    }
                    case(KREIRAJ_UPLATU):{
                        System.out.println("Message: kreiraj uplatu");
                        KreirajUplatu r = new KreirajUplatu();
                        String svrha = msg.getStringProperty("str1");
                        int rid = msg.getIntProperty("int1");
                        int fid = msg.getIntProperty("int2");
                        int iznos = msg.getIntProperty("int3");
                        r.setIznos(iznos);
                        r.setRacunId(rid);
                        r.setSvrha(svrha);
                        r.setFilijalaId(fid);
                        service = r;
                        break;
                    }
                    case(KREIRAJ_ISPLATU):{
                        System.out.println("Message: kreiraj isplatu");
                        KreirajIsplatu r = new KreirajIsplatu();
                        String svrha = msg.getStringProperty("str1");
                        int rid = msg.getIntProperty("int1");
                        int fid = msg.getIntProperty("int2");
                        int iznos = msg.getIntProperty("int3");
                        r.setIznos(iznos);
                        r.setRacunId(rid);
                        r.setSvrha(svrha);
                        r.setFilijalaId(fid);
                        service = r;
                        break;
                    }
                    case(DOHVATI_RACUNE):{
                        System.out.println("Message: dohvati racune");
                        DohvatiRacune r = new DohvatiRacune();
                        int kid = msg.getIntProperty("select");
                        r.setKomitentId(kid);
                        service = r;
                        break;
                    }
                    case(DOHVATI_TRANSAKCIJE):{
                        System.out.println("Message: dohvati transakcije");
                        DohvatiTransakcije t = new DohvatiTransakcije();
                        int rid = msg.getIntProperty("select");
                        t.setRacunId(rid);
                        service = t;
                        break;
                    }
                    default:{
                        throw new NemogucaOperacijaException("Prosledjen je nepostojeci ID operacije");
                    }
                }
                
                ctx = service.getJAXBctx();
                if(ctx != null){
                    List<Object> podaci = (List<Object>) service.execute();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    Marshaller marshaller = ctx.createMarshaller();

                    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                    marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
                    for(Object obj: podaci){
                        marshaller.marshal(obj, baos);

                    }
                    BytesMessage bMsg = context.createBytesMessage();
                    bMsg.writeBytes(baos.toByteArray());
                    System.out.println(baos.toString());
                    //TextMessage response = context.createTextMessage(poruka);
                    producer.send(myQueue, bMsg);
                    
                }
                else{
                    String response = (String) service.execute();
                    TextMessage tMsg = context.createTextMessage(response);
                    producer.send(myQueue, tMsg);
                }
                System.out.println("poslata poruka");

                
            } catch (NemogucaOperacijaException ex) {
                TextMessage tMsg = context.createTextMessage(ex.getMessage());
                producer.send(myQueue, tMsg);
            } catch (PropertyException ex) {
                ex.printStackTrace();
            } catch (JMSException | JAXBException ex) {
                ex.printStackTrace();
            } 
        }
    }
    
}

