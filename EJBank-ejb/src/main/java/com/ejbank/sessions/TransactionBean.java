package com.ejbank.sessions;

import com.ejbank.entities.AccountEntity;
import com.ejbank.entities.TransactionEntity;
import com.ejbank.entities.UserEntity;
import com.ejbank.errors.DepositException;
import com.ejbank.errors.TransactionInsertionException;
import com.ejbank.errors.WithdrawException;
import com.ejbank.payload.*;
import com.ejbank.repositories.AccountRepository;
import com.ejbank.repositories.TransactionRepository;
import com.ejbank.repositories.UserRepository;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class TransactionBean implements TransactionBeanLocal, Serializable {

    private InitialContext ctx = new InitialContext();
    private UserTransaction tx = (UserTransaction) ctx.lookup("UserTransaction");
    private Logger logger = Logger.getLogger("TransactionBeanLogger");
    @Inject
    private TransactionRepository transactionRepository;
    @Inject
    private AccountRepository accountRepository;
    @Inject
    private UserRepository userRepository;

    public TransactionBean() throws NamingException {
    }

    @Override
    public PayloadTransaction previewTransaction(PayloadTransactionRequest payloadTransactionRequest) {
        AccountEntity src = accountRepository.findById(payloadTransactionRequest.getSource());
        AccountEntity dest = accountRepository.findById(payloadTransactionRequest.getDestination());
        double before = src.getBalance();
        double afterSource = src.getBalance() - payloadTransactionRequest.getAmount();
        boolean result = afterSource >= (-src.getAccountType().getOverdraft());
        double after = dest.getBalance() + payloadTransactionRequest.getAmount();
        String message = null;
        if(!result) {
            message = "Vous ne disposez pas d'un solde suffisant";
        }
        return new PayloadTransaction(result, (int) before, (int) after, message, null);
    }
    private TransactionEntity computeTransactionResponse(PayloadTransactionRequest payloadTransactionRequest) {
        boolean waitingValidation = payloadTransactionRequest.getAmount() > 1000;
        return new TransactionEntity(
                0,
                accountRepository.findById(payloadTransactionRequest.getSource()),
                accountRepository.findById(payloadTransactionRequest.getDestination()),
                userRepository.findById(payloadTransactionRequest.getAuthor()),
                payloadTransactionRequest.getAmount(),
                payloadTransactionRequest.getComment(),
                !waitingValidation,
                new java.sql.Date(new java.util.Date().getTime())
                );
    }
    @Override
    public PayloadResult commitTransaction(PayloadTransactionRequest payloadTransactionRequest) {
        logger.log(Level.INFO, "Commit");
        PayloadTransaction payloadTransaction = previewTransaction(payloadTransactionRequest);
        //enough money
        if(payloadTransaction.isResult()) {
            TransactionEntity transactionEntity = computeTransactionResponse(payloadTransactionRequest);
            logger.log(Level.INFO, transactionEntity.toString());
            if(transactionEntity.isApplied()) {
                logger.log(Level.INFO, "The transaction will be applied");
                AccountEntity src = accountRepository.findById(payloadTransactionRequest.getSource());
                AccountEntity dest = accountRepository.findById(payloadTransactionRequest.getDestination());
                return applyAndCreateTransaction(transactionEntity, src, dest, payloadTransactionRequest.getAmount(), payloadTransaction.getMessage());
            } else {
                try {
                    logger.log(Level.INFO, "The transaction will not be applied but only inserted");
                    createTransaction(transactionEntity);
                } catch (TransactionInsertionException e) {
                    return new PayloadResult(false, "La création de la transaction a échouée");
                }
            }
        }
        return new PayloadResult(payloadTransaction.isResult(), payloadTransaction.getMessage());
    }
    public PayloadResult validateTransaction(PayloadTransactionValidationRequest payload) {
        if(payload.isApprove()) {
            TransactionEntity transactionEntity = transactionRepository.findById(payload.getTransaction());
            transactionEntity.setApplied(true);
            return applyAndUpdateTransaction(
                    transactionEntity,
                    transactionEntity.getSrc(),
                    transactionEntity.getDest(),
                    transactionEntity.getAmount(),
                    "La transaction a été approuvée et réalisée avec succès.");
        } else {
            return new PayloadResult(false, "La transaction a été refusée par le conseiller.");
        }
    }
    private PayloadResult applyAndCreateTransaction(TransactionEntity transactionEntity, AccountEntity src, AccountEntity dest, double amount, String successfulMessage) {
        try {
            tx.begin();
            withdrawAmount(src, amount);
            depositAmount(dest, amount);
            createTransaction(transactionEntity);
            tx.commit();
        } catch (WithdrawException | DepositException | TransactionInsertionException e) {
            try {
                tx.rollback();
            } catch (SystemException ex) {
                return new PayloadResult(false, "La transaction n'a pas été effectuée correctement. Aucun transfert d'argent n'a été réalisé.");
            }
        } catch (HeuristicRollbackException | HeuristicMixedException | SystemException | NotSupportedException | RollbackException e) {
            return new PayloadResult(false, "Internal error, don't worry");
        }
        return new PayloadResult(true, successfulMessage);
    }
    private PayloadResult applyAndUpdateTransaction(TransactionEntity transactionEntity, AccountEntity src, AccountEntity dest, double amount, String successfulMessage) {
        try {
            tx.begin();
            withdrawAmount(src, amount);
            depositAmount(dest, amount);
            updateTransaction(transactionEntity);
            tx.commit();
        } catch (WithdrawException | DepositException | TransactionInsertionException e) {
            try {
                tx.rollback();
            } catch (SystemException ex) {
                return new PayloadResult(false, "L'opération a échoué suite à une erreur technique. Réessayez plus tard.");
            }
        } catch (HeuristicRollbackException | HeuristicMixedException | SystemException | NotSupportedException | RollbackException e) {
            return new PayloadResult(false, "Oh... Erreur technique.");
        }
        return new PayloadResult(true, successfulMessage);
    }


    private void createTransaction(TransactionEntity transactionEntity) throws TransactionInsertionException {
        try {
            logger.log(Level.INFO, "Insert transaction");
            transactionRepository.create(transactionEntity);
        } catch (Exception e) {
            throw new TransactionInsertionException();
        }
    }
    private void updateTransaction(TransactionEntity transactionEntity) throws TransactionInsertionException {
        try {
            logger.log(Level.INFO, "Update transaction");
            transactionRepository.update(transactionEntity);
        } catch (Exception e) {
            throw new TransactionInsertionException();
        }
    }
    private void withdrawAmount(AccountEntity account, double amount) throws WithdrawException {
        try {
            logger.log(Level.INFO, "Withdraw : " + amount);
            account.setBalance(account.getBalance() - amount);
            accountRepository.update(account);
        } catch (Exception exception) {
            throw new WithdrawException();
        }
    }

    private void depositAmount(AccountEntity account, double amount) throws DepositException {
        try {
            logger.log(Level.INFO, "deposit : " + amount);
            account.setBalance(account.getBalance() + amount);
            accountRepository.update(account);
        } catch (Exception e) {
            throw new DepositException();
        }
    }
    public int getAllWaitingTransactions(int userId) {
        UserEntity user = userRepository.findById(userId);
        int rep = 0;
        for (TransactionEntity transaction : user.getTransactions()) {
            if (!transaction.isApplied()) rep++;
        }
        return rep;
    }

    @Override
    public PayloadTransactions listTransactions(int userId, int accountId, int offset) {
        AccountEntity account = accountRepository.findById(accountId);
        List<PayloadTransactionFull> payload = new ArrayList<PayloadTransactionFull>();
        List<TransactionEntity> transactions = account.getTransactionsFrom();
        for (TransactionEntity transaction : transactions) {
            payload.add(new PayloadTransactionFull(
                    transaction.getId(),
                    transaction.getDate(),
                    transaction.getSrc().getAccountType().getName(),
                    transaction.getDest().getAccountType().getName()    ,
                    userRepository.findById(transaction.getDest().getCustomerId()).getFormattedName(),
                    transaction.getAmount(),
                    transaction.getAuthor().getFormattedName(),
                    transaction.getComment(),
                    transaction.isApplied() ? "APPLYED" : "TO_APPROVE"
            ));
        }
        return new PayloadTransactions(transactions.size(), payload);
    }


}
