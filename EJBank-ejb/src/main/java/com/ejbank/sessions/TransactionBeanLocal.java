package com.ejbank.sessions;

import com.ejbank.errors.TransactionException;
import com.ejbank.payload.*;

import javax.ejb.Local;

@Local
public interface TransactionBeanLocal {
    PayloadTransaction previewTransaction(PayloadTransactionRequest payloadTransactionRequest);
    PayloadResult commitTransaction(PayloadTransactionRequest payloadTransactionRequest);
    int getAllWaitingTransactions(int userId);
    PayloadTransactions listTransactions(int userId, int accountId, int offset);
    PayloadResult validateTransaction(PayloadTransactionValidationRequest payload);
}
