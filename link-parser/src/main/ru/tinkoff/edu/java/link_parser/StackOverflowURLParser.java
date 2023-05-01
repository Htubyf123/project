package main.ru.tinkoff.edu.java.link_parser;

public record StackOverflowURLParser() {
    public ParsedUrl parse(String url) {
        if (url.startsWith("https://stackoverflow.com/questions/")) {
            String[] parts = url.split("/");
            // make sure there are at least three parts (https:, , stackoverflow, questions)
            if (parts.length >= 4) {
                long question = Long.parseLong(parts[4]);
                return new StackOverflowQuestion(question);
            }
        }
        return null;
    }
}