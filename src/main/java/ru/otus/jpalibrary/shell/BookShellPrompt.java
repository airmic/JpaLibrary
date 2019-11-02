package ru.otus.jpalibrary.shell;


import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;
import ru.otus.jpalibrary.security.UserInfo;

import java.util.Optional;

@Component
public class BookShellPrompt implements PromptProvider {

    private UserInfo userInfo;

    @Autowired
    public BookShellPrompt(UserInfo userInfo) {
        this.userInfo =userInfo;
    }

    @Override
    public AttributedString getPrompt() {
        if( userInfo.isConnected() )
            return new AttributedString(userInfo.getNick() + ":>",
                    AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW));
        else
            return new AttributedString("not logged in:>",
                    AttributedStyle.DEFAULT.foreground(AttributedStyle.RED));
    }
}
