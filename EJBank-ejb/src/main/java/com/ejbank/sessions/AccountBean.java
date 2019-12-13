package com.ejbank.sessions;

import com.ejbank.entities.AccountEntity;
import com.ejbank.entities.AdvisorEntity;
import com.ejbank.entities.CustomerEntity;
import com.ejbank.entities.UserEntity;
import com.ejbank.payload.PayloadAccount;
import com.ejbank.payload.PayloadAccounts;
import com.ejbank.repositories.AccountRepository;
import com.ejbank.repositories.AdvisorRepository;
import com.ejbank.repositories.CustomerRepository;
import com.ejbank.repositories.UserRepository;

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

    @Override
    public PayloadAccounts accountsByUser(int userId) {
        List<PayloadAccount> accounts = new ArrayList<>();

        CustomerEntity customerEntity = customerRepository.findById(userId);
        if(customerEntity != null) {
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
        if(advisor != null) {
            for (CustomerEntity customer : advisor.getCustomers()) {
                for (AccountEntity account : customer.getAccounts()) {
                    accounts.add(new PayloadAccount(account.getId(), account.getAccountType().getName(), account.getBalance(), customer.getFirstname()+" "+customer.getLastname()));
                }
            }
            return new PayloadAccounts(accounts);
        } else {
            return new PayloadAccounts(accounts, "Vous n'êtes pas un conseiller");
        }
    }

    @Override
    public PayloadAccounts allAccounts(int userId) {
        List<PayloadAccount> accounts = new ArrayList<>();
        UserEntity user = userRepository.findById(userId);
        if (user.getType().equals("customer")) {
            return accountsByUser(userId);
        } else {
            return accountsByAdvisor(userId);
        }
    }

}
