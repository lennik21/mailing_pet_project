package main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepetitionSpamFilterTest {
    private static User user1;
    private static User user2;

    @BeforeEach
    void setup(){
        user1 = new User("Roma");
        user2 = new User("Nikita");
    }
    @Test
    public void testIsSpam(){
        RepetitionSpamFilter filter = new RepetitionSpamFilter(3);
        Message spam1 = new Message("Priv", "#Text #Text #Text #Text", user1, user2);
        Message notSpam1 = new Message("Hi", "#Text #Text #Text", user1, user2);
        Message notSpam2 = new Message("#XVii", "#Text", user1, user2);
        assertTrue(filter.isSpam(spam1));
        assertFalse(filter.isSpam(notSpam1));
        assertFalse(filter.isSpam(notSpam2));
    }
}