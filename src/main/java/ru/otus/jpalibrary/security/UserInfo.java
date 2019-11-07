package ru.otus.jpalibrary.security;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.jpalibrary.domain.User;

import java.util.Optional;
import java.util.function.Supplier;


@Component
@NoArgsConstructor
public class UserInfo {
    private Optional<User> ouser;

    public boolean isConnected() {
        if( ouser == null )
            return false;
        return ouser.isPresent();
    }

    public void setUser(User user) {
        this.ouser = Optional.ofNullable(user);
    }

    public String getNick() {
        return (ouser.orElseThrow((Supplier<? extends RuntimeException>)() -> {
            throw new RuntimeException("Текущий пользователь не установлен");
        })).getNick();
    }

    public User getUser() {
        return ouser.orElseThrow((Supplier<? extends RuntimeException>)() -> {
            throw new RuntimeException("Текущий пользователь не установлен");
        });
    }
}
