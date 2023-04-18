package main.java;
public record StackOverflowURLParser() {
    public ParsedUrl parse(String url) {
        if (url.startsWith("https://stackoverflow.com/questions/")) {
            String[] parts = url.split("/");
            // make sure there are at least three parts (https:, , stackoverflow, questions)
            if (parts.length >= 4) {
                String question = parts[4];
                return new StackOverflowQuestion(question);
            }
        }
        return null;
    }
}