package com.ejbank.sessions;

import com.ejbank.entities.AccountEntity;
import com.ejbank.payload.PayloadAccount;
import com.ejbank.payload.PayloadAccounts;

import javax.ejb.Local;
import java.util.List;

@Local
public interface AccountBeanLocal {
    PayloadAccounts accountsByUser(int userId);

    PayloadAccounts allAccountsByUser(int userId);
}
