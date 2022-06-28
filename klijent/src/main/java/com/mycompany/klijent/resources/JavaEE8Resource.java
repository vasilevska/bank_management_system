package com.mycompany.klijent.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

/**
 *
 * @author 
 */
@Path("test")
public class JavaEE8Resource {
    
    @GET
    public Response ping(){
        Client client = ClientBuilder.newClient();
        Response resp = client.target("http://localhost:8080/server/resources/service")
                .request().get();
        
        return resp;
    }
    
}
