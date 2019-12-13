package com.ejbank.api;

import com.ejbank.payload.PayloadTransactionRecap;
import com.ejbank.sessions.AccountBeanLocal;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/transaction")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class Transaction {
    @EJB
    private AccountBeanLocal accountBeanLocal ;

    @POST
    @Path("/preview")
    public String postPreview(PayloadTransactionRecap payloadTransactionRecap) {
        return null; //TODO
    }



}
