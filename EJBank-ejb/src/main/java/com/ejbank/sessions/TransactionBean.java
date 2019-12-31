package com.ejbank.sessions;

import com.ejbank.entities.AccountEntity;
import com.ejbank.payload.PayloadTransaction;
import com.ejbank.payload.PayloadTransactionRequest;
import com.ejbank.repositories.AccountRepository;
import com.ejbank.repositories.TransactionRepository;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
@Stateless
@Local
public class TransactionBean implements TransactionBeanLocal {
    @Inject
    private TransactionRepository transactionRepository;
    @Inject
    private AccountRepository accountRepository;

    @Override
    public PayloadTransaction previewTransaction(PayloadTransactionRequest payloadTransactionRequest) {
        AccountEntity src = accountRepository.findById(payloadTransactionRequest.getSource());
        double before = src.getBalance();
        double after = src.getBalance() - payloadTransactionRequest.getAmount();
        String message = null;
        boolean result = after >= (-src.getAccountType().getOverdraft());
        if(!result) {
            message = "Vous ne disposez pas d'un solde suffisant";
        }
        return new PayloadTransaction(result, (int) before, (int) after, message, null);
    }
}
