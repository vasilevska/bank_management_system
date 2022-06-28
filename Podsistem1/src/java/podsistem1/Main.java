/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package podsistem1;
import java.io.ByteArrayOutputStream;
import java.util.List;
import javax.jms.*;

import javax.annotation.Resource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import services.*;
import static services.Ps1IDs.*;


/**
 *
 * @author neven
 */

public class Main {

    @Resource(lookup = "bankaConnFactory")
    public static ConnectionFactory connectionFactory;
    
    @Resource(lookup = "pods1Queue")
    public static Queue myQueue;
    
    @Resource(lookup = "p12Queue")
    public static Queue comQueue;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JMSContext context = connectionFactory.createContext();
        context.setClientID("Podsistem1");
        JMSConsumer consumer = context.createConsumer(myQueue, "ID IS NOT NULL");
        JMSProducer producer = context.createProducer();
        JAXBContext ctx = null;
       
        Service service = null;
        
        while(true){
            try {
                System.out.println("Waiting for new message...");
                TextMessage msg = (TextMessage)consumer.receive();
                
                System.out.println("received: " + msg.getText());
                
                switch (msg.getIntProperty("ID")) {                                       // Delegate service
                    case KREIRAJ_MESTO: {
                        System.out.println("Message: kreiraj mesto");
                        KreirajMesto m = new KreirajMesto();
                        m.setNaziv(msg.getStringProperty("str1"));
                        m.setPostanskiBr(msg.getIntProperty("int1"));
                        service = m;
                        break;
                    }
                    case KREIRAJ_FILIJALU: {
                        System.out.println("Message: kreiraj filijalu");
                        KreirajFilijalu k = new KreirajFilijalu();
                        k.setNaziv(msg.getStringProperty("str1"));
                        k.setAdresa(msg.getStringProperty("str2"));
                        k.setMestoId(msg.getIntProperty("int1"));
                        service = k;
                        break;
                    }
                    case KREIRAJ_KOMITENTA: {
                        System.out.println("Message: kreiraj komitenta");
                        KreirajKomitenta k = new KreirajKomitenta();
                        k.setNaziv(msg.getStringProperty("str1"));
                        k.setAdresa(msg.getStringProperty("str2"));
                        k.setMestoId(msg.getIntProperty("int1"));
                        service = k;
                        break;
                    }
                    case DOHVATI_MESTA:{
                        System.out.println("Message: dohvati mesta");
                        service = new DohvatiMesta();
                        break;
                    }
                    case DOHVATI_FILIJALE:{
                        System.out.println("Message: dohvati filijale");
                        service = new DohvatiFilijale();
                        break;
                    }
                    case DOHVATI_KOMITENTE:{
                        System.out.println("Message: dohvati komitente");
                        service = new DohvatiKomitente();
                        break;
                    }
                    case PROMENI_SEDISTE:{
                        System.out.println("Message: promeni sediste");
                        PromeniSediste s = new PromeniSediste();
                        s.setAdresa(msg.getStringProperty("str1"));
                        s.setKomitentId(msg.getIntProperty("int1"));
                        service = s;
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
