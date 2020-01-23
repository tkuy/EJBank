package com.ejbank.sessions;

import com.ejbank.entities.*;
import com.ejbank.payload.PayloadAccount;
import com.ejbank.payload.PayloadAccountFull;
import com.ejbank.payload.PayloadAccounts;
import com.ejbank.repositories.*;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Local
public class AccountBean implements AccountBeanLocal {
    @Inject
    private CustomerRepository customerRepository;
    @Inject
    private AdvisorRepository advisorRepository;
    @Inject
    private UserRepository userRepository;
    @Inject
    private AccountRepository accountRepository;
    @Inject
    private TransactionRepository transactionRepository;

    @Override
    public PayloadAccounts accountsByUser(int userId) {
        List<PayloadAccount> accounts = new ArrayList<>();

        CustomerEntity customerEntity = customerRepository.findById(userId);
        if (customerEntity != null) {
            for (AccountEntity accountEntity : customerEntity.getAccounts()) {
                accounts.add(new PayloadAccount(accountEntity.getId(), accountEntity.getAccountType().getName(), accountEntity.getBalance(), customerEntity.getFirstname() + " " + customerEntity.getLastname()));
            }
            return new PayloadAccounts(accounts);
        } else {
            return new PayloadAccounts(accounts, "Vous n'êtes pas un client");
        }
    }

    @Override
    public PayloadAccounts accountsByAdvisor(int userId) {
        List<PayloadAccount> accounts = new ArrayList<>();
        AdvisorEntity advisor = advisorRepository.findById(userId);
        if (advisor != null) {
            for (CustomerEntity customer : advisor.getCustomers()) {
                for (AccountEntity account : customer.getAccounts()) {
                    int cpt = 0;
                    for (TransactionEntity transactionEntity : account.getTransactionsFrom()) {
                        if(!transactionEntity.isApplied()) {
                            cpt++;
                        }
                    }
                    accounts.add(new PayloadAccount(account.getId(), account.getAccountType().getName(), account.getBalance(), customer.getFirstname() + " " + customer.getLastname(), cpt));
                }
            }
            return new PayloadAccounts(accounts);
        } else {
            return new PayloadAccounts(accounts, "Vous n'êtes pas un conseiller");
        }
    }

    @Override
    public PayloadAccountFull accountByUser(int userId, int accountId) {
        CustomerEntity customerEntity = customerRepository.findById(userId);
        AccountEntity accountEntity = accountRepository.findById(accountId);
        if (customerEntity == null) { return new PayloadAccountFull("Vous n'êtes pas un client"); }
        if (accountEntity == null) { return new PayloadAccountFull("Ceci n'est pas un compte"); }
        if (!customerEntity.getAccounts().contains(accountEntity)) { return new PayloadAccountFull("Ce compte ne vous est pas accessible"); }

        AdvisorEntity advisor = advisorRepository.findById(customerEntity.getAdvisorId());
        AccountTypeEntity accountType = accountEntity.getAccountType();
        return new PayloadAccountFull(customerEntity.getFormattedName(), advisor.getFormattedName(), accountType.getRate(), accountEntity.getBalance()*(accountType.getRate()/100), accountEntity.getBalance());

}

    @Override
    public PayloadAccounts allAccounts(int userId) {
        UserEntity user = userRepository.findById(userId);
        if (user.getType().equals("customer")) {
            return accountsByUser(userId);
        } else {
            return accountsByAdvisor(userId);
        }
    }
}
