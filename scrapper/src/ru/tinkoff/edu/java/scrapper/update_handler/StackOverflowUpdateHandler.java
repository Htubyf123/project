package ru.tinkoff.edu.java.scrapper.update_handler;

import org.springframework.stereotype.Component;
import main.java.ParsedUrl;
import main.java.StackOverflowQuestion;
import ru.tinkoff.edu.java.scrapper.client.BotClient;
import ru.tinkoff.edu.java.scrapper.client.StackOverflowClient;
import ru.tinkoff.edu.java.scrapper.database.dto.Chat;
import ru.tinkoff.edu.java.scrapper.database.dto.Link;
import ru.tinkoff.edu.java.scrapper.dto.LinkUpdate;
import ru.tinkoff.edu.java.scrapper.dto.QuestionResponse;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Component
public class StackOverflowUpdateHandler implements UpdateHandler {
    private final BotClient botClient;
    private final StackOverflowClient stackOverflowClient;

    public StackOverflowUpdateHandler(BotClient botClient, StackOverflowClient stackOverflowClient) {
        this.botClient = botClient;
        this.stackOverflowClient = stackOverflowClient;
    }

    @Override
    public void handleUpdate(ParsedObject object, Link link, List<Chat> chats) {
        if (object instanceof StackOverflowQuestion question) {
            boolean scenario = false;
            QuestionResponse response = stackOverflowClient.fetchQuestion(question);
            var answers = response.answersTime();
            int countNewAnswers = 0;
            for (var answer : answers) {
                if (answer.compareTo(link.checkedAt()) > -1) {
                    countNewAnswers++;
                }
            }
            if (countNewAnswers > 0) {
                try {
                    botClient.update(new LinkUpdate(link.id(), new URI(link.url()), "На вопрос '".
                            concat(response.title()).concat("' ответили ").concat(String.valueOf(countNewAnswers)).
                            concat(getCorrectForm(countNewAnswers)), chats.stream().map(Chat::id).toList()));
                    scenario = true;
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            }
            if (response.closedAt() != null && response.closedAt().compareTo(link.checkedAt()) > -1) {
                try {
                    botClient.update(new LinkUpdate(link.id(), new URI(link.url()),
                            "Вопрос '".concat(response.title()).concat("' был закрыт "),
                            chats.stream().map(Chat::id).toList()));
                    scenario = true;
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            }
            if (response.updatedAt().compareTo(link.checkedAt()) > -1 && !scenario) {
                try {
                    botClient.update(new LinkUpdate(link.id(), new URI(link.url()),
                            "Произошло обновление вопроса '".concat(response.title()).concat("'"),
                            chats.stream().map(Chat::id).toList()));
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private String getCorrectForm(int x) {
        if (x % 10 >= 2 && x % 10 <= 4 && (x % 100 > 14 || x % 100 < 12)) {
            return " раза";
        }
        return " раз";
    }
}