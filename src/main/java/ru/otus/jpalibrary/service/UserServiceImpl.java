package ru.otus.jpalibrary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.jpalibrary.domain.User;
import ru.otus.jpalibrary.repositories.UserRepository;
import ru.otus.jpalibrary.repositories.comm.ContactTypeEn;
import ru.otus.jpalibrary.security.UserInfo;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private UserInfo userInfo;
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserInfo userInfo, UserRepository userRepository) {
        this.userInfo = userInfo;
        this.userRepository = userRepository;
    }

    @Override
    public void connectBy(ContactTypeEn ct, String contactVal, String password) {
        if( ct == null )
            return;
        Optional<User> userOp = userRepository.getUserByContact(contactVal,ct.value());
        userOp.ifPresent( user -> {
            if(  password.equals("123"))
                userInfo.setUser(user);
        });
    }

    @Override
    public boolean isConnect() {
        return userInfo.isConnected();
    }

    @Override
    public User getUser() {
        return userInfo.getUser();
    }
}
