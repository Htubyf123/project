package ru.tinkoff.edu.java.bot.test;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.MessageEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.tinkoff.edu.java.bot.pomogator2001_bot;

import static org.junit.jupiter.api.Assertions.*;

public class BotTest {
    private static pomogator2001_bot bot;
    private static long testChatId;

    @BeforeAll
    public static void init() {
        bot = new pomogator2001_bot ("5686265283:AAFiHvfNcGjFNOXzsQDvSB-FIBYA20KQ500");
        testChatId = 795253839;
    }

    @Test
    public void testList() {
        Message message = bot.list(testChatId).message();
        assertEquals(message.text(), "Пока что отсутствуют отслеживаемые ссылки");
        assertEquals(message.entities()[0].type(), MessageEntity.Type.italic);
    }

    @Test
    public void testInvalidCommand() {
        Message message = bot.handleInvalidMessage(testChatId, "abacabadabacaba").message();
        assertEquals(message.text(), "Неизвестная команда: abacabadabacaba");
        assertEquals(message.entities()[0].type(), MessageEntity.Type.italic);
    }
}