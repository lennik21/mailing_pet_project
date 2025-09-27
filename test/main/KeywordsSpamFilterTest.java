package main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KeywordsSpamFilterTest {
    private static User user1;
    private static User user2;

    @BeforeEach
    void setup(){
        user1 = new User("Roma");
        user2 = new User("Nikita");
    }

    @Test
    public void testIsSpam(){
        KeywordsSpamFilter filter = new KeywordsSpamFilter("management SWOT");
        Message spam1 = new Message("Attention", "#Text MANAGEMENT", user1, user2);
        Message spam2 = new Message("Do swot", "#Text", user1, user2);
        Message notSpam = new Message("#812", "#Text", user1, user2);
        assertTrue(filter.isSpam(spam1));
        assertTrue(filter.isSpam(spam2));
        assertFalse(filter.isSpam(notSpam));
    }
}