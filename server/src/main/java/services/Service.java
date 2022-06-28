/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.Serializable;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.xml.bind.JAXBContext;


/**
 *
 * @author neven
 */
public abstract class Service implements Serializable, Ps1IDs, Ps2IDs, Ps3IDs {

    private static JMSProducer producer = null;
    private static JMSConsumer consumer1 = null;
    private static JMSConsumer consumer2 = null;
    private static JMSConsumer consumer3 = null;
    private static JMSContext context = null;
    
    public static JMSContext getContext(ConnectionFactory connectionFactory){
        if(context == null){
            context = connectionFactory.createContext();
        }
        return context;
    }
    public static JMSConsumer getConsumer(ConnectionFactory connectionFactory, Queue podsistem1Q, int id){
        switch(id){
            case(1):{
                if(consumer1 == null){
                    getContext(connectionFactory);
                    consumer1 = context.createConsumer(podsistem1Q, "ID IS NULL");
                }
                return consumer1;
            }
            case(2):{
                if(consumer2 == null){
                    getContext(connectionFactory);
                    consumer2 = context.createConsumer(podsistem1Q, "ID IS NULL");
                }
                return consumer2;
            }
            case(3):{
                if(consumer3 == null){
                    getContext(connectionFactory);
                    consumer3 = context.createConsumer(podsistem1Q, "ID IS NULL");
                }
                return consumer3;
            }
        }
        return null;
    }
    public static JMSProducer getProducer(ConnectionFactory connectionFactory){
        if(producer == null){
            getContext(connectionFactory);
            producer = context.createProducer();
        }
        return producer;
    }
    
    public abstract Object execute();
}
