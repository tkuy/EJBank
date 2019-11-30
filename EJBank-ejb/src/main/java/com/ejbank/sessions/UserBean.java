package com.ejbank.sessions;

import com.ejbank.repositories.UserRepository;
import com.ejbank.entities.UserEntity;
import com.ejbank.payload.PayloadUser;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
@Local
public class UserBean implements UserBeanLocal {
    @Inject
    private UserRepository userRepository;
    @Override
    public PayloadUser namesByUserId(int id) {
        //Call UserRepository to get the user
        UserEntity userEntity = userRepository.findById(id);
        return new PayloadUser(userEntity.getFirstname(), userEntity.getLastname());
    }
}
