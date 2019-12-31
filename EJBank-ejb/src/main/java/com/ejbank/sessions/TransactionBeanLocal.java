package com.ejbank.sessions;

import com.ejbank.entities.TransactionEntity;
import com.ejbank.payload.PayloadAccounts;
import com.ejbank.payload.PayloadTransaction;
import com.ejbank.payload.PayloadTransactionRequest;

import javax.ejb.Local;

@Local
public interface TransactionBeanLocal {
    PayloadTransaction previewTransaction(PayloadTransactionRequest payloadTransactionRequest);
}
