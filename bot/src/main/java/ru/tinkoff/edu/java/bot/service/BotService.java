package ru.tinkoff.edu.java.bot.service;

import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.bot.pomogator2001_bot;
import ru.tinkoff.edu.java.bot.dto.LinkUpdate;

@Service
public class BotService {
    private final pomogator2001_bot pomogator2001Bot;

    public BotService(pomogator2001_bot pomogator2001Bot) {
        this.pomogator2001Bot = pomogator2001Bot;
    }

    public void sendUpdate(LinkUpdate linkUpdate) {
        for (long id : linkUpdate.tgChatIds()) {
            pomogator2001Bot.sendMessage(id, linkUpdate.description().concat(":\n").
                    concat(linkUpdate.url().toString()));
        }
    }
}