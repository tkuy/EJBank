package com.ejbank.sessions;

import com.ejbank.entities.AccountEntity;
import com.ejbank.payload.PayloadAccount;
import com.ejbank.payload.PayloadAccounts;
import com.ejbank.repositories.AccountRepository;
import com.ejbank.repositories.CustomerRepository;

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

    @Override
    public PayloadAccounts accountsByUser(int userId) {
        List<PayloadAccount> accounts = new ArrayList<>();
        for (AccountEntity accountEntity : customerRepository.findByUser(userId)) {
            accounts.add(new PayloadAccount(accountEntity.getId(), accountEntity.getAccountType().getName(), accountEntity.getBalance()));
        }
//        return customerRepository.findByUser(userId).stream().map(account -> new PayloadAccount(account.getId(), account.getAccountType().getName(), account.getBalance())).collect(Collectors.toList());
        return new PayloadAccounts(accounts);
    }
}
