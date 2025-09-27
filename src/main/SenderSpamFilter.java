package main;

import java.util.*;
public class SenderSpamFilter implements SpamFilter {
    private final String sender;

    public SenderSpamFilter (String sender){
        this.sender = sender.trim();
    }
    @Override
    public boolean isSpam(Message message){
        return message.getSender().getUserName().equals(sender);
    }
}
