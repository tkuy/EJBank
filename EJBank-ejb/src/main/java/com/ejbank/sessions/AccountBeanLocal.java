package com.ejbank.sessions;

import com.ejbank.entities.AccountEntity;
import com.ejbank.payload.PayloadAccount;

import javax.ejb.Local;
import java.util.List;

@Local
public interface AccountBeanLocal {
    List<PayloadAccount> accountsByUser(int userId);
}
