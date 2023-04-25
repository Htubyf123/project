package ru.tinkoff.edu.java.scrapper.update_handler;

import org.springframework.stereotype.Component;
import main.java.GithubRepository;
import main.java.ParsedUrl;
import ru.tinkoff.edu.java.scrapper.client.BotClient;
import ru.tinkoff.edu.java.scrapper.client.GitHubClient;
import ru.tinkoff.edu.java.scrapper.database.dto.Chat;
import ru.tinkoff.edu.java.scrapper.database.dto.Link;
import ru.tinkoff.edu.java.scrapper.dto.LinkUpdate;
import ru.tinkoff.edu.java.scrapper.dto.RepositoryResponse;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Component
public class GitHubUpdateHandler implements UpdateHandler {
    private final GitHubClient gitHubClient;
    private final BotClient botClient;

    public GitHubUpdateHandler(GitHubClient gitHubClient, BotClient botClient) {
        this.gitHubClient = gitHubClient;
        this.botClient = botClient;
    }

    @Override
    public void handleUpdate(ParsedObject object, Link link, List<Chat> chats) {
        if (object instanceof GitHubRepository repo) {
            RepositoryResponse response = gitHubClient.fetchRepository(repo);
            if (response.pushedAt().compareTo(link.checkedAt()) > -1) {
                try {
                    botClient.update(new LinkUpdate(link.id(), new URI(link.url()),
                            "1 или более коммитов были запушены в репозиторий '".concat(response.fullName()).
                                    concat("'"), chats.stream().map(Chat::id).toList()));
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}