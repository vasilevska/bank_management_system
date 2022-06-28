/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.klijent.resources;

/**
 *
 * @author neven
 */

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

/**
 *
 * @author 
 */
@Path("add")
public class AddResources{

    
    @POST
    @Path("mesto")
    @Consumes("application/x-www-form-urlencoded")
    public Response mesta(@FormParam("naziv") String naziv, @FormParam("postanskiBr") int poBr){
        naziv = naziv.replace(" ", "+");
        Client client = ClientBuilder.newClient();
        System.out.println(naziv);
        System.out.println(poBr);
        Response resp = client.target("http://localhost:8080/server/resources/service/aMesto?naziv=" + naziv + "&postanskiBr=" + poBr)
                .request().get();
        
        return resp;
    }
    
    @POST
    @Path("filijala")
    @Consumes("application/x-www-form-urlencoded")
    public Response filijale(@FormParam("naziv") String naziv, @FormParam("adresa") String adr, @FormParam("mestoId") int mid){
        naziv = naziv.replace(" ", "+");
        adr = adr.replace(" ", "+");
        Client client = ClientBuilder.newClient();
        Response resp = client.target("http://localhost:8080/server/resources/service/aFilijala?naziv=" + naziv + "&adresa=" + adr + "&mestoId=" + mid)
                .request().get();
        
        return resp;
    }
    
    @POST
    @Path("komitent")
    @Consumes("application/x-www-form-urlencoded")
    public Response komitenti(@FormParam("naziv") String naziv, @FormParam("adresa") String adr, @FormParam("mestoId") int mid){
        naziv = naziv.replace(" ", "+");
        adr = adr.replace(" ", "+");
        Client client = ClientBuilder.newClient();
        Response resp = client.target("http://localhost:8080/server/resources/service/aKomitent?naziv=" + naziv + "&adresa=" + adr + "&mestoId=" + mid)
                .request().get();
        
        return resp;
    }
    
    @POST
    @Path("sediste")
    @Consumes("application/x-www-form-urlencoded")
    public Response sediste(@FormParam("komitentId") int kid, @FormParam("adresa") String adr){
        adr = adr.replace(" ", "+");
        Client client = ClientBuilder.newClient();
        Response resp = client.target("http://localhost:8080/server/resources/service/uSediste?komitentId=" + kid + "&adresa=" + adr )
                .request().get();
        
        return resp;
    }
    
    @POST
    @Path("racun")
    @Consumes("application/x-www-form-urlencoded")
    public Response racun(@FormParam("komitentId") int kid, @FormParam("mestoId") int mid, @FormParam("dozvMinus") int minus){
        Client client = ClientBuilder.newClient();
        Response resp = client.target("http://localhost:8080/server/resources/service/aRacun?komitentId=" + kid + "&mestoId=" + mid + "&dozvMinus=" + minus )
                .request().get();
        
        return resp;
    }
    
    @POST
    @Path("rracun")
    @Consumes("application/x-www-form-urlencoded")
    public Response rracun(@FormParam("racunId") int rid){
        Client client = ClientBuilder.newClient();
        Response resp = client.target("http://localhost:8080/server/resources/service/rRacun?racunId=" + rid)
                .request().get();
        
        return resp;
    }
    
    @POST
    @Path("prenos")
    @Consumes("application/x-www-form-urlencoded")
    public Response prenos(@FormParam("racunId") int rid, @FormParam("primalacId") int pid, @FormParam("iznos") int iznos, @FormParam("svrha") String svrha){
        svrha = svrha.replace(" ", "+");
        Client client = ClientBuilder.newClient();
        Response resp = client.target("http://localhost:8080/server/resources/service/Prenos?racunId=" + rid + "&primalacId=" + pid + "&iznos=" + iznos + "&svrha=" + svrha)
                .request().get();
        
        return resp;
    }
    
    @POST
    @Path("uplata")
    @Consumes("application/x-www-form-urlencoded")
    public Response uplata(@FormParam("racunId") int rid, @FormParam("iznos") int iznos, @FormParam("filijalaId") int fid, @FormParam("svrha") String svrha){
        svrha = svrha.replace(" ", "+");
        Client client = ClientBuilder.newClient();
        Response resp = client.target("http://localhost:8080/server/resources/service/Uplata?racunId=" + rid + "&filijalaId=" + fid + "&iznos=" + iznos + "&svrha=" + svrha)
                .request().get();
        
        return resp;
    }
    
    @POST
    @Path("isplata")
    @Consumes("application/x-www-form-urlencoded")
    public Response isplata(@FormParam("racunId") int rid, @FormParam("iznos") int iznos, @FormParam("filijalaId") int fid, @FormParam("svrha") String svrha){
        svrha = svrha.replace(" ", "+");
        Client client = ClientBuilder.newClient();
        Response resp = client.target("http://localhost:8080/server/resources/service/Isplata?racunId=" + rid + "&filijalaId=" + fid + "&iznos=" + iznos + "&svrha=" + svrha)
                .request().get();
        
        return resp;
    }
    
}
