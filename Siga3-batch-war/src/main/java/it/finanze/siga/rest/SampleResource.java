package it.finanze.siga.rest;

import jakarta.ejb.EJB;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import it.finanze.siga.ejb.SampleBeanLocal;

@Path("/sample")
public class SampleResource {

    @EJB
    private SampleBeanLocal sampleBean;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHello() {
        String message = sampleBean.greet();
        return Response.ok("{\"message\":\"" + message + "\"}").build();
    }
}
