package com.ejbank.api;

import com.ejbank.payload.PayloadResult;
import com.ejbank.payload.PayloadTransaction;
import com.ejbank.payload.PayloadTransactionRequest;
import com.ejbank.sessions.TransactionBeanLocal;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public PayloadResult postTransaction(PayloadTransactionRequest payloadTransactionRequest) {
        return transactionBeanLocal.commitTransaction(payloadTransactionRequest);
    }


}
