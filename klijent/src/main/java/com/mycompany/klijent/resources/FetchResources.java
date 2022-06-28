/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.klijent.resources;

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
@Path("fetch")
public class FetchResources{
    
    @POST
    @Path("mesta")
    public Response mesta(){
        Client client = ClientBuilder.newClient();
        Response resp = client.target("http://localhost:8080/server/resources/service/fMesta")
                .request().get();
        
        return resp;
    }
    
    @POST
    @Path("filijale")
    public Response filijale(){
        Client client = ClientBuilder.newClient();
        Response resp = client.target("http://localhost:8080/server/resources/service/fFilijale")
                .request().get();
        
        return resp;
    }
    
    @POST
    @Path("komitenti")
    public Response komitenti(){
        Client client = ClientBuilder.newClient();
        Response resp = client.target("http://localhost:8080/server/resources/service/fKomitenti")
                .request().get();
        
        return resp;
    }
    
    @POST
    @Path("racuni")
    @Consumes("application/x-www-form-urlencoded")
    public Response racuni(@FormParam("komitentId") int kid){
        Client client = ClientBuilder.newClient();
        Response resp = client.target("http://localhost:8080/server/resources/service/fRacuni/" + kid)
                .request().get();
        
        return resp;
    }
    
    @POST
    @Path("transakcije")
    @Consumes("application/x-www-form-urlencoded")
    public Response transakcije(@FormParam("racunId") int rid){
        Client client = ClientBuilder.newClient();
        Response resp = client.target("http://localhost:8080/server/resources/service/fTransakcije/" + rid)
                .request().get();
        
        return resp;
    }
    
    
}
