package com.ejbank.api;

import com.ejbank.test.TestBeanLocal;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/test")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class Test {

    @EJB
    private TestBeanLocal testBean;
    
    @GET
    @Path("/ejb")
    public String testEJB() {
        return testBean.test();
    }
}
