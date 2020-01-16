package com.ejbank.api;

import com.ejbank.payload.*;
import com.ejbank.sessions.TransactionBean;
import com.ejbank.sessions.TransactionBeanLocal;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
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

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public PayloadResult postTransaction(PayloadTransactionRequest payloadTransactionRequest) {
        return transactionBeanLocal.commitTransaction(payloadTransactionRequest);
    }

    @GET
    @Path("/validation/notification/{user_id}")
    public int validationNumber(@PathParam("user_id") int id) {
        return transactionBeanLocal.getAllWaitingTransactions(id);
    }

    @GET
    @Path("/list/{account_id}/{offset}/{user_id}")
    public PayloadTransactions listTransations(@PathParam("user_id") int userId, @PathParam("account_id") int accountId, @PathParam("offset") int offset) {
        return transactionBeanLocal.listTransactions(userId, accountId, offset);
    }

    @POST
    @Path("/validation")
    @Consumes(MediaType.APPLICATION_JSON)
    public PayloadResult validateTransaction(PayloadTransactionValidationRequest payload) {
        System.out.println("Validate Transaction");
        return transactionBeanLocal.validateTransaction(payload);
    }



}
