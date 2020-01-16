package com.ejbank.sessions;

import com.ejbank.payload.PayloadAccountFull;
import com.ejbank.payload.PayloadAccounts;

import javax.ejb.Local;

@Local
public interface AccountBeanLocal {
    PayloadAccounts accountsByUser(int userId);
    PayloadAccounts accountsByAdvisor(int userId);
    PayloadAccountFull accountByUser (int userId, int accountId);
    PayloadAccounts allAccounts(int userId);
}
