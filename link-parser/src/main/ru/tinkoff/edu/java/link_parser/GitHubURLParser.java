package main.ru.tinkoff.edu.java.link_parser;

public record GitHubURLParser() {

    public ParsedUrl parse(String url) {
        if (url.startsWith("https://github.com/")) {
            String[] parts = url.split("/");
            // make sure there are at least three parts (https:, , github.com)
            if (parts.length >= 3) {
                String user = parts[3];
                String repo = "";
                if (parts.length >= 5) {
                    repo = parts[4];
                }
                return new GithubRepository (user, repo);
            }
        }
        // if it's not a GitHub URL, return null
        return null;
    }
}