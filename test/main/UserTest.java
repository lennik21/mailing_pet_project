package main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private static User user1;
    private static User user2;

    @BeforeEach
    void setup(){
        user1 = new User("Roma");
        user2 = new User("Nikita");
    }

    @Test
    public void testUserCreation(){
        User user = new User("Lenya");
        assertEquals("Lenya", user.getUserName());
        assertTrue(user.getInbox().isEmpty());
        assertTrue(user.getOutbox().isEmpty());
        assertTrue(user.getSpam().isEmpty());
        assertNull(user.getSpamFilter());
    }
    @Test
    public void testSendMessage(){
        user2.setSpamFilter(new SimpleSpamFilter());
        user1.sendMessage("Spam yo", "#Text", user2);
        user1.sendMessage("#Test", "#Text", user2);
        assertEquals(2, user1.getOutbox().size());
        assertEquals(1, user2.getInbox().size());
        assertEquals(1, user2.getSpam().size());
    }
}