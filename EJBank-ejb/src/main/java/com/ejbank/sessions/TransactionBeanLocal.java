package com.ejbank.sessions;

import com.ejbank.errors.TransactionException;
import com.ejbank.payload.PayloadResult;
import com.ejbank.payload.PayloadTransaction;
import com.ejbank.payload.PayloadTransactionRequest;

import javax.ejb.Local;

@Local
public interface TransactionBeanLocal {
    PayloadTransaction previewTransaction(PayloadTransactionRequest payloadTransactionRequest);
    PayloadResult commitTransaction(PayloadTransactionRequest payloadTransactionRequest);
}
