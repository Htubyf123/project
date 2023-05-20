package ru.tinkoff.edu.java.scrapper.service.update_handler;

import main.ru.tinkoff.edu.java.link_parser.ParsedUrl;
import ru.tinkoff.edu.java.scrapper.database.dto.Chat;
import ru.tinkoff.edu.java.scrapper.database.dto.Link;

import java.util.List;

public interface UpdateHandler {
    void handleUpdate(ParsedUrl object, Link link, List<Chat> chats);
}