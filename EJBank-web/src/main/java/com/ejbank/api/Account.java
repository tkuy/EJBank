package com.ejbank.api;

import com.ejbank.payload.PayloadAccount;
import com.ejbank.payload.PayloadUser;
import com.ejbank.sessions.AccountBean;
import com.ejbank.sessions.AccountBeanLocal;
import com.ejbank.sessions.UserBeanLocal;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/accounts")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class Account {

    @EJB
    private AccountBeanLocal accountBeanLocal ;

    @GET
    @Path("/{user_id}")
    public List<PayloadAccount> getUserInfo(@PathParam("user_id") int id) {
        return accountBeanLocal.accountsByUser(id);
    }
    @GET
    @Path("/test")
    public String getUser() {
        return "Response";
    }
}
