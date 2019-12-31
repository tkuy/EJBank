package com.ejbank.api;

import com.ejbank.payload.PayloadUser;
import com.ejbank.sessions.UserBeanLocal;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class UserInfo {

    @EJB
    private UserBeanLocal userBean ;

    @GET
    @Path("/{id}")
    public PayloadUser getUserInfo(@PathParam("id") int id) {
        return userBean.namesByUserId(id);
    }
    @GET
    @Path("/test")
    public String getUser() {
        return "Response";
    }
}
