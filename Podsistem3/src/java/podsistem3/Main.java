/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package podsistem3;

import java.io.ByteArrayOutputStream;
import javax.annotation.Resource;
import javax.jms.*;
import javax.xml.bind.JAXBContext;
import services.DohvatiSve;
import static services.Ps3IDs.*;
import services.*;

/**
 *
 * @author neven
 */
public class Main {

    @Resource(lookup = "bankaConnFactory")
    public static ConnectionFactory connectionFactory;
    
    @Resource(lookup = "pods3Queue")
    public static Queue myQueue;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JMSContext context = connectionFactory.createContext();
        context.setClientID("Podsistem3");
        JMSConsumer consumer = context.createConsumer(myQueue, "ID IS NOT NULL");
        JMSProducer producer = context.createProducer();
        JAXBContext ctx = null;
       
        Service service = null;
        Timing time = new Timing();
        time.start();
        while(true){
            try {
                System.out.println("Waiting for new message...");
                TextMessage msg = (TextMessage)consumer.receive();
                
                System.out.println("received: " + msg.getText());
                
                switch (msg.getIntProperty("ID")) {                                       // Delegate service
                    case DOHVATI_SVE: {
                        System.out.println("Message: dohvati sve");
                        service = new DohvatiSve();
                        break;
                    }
                    case PRIKAZI_RAZLIKE:{
                        System.out.println("Message: prikazi razlike");
                        break;
                    }
                    case GET_UPDATE:{
                        System.out.println("Message: dohvati update");
                        
                        break;
                    }
                    default:{
                        throw new NemogucaOperacijaException("Prosledjen je nepostojeci ID operacije");
                    }
                }
                
                    ByteArrayOutputStream baos = (ByteArrayOutputStream) service.execute();
                    BytesMessage bMsg = context.createBytesMessage();
                    bMsg.writeBytes(baos.toByteArray());
                    System.out.println(baos.toString());
                    //TextMessage response = context.createTextMessage(poruka);
                    producer.send(myQueue, bMsg);
                    
                
                
                System.out.println("poslata poruka");

                
            } catch (NemogucaOperacijaException ex) {
                TextMessage tMsg = context.createTextMessage(ex.getMessage());
                producer.send(myQueue, tMsg);
            } catch (JMSException ex) {
                ex.printStackTrace();
            } 
        }
    }
    
}
