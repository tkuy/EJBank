package com.ejbank.api;

import com.ejbank.payload.PayloadAccounts;
import com.ejbank.sessions.AccountBeanLocal;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/accounts")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class Accounts {
    @EJB
    private AccountBeanLocal accountBeanLocal ;

    @GET
    @Path("/{user_id}")
    public PayloadAccounts getUserInfo(@PathParam("user_id") int id) {
        return accountBeanLocal.accountsByUser(id);
    }

    @GET
    @Path("/test")
    public String getUser() {
        return "Response";
    }

    @GET
    @Path("/attached/{user_id}")
    public PayloadAccounts getAllUserAccounts(@PathParam("user_id") int id) {
        return accountBeanLocal.accountsByAdvisor(id);
    }

    @GET
    @Path("/all/{user_id}")
    public PayloadAccounts getAllAccounts(@PathParam("user_id") int id) {
        return accountBeanLocal.allAccounts(id);
    }


}
