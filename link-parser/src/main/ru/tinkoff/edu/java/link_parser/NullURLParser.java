package main.ru.tinkoff.edu.java.link_parser;

public record NullURLParser() {
    private static GitHubURLParser gitHubParser;
    private static StackOverflowURLParser stackOverflowParser;

    public NullURLParser() {
        gitHubParser = new GitHubURLParser();
        stackOverflowParser = new StackOverflowURLParser();
    }

    public static ParsedUrl parse(String url) {
        ParsedUrl result = null;
        // try the GitHub parser
        result = gitHubParser.parse(url);
        // if it didn't work, try the StackOverflow parser
        if (result == null) {
            result = stackOverflowParser.parse(url);
        }
        return result;
    }
}