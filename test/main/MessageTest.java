package main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {
    private static User sender;
    private static User receiver;

    @BeforeEach
    void setup(){
        sender = new User("Roma");
        receiver = new User("Nikita");
    }
    @Test
    public void testMessageCreation(){
        Message message = new Message("#Test", "#Text", sender, receiver);
        assertEquals("#Test", message.getCaption());
        assertEquals("#Text", message.getText());
        assertEquals(sender, message.getSender());
        assertEquals(receiver, message.getReceiver());
    }
}