package main.ru.tinkoff.edu.java.link_parser;

public record GithubRepository (String user, String repository) implements ParsedUrl {
}
