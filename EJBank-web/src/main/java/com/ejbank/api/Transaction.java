package com.ejbank.api;

import com.ejbank.payload.PayloadTransaction;
import com.ejbank.payload.PayloadTransactionRequest;
import com.ejbank.sessions.TransactionBeanLocal;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/transaction")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class Transaction {
    @EJB
    private TransactionBeanLocal transactionBeanLocal;

    @POST
    @Path("/preview")
    @Consumes(MediaType.APPLICATION_JSON)
    public PayloadTransaction postPreview(PayloadTransactionRequest payloadTransactionRequest) {
        return transactionBeanLocal.previewTransaction(payloadTransactionRequest);
    }

}
