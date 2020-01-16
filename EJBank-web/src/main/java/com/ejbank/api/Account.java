package com.ejbank.api;

import com.ejbank.payload.PayloadAccountFull;
import com.ejbank.payload.PayloadAccounts;
import com.ejbank.sessions.AccountBeanLocal;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/account")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class Account {
    @EJB
    private AccountBeanLocal accountBeanLocal ;

    @GET
    @Path("/{account_id}/{user_id}")
    public PayloadAccountFull accountDetail(@PathParam("user_id") int userId, @PathParam("account_id") int accountId) {
        return accountBeanLocal.accountByUser(userId, accountId);
    }

}
