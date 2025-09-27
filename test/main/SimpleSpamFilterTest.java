package main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class SimpleSpamFilterTest {
    private static User user1;
    private static User user2;

    @BeforeEach
    void setup(){
        user1 = new User("Roma");
        user2 = new User("Nikita");
    }
    @Test
    public void testIsSpam(){
        SimpleSpamFilter filter = new SimpleSpamFilter();
        Message spam1 = new Message("SPAM YO", "#Text", user1, user2);
        Message spam2 = new Message("PLEASE TRUST", "#Text SPAM", user1, user2);
        Message notSpam = new Message("Privet", "#Text", user1, user2);

        assertTrue(filter.isSpam(spam1));
        assertTrue(filter.isSpam(spam2));
        assertFalse(filter.isSpam(notSpam));
    }
}