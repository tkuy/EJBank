package com.ejbank.sessions;

import com.ejbank.payload.PayloadAccounts;

import javax.ejb.Local;

@Local
public interface AccountBeanLocal {
    PayloadAccounts accountsByUser(int userId);
    PayloadAccounts accountsByAdvisor(int userId);

    PayloadAccounts allAccounts(int userId);
}
