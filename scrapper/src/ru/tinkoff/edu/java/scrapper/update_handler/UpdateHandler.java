package ru.tinkoff.edu.java.scrapper.update_handler;

import main.java.ParsedUrl;
import ru.tinkoff.edu.java.scrapper.database.dto.Chat;
import ru.tinkoff.edu.java.scrapper.database.dto.Link;

import java.util.List;

public interface UpdateHandler {
    void handleUpdate(ParsedObject object, Link link, List<Chat> chats);
}