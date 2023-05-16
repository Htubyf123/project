package test;

import org.junit.jupiter.api.Test;
import ru.tinkoff.edu.java.link_parser.GitHubRepository;
import ru.tinkoff.edu.java.link_parser.LinkParser;
import ru.tinkoff.edu.java.link_parser.StackOverflowQuestion;

import static org.junit.jupiter.api.Assertions.*;

public class LinkParserTest {
    @Test
    public void testValidGithubLinks() {
        var rep1 = LinkParser.parseLink("https://github.com/Htubyf123/project");
        var rep2 = LinkParser.parseLink("https://github.com/Htubyf123/project/dorghlgwg");
        var ans = new GitHubRepository("Htubyf123", "project");
        assertEquals(ans, rep1);
        assertEquals(ans, rep2);
    }

    @Test
    public void testMissingPartsInGithubLinks() {
        var rep1 = LinkParser.parseLink("https://github.com/Htubyf123");
        var rep2 = LinkParser.parseLink("https://github.com");
        assertNull(rep1);
        assertNull(rep2);
    }

    @Test
    public void testValidStackOverflowLinks() {
        var q1 = LinkParser.parseLink("https://stackoverflow.com/questions/123321");
        var q2 = LinkParser.parseLink("https://stackoverflow.com/questions/123321/asdlaksd;lasd");
        var ans = new StackOverflowQuestion(123321);
        assertEquals(ans, q1);
        assertEquals(ans, q2);
    }

    @Test
    public void testMissingPartsInStackOverflowLinks() {
        var q1 = LinkParser.parseLink("https://stackoverflow.com/questions/");
        var q2 = LinkParser.parseLink("https://stackoverflow.com/");
        assertNull(q1);
        assertNull(q2);
    }

    @Test
    public void testMistakesInStackOverflowLinks() {
        var q1 = LinkParser.parseLink("https://stackoverflow.com/questions/1234l");
        var q2 = LinkParser.parseLink("https://stackoverflow.com/question/1234");
        assertNull(q1);
        assertNull(q2);
    }

    @Test
    public void testInvalidLinks() {
        var res1 =LinkParser.parseLink("https://stackunderflow.com/questions/1234");
        var res2 = LinkParser.parseLink("https://githabr.com/EuphoriaV/WeChat");
        var res3 =LinkParser.parseLink ("not a link");
        assertNull(res1);
        assertNull(res2);
        assertNull(res3);
    }
}