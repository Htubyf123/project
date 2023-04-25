package ru.tinkoff.edu.java.scrapper.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.tinkoff.edu.java.scrapper.client.BotClient;
import ru.tinkoff.edu.java.scrapper.client.GitHubClient;
import ru.tinkoff.edu.java.scrapper.client.ScrapperClient;
import ru.tinkoff.edu.java.scrapper.client.StackOverflowClient;

@Configuration
@EnableScheduling
public class ClientConfiguration {
    @Bean
    public BotClient botClient() {
        return new BotClient();
    }
    @Bean
    public GitHubClient gitHubClient() {
        return new GitHubClient();
    }

    @Bean
    public StackOverflowClient stackOverflowClient() {
        return new StackOverflowClient();
    }
    @Bean
    public ScrapperClient scrapperClient() {
        return new ScrapperClient();
    }


}
