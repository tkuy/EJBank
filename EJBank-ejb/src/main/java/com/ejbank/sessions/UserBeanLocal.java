package com.ejbank.sessions;

import com.ejbank.payload.PayloadUser;

import javax.ejb.Local;

@Local
public interface UserBeanLocal {

    PayloadUser namesByUserId(int id);
}
