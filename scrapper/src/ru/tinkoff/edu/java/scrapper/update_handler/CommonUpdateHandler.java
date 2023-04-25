package ru.tinkoff.edu.java.scrapper.update_handler;

import org.springframework.stereotype.Component;
import main.java.GithubRepository;
import main.java.ParsedUrl;
import main.java.StackOverflowQuestion;
import ru.tinkoff.edu.java.scrapper.database.dto.Chat;
import ru.tinkoff.edu.java.scrapper.database.dto.Link;

import java.util.List;

@Component
public class CommonUpdateHandler implements UpdateHandler {
    private final GitHubUpdateHandler gitHubUpdateHandler;
    private final StackOverflowUpdateHandler stackOverflowUpdateHandler;

    public CommonUpdateHandler(GitHubUpdateHandler gitHubUpdateHandler, StackOverflowUpdateHandler stackOverflowUpdateHandler) {
        this.gitHubUpdateHandler = gitHubUpdateHandler;
        this.stackOverflowUpdateHandler = stackOverflowUpdateHandler;
    }

    @Override
    public void handleUpdate(ParsedObject object, Link link, List<Chat> chats) {
        if (object instanceof GitHubRepository repo) {
            gitHubUpdateHandler.handleUpdate(repo, link, chats);
        } else if (object instanceof StackOverflowQuestion question) {
            stackOverflowUpdateHandler.handleUpdate(question, link, chats);
        }
    }
}