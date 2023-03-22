public sealed interface SiteParser permits GitHubURLParser, StackOverflowURLParser {
     ParsedUrl parse(String url);
}


public record NullURLParser {
    private static GitHubURLParser gitHubParser;
    private static StackOverflowURLParser stackOverflowParser;

    public NullURLParser() {
        gitHubParser = new GitHubURLParser();
        stackOverflowParser = new StackOverflowURLParser();
    }

    public ParsedUrl parse(String url) {
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
public record GitHubURLParser() implements SiteParser {
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
                return new ParsedUrl (user, repo, "");
            }
        }
        // if it's not a GitHub URL, return null
        return null;
    }
}

public record StackOverflowURLParser() implements SiteParser {
    public ParsedUrl parse(String url) {
        if (url.startsWith("https://stackoverflow.com/questions/")) {
            String[] parts = url.split("/");
            // make sure there are at least three parts (https:, , stackoverflow, questions)
            if (parts.length >= 4) {
                String question = parts[4];
                return new ParsedUrl(question);
            }
        }
        return null;
    }
}




