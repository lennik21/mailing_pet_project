package main;

import java.util.List;
import java.util.ArrayList;
public class User {
    private final String userName;
    private final List<Message> inbox = new ArrayList<>();
    private final List<Message> outbox = new ArrayList<>();
    private final List<Message> spam = new ArrayList<>();
    private SpamFilter spamFilter;

    public User(String userName){
        this.userName = userName;
    }

    void sendMessage(String caption, String text, User receiver){
        Message message = new Message(caption, text, this, receiver);
        outbox.add(message);
        if (receiver.getSpamFilter() != null && receiver.getSpamFilter().isSpam(message)) {
            receiver.spam.add(message);
        } else {
            receiver.inbox.add(message);
        }
    }

    public String getUserName(){
        return userName;
    }
    public List<Message> getInbox(){
        return inbox;
    }
    public List<Message> getOutbox(){
        return outbox;
    }
    public List<Message> getSpam(){
        return spam;
    }
    public SpamFilter getSpamFilter(){
        return spamFilter;
    }

    public void setSpamFilter(SpamFilter spamFilter){
        this.spamFilter = spamFilter;
    }
}
