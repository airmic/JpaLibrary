package ru.otus.jpalibrary.service;

import ru.otus.jpalibrary.domain.User;
import ru.otus.jpalibrary.repositories.comm.ContactTypeEn;

public interface UserService {
    void connectBy(ContactTypeEn ct, String contactVal, String password);
    boolean isConnect();
    User getUser();
}
