/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.server.resources;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import javax.jms.*;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import services.*;
import static services.Ps1IDs.*;
import static services.Ps2IDs.*;
import static services.Ps3IDs.*;
/**
 *
 * @author neven
 */

@Path("service")
public class MainResource {
    
    @Resource(lookup = "pods1Queue")
    public Queue podsistem1Q;
    
    @Resource(lookup = "pods2Queue")
    public Queue podsistem2Q;
    
    @Resource(lookup = "pods3Queue")
    public Queue podsistem3Q;
    
    @Resource(lookup = "bankaConnFactory")
    public ConnectionFactory connectionFactory;
    
    private Response getData(Queue q, int brQ, int id, int selectorId){
        JMSContext context = Service.getContext(connectionFactory);
        JMSProducer producer = Service.getProducer(connectionFactory);
        JMSConsumer consumer = Service.getConsumer(connectionFactory, q, brQ);
            
        try { 
            TextMessage message = context.createTextMessage("poruka");
            message.setIntProperty("ID", id);
            message.setIntProperty("select", selectorId);
            
            producer.send(q, message);
            
            System.out.println("poslata poruka");
            BytesMessage response = (BytesMessage) consumer.receive();
            System.out.println("primljena poruka");
            
            byte[] bytes = response.getBody(byte[].class);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            baos.write(bytes, 0, bytes.length);

            return Response
                    .ok()
                    .entity(baos.toString())
                    .build();
            
        } catch (JMSException ex) {
            ex.printStackTrace();
            return Response.ok("greska").build();
        }
    }
    
    private Response getResp(Queue q, int brQ, int id, String str1, String str2, int int1, int int2 , int int3){
        JMSContext context = Service.getContext(connectionFactory);
        JMSProducer producer = Service.getProducer(connectionFactory);
        JMSConsumer consumer = Service.getConsumer(connectionFactory, q, brQ);
            
        try { 
            TextMessage message = context.createTextMessage("poruka");
            
            System.out.println(str1);
            System.out.println(str2);
            System.out.println(int1);
            System.out.println(int2);
            System.out.println(int3);
            message.setIntProperty("ID", id);
            message.setStringProperty("str1", str1);
            message.setStringProperty("str2", str2);
            message.setIntProperty("int1", int1);
            message.setIntProperty("int2", int2);
            message.setIntProperty("int3", int3);
            
            
            producer.send(q, message);
            
            System.out.println("poslata poruka");
            TextMessage response = (TextMessage) consumer.receive();
            System.out.println("primljena poruka");

            return Response
                    .ok()
                    .entity(response.getText())
                    .build();
            
        } catch (JMSException ex) {
            ex.printStackTrace();
            return Response.ok("greska").build();
        }
    }
    
    
    //@QueryParam("idStudenta") int idS
    @GET
    @Path("fFilijale")
    public Response fFilijale(){
        return getData(podsistem1Q, 1, DOHVATI_FILIJALE, 0);
    }
    
    @GET
    @Path("fMesta")
    public Response fMesta(){
        return getData(podsistem1Q, 1, DOHVATI_MESTA, 0);
    }
    
    @GET
    @Path("fKomitenti")
    public Response fKomitenti(){
        return getData(podsistem1Q, 1, DOHVATI_KOMITENTE, 0);
    }
    
    @GET
    @Path("fRacuni/{komitentId}")
    public Response fRacuni(@PathParam("komitentId") int kid){
        return getData(podsistem2Q, 2, DOHVATI_RACUNE, kid);
    }
    
    @GET
    @Path("fTransakcije/{racunId}")
    public Response fTransakcije(@PathParam("racunId") int rid){
        return getData(podsistem2Q, 2, DOHVATI_TRANSAKCIJE, rid);
    }
    
    @GET
    @Path("aMesto")
    public Response aMesto(@QueryParam("naziv") String n, @QueryParam("postanskiBr") int b){
        return getResp(podsistem1Q, 1, KREIRAJ_MESTO, n, "", b, 0, 0);
    }
    
    @GET
    @Path("aFilijala")
    public Response aFilijala(@QueryParam("naziv") String n, @QueryParam("adresa") String adr, @QueryParam("mestoId") int mid){
        System.out.println(n);
        System.out.println(adr);
        System.out.println(mid);
        return getResp(podsistem1Q, 1, KREIRAJ_FILIJALU, n, adr, mid, 0, 0);
    }
    
    @GET
    @Path("aKomitent")
    public Response aKomitent(@QueryParam("naziv") String n, @QueryParam("adresa") String adr, @QueryParam("mestoId") int mid){
        return getResp(podsistem1Q, 1, KREIRAJ_KOMITENTA, n, adr, mid, 0, 0);
    }
    
    @GET
    @Path("uSediste")
    public Response uSediste(@QueryParam("komitentId") int id, @QueryParam("adresa") String adr){
        return getResp(podsistem1Q, 1, PROMENI_SEDISTE, adr, "", id, 0, 0);
    }
    
    @GET
    @Path("aRacun")
    public Response aRacun(@QueryParam("komitentId") int kid, @QueryParam("mestoId") int mid, @QueryParam("dozvMinus") int minus){
        return getResp(podsistem2Q, 2, OTVORI_RACUN, "", "", kid, mid, minus);
    }
    
    @GET
    @Path("rRacun")
    public Response rRacun(@QueryParam("racunId") int rid){
        return getResp(podsistem2Q, 2, ZATVORI_RACUN, "", "", rid, 0, 0);
    }
    
    @GET
    @Path("Prenos")
    public Response prenos(@QueryParam("racunId") int rid, @QueryParam("primalacId") int pid, @QueryParam("iznos") int iznos, @QueryParam("svrha") String svrha){
        return getResp(podsistem2Q, 2, KREIRAJ_PRENOS, svrha, "", rid, pid, iznos);
    }
    
    @GET
    @Path("Uplata")
    public Response uplata(@QueryParam("racunId") int rid, @QueryParam("filijalaId") int fid, @QueryParam("iznos") int iznos, @QueryParam("svrha") String svrha){
        return getResp(podsistem2Q, 2, KREIRAJ_UPLATU, svrha, "", rid, fid, iznos);
    }
    
    @GET
    @Path("Isplata")
    public Response isplata(@QueryParam("racunId") int rid, @QueryParam("filijalaId") int fid, @QueryParam("iznos") int iznos, @QueryParam("svrha") String svrha){
        return getResp(podsistem2Q, 2, KREIRAJ_ISPLATU, svrha, "", rid, fid, iznos);
    }
    
    @GET
    @Path("BackUp")
    public Response backUp(){
        return getData(podsistem3Q, 3, DOHVATI_SVE, 0);
    }
    
    @GET
    @Path("Razlike")
    public Response razlike(){
        return getData(podsistem3Q, 3, PRIKAZI_RAZLIKE, 0);
    }
    
}
