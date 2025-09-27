package main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompositeSpamFilterTest {
    private static User user1;
    private static User user2;

    @BeforeEach
    void setup(){
        user1 = new User("Roma");
        user2 = new User("Nikita");
    }
    @Test
    public void testIsSpam(){
        CompositeSpamFilter composite = new CompositeSpamFilter();
        User goodUser = new User("Goodman");
        composite.addFilter(new SimpleSpamFilter());
        composite.addFilter(new SenderSpamFilter(goodUser.getUserName()));
        Message spam1 = new Message("YOOOOO", "#Text spam", user1, user2);
        Message spam2 = new Message("Hello again", "#Text", goodUser, user2);
        Message notSpam = new Message("#Blood", "#Text", user1, user2);
        assertTrue(composite.isSpam(spam1));
        assertTrue(composite.isSpam(spam2));
        assertFalse(composite.isSpam(notSpam));
    }
}