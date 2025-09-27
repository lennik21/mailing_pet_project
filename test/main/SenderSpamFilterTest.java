package main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SenderSpamFilterTest {
    private static User user1;
    private static User user2;

    @BeforeEach
    void setup(){
        user1 = new User("Roma");
        user2 = new User("Nikita");
    }
    @Test
    public void testIsSpam(){
        User goodUser = new User("Goodman");
        SenderSpamFilter filter = new SenderSpamFilter(user1.getUserName());
        Message spam = new Message("Hi!", "#Text", user1, user2);
        Message goodMessage = new Message("Better call Saul!", "#Text", goodUser, user2);
        assertTrue(filter.isSpam(spam));
        assertFalse(filter.isSpam(goodMessage));
    }
}