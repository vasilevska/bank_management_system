/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package podsistem3;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.jms.TextMessage;
import static services.Ps3IDs.GET_UPDATE;

/**
 *
 * @author neven
 */
public class Timing extends Thread {
    
    @Resource(lookup = "bankaConnFactory")
    public static ConnectionFactory connectionFactory;
    
    @Resource(lookup = "pods3Queue")
    public static Queue myQueue;
    
    @Override
    public void run() {
        JMSContext context = connectionFactory.createContext();
        JMSProducer producer = context.createProducer();
        
        System.out.println("Zapocet tajmer");

        long sleepTime = 120000;
        try {
            while (true) {
                Thread.sleep(sleepTime);
                TextMessage message = context.createTextMessage("poruka");
                message.setIntProperty("ID", GET_UPDATE);
                producer.send(myQueue, message);
                
            }
        }
        catch (InterruptedException e) {

        } catch (JMSException ex) {
            Logger.getLogger(Timing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
