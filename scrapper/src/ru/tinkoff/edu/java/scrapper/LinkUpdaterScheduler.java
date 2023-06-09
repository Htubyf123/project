package ru.tinkoff.edu.java.scrapper;

import org.springframework.jdbc.core.namedparam.ParsedSql;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ru.tinkoff.edu.java.bot.dto.LinkUpdate;
import main.ru.tinkoff.edu.java.link_parser.ParsedUrl;
import main.ru.tinkoff.edu.java.link_parser.NullURLParser;

import ru.tinkoff.edu.java.scrapper.database.dto.Link;
import ru.tinkoff.edu.java.scrapper.database.dto.Subscription;
import ru.tinkoff.edu.java.scrapper.service.update_handler.CommonUpdateHandler;
import ru.tinkoff.edu.java.scrapper.service.LinkUpdater;

import java.util.stream.Collectors;

@Component
public class LinkUpdaterScheduler {
    private final LinkUpdater linkUpdater;
    private final CommonUpdateHandler commonUpdateHandler;

    public LinkUpdaterScheduler(LinkUpdater linkUpdater, CommonUpdateHandler commonUpdateHandler) {
        this.linkUpdater = linkUpdater;
        this.commonUpdateHandler = commonUpdateHandler;
    }

    @Scheduled(fixedDelayString = "#{schedulerInterval}")
    public void update() {
        var updates = linkUpdater.getUpdates();
        var map = updates.stream().collect(Collectors.groupingBy(Subscription::link,
                Collectors.mapping(Subscription::chat, Collectors.toList())));
        for (var entry : map.entrySet()) {
            Link link = entry.getKey();
            var chats = entry.getValue();
            ParsedUrl object = NullURLParser.parse(link.url());
            commonUpdateHandler.handleUpdate(object, link, chats);
            linkUpdater.updateAll(updates.stream().map(Subscription::link).distinct().collect(Collectors.toList()));
        }
    }
}