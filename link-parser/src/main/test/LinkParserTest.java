package main.test;

import org.junit.jupiter.api.Test;
import main.java.GithubRepository;
import main.java.NullURLParser;
import main.java.StackOverflowQuestion;

import static org.junit.jupiter.api.Assertions.*;

public class LinkParserTest {
    @Test
    public void testValidGithubLinks() {
        var rep1 = NullURLParser.parse("https://github.com/Htubyf123/project");
        var rep2 = NullURLParser.parse("https://github.com/Htubyf123/project/dorghlgwg");
        var ans = new GithubRepository("Htubyf123", "project");
        assertEquals(ans, rep1);
        assertEquals(ans, rep2);
    }

    @Test
    public void testMissingPartsInGithubLinks() {
        var rep1 = NullURLParser.parse("https://github.com/Htubyf123");
        var rep2 = NullURLParser.parse("https://github.com");
        assertNull(rep1);
        assertNull(rep2);
    }

    @Test
    public void testValidStackOverflowLinks() {
        var q1 = NullURLParser.parse("https://stackoverflow.com/questions/123321");
        var q2 = NullURLParser.parse("https://stackoverflow.com/questions/123321/asdlaksd;lasd");
        var ans = new StackOverflowQuestion(123321);
        assertEquals(ans, q1);
        assertEquals(ans, q2);
    }

    @Test
    public void testMissingPartsInStackOverflowLinks() {
        var q1 = NullURLParser.parse("https://stackoverflow.com/questions/");
        var q2 = NullURLParser.parse("https://stackoverflow.com/");
        assertNull(q1);
        assertNull(q2);
    }

    @Test
    public void testMistakesInStackOverflowLinks() {
        var q1 = NullURLParser.parse("https://stackoverflow.com/questions/1234l");
        var q2 = NullURLParser.parse("https://stackoverflow.com/question/1234");
        assertNull(q1);
        assertNull(q2);
    }

    @Test
    public void testInvalidLinks() {
        var res1 = NullURLParser.parse("https://stackunderflow.com/questions/1234");
        var res2 = NullURLParser.parse("https://githabr.com/EuphoriaV/WeChat");
        var res3 = NullURLParser.parse ("not a link");
        assertNull(res1);
        assertNull(res2);
        assertNull(res3);
    }
}