package main;

import java.util.*;
public class Main {
    private final UserStorage userStorage = new UserStorage();
    private final Scanner sc = new Scanner(System.in);
    public void service(){
        System.out.println("Email Service activated, write 'quit' to end");
        while (true){
            System.out.print("> ");
            String command = sc.nextLine().trim().toLowerCase();

            switch (command){
                case "add":
                    doAddUser();
                    break;
                case "list":
                    doListUsers();
                    break;
                case "send":
                    doSendMessage();
                    break;
                case "inbox":
                    showInbox();
                    break;
                case "outbox":
                    showOutbox();
                    break;
                case "spam":
                    showSpam();
                    break;
                case "setfilter":
                    doSetFilter();
                    break;
                case "quit":
                    return;
                default:
                    System.out.println("Unknown command");
            }
        }
    }

    private void doAddUser(){
        System.out.print("Enter user name: ");
        String userName = sc.nextLine();

        if(userStorage.addUser(userName)){
            System.out.println("User '" + userName + "' added");
        } else {
            System.out.println("User '" + userName + "' is already exists");
        }
    }

    private void doListUsers(){
        List <User> users = userStorage.getAllUsers();
        if (users.isEmpty()){
            System.out.println("No users");
            return;
        }

        for (User user : users){
            System.out.println("* " + user.getUserName());
        }
        System.out.println("Total: " + users.size() + " users");

    }

    private void doSendMessage(){
        System.out.print("Enter sender name: ");
        String senderName = sc.nextLine();
        if (!userStorage.userExists(senderName)){
            System.out.println("The sender was not found");
            return;
        }
        User sender = userStorage.getUser(senderName);
        System.out.print("Enter receiver name: ");
        String receiverName = sc.nextLine();
        if (!userStorage.userExists(receiverName)){
            System.out.println("The receiver was not found");
            return;
        }
        User receiver = userStorage.getUser(receiverName);
        System.out.println("Caption:");
        String caption = sc.nextLine();

        System.out.println("Text:");
        String text = sc.nextLine();

        sender.sendMessage(caption, text, receiver);
        System.out.println("Message sent");
    }

    private void displayMessages(List<Message> messages){
        if (messages.isEmpty()){
            System.out.println("No messages");
            return;
        }

        for (Message message : messages){
            System.out.println("===================");
            System.out.println(message.getCaption());
            System.out.println(message.getText());

        }
        System.out.println("===================");
    }

    private void showInbox(){
        System.out.print("Enter user name: ");
        String userName = sc.nextLine();
        if (!userStorage.userExists(userName)){
            System.out.println("The user was not found");
            return;
        }
        User user = userStorage.getUser(userName);
        displayMessages(user.getInbox());
    }

    private void showOutbox(){
        System.out.print("Enter user name: ");
        String userName = sc.nextLine();
        if (!userStorage.userExists(userName)){
            System.out.println("The user was not found");
            return;
        }
        User user = userStorage.getUser(userName);
        displayMessages(user.getOutbox());
    }

    private void showSpam(){
        System.out.print("Enter user name: ");
        String userName = sc.nextLine();
        if (!userStorage.userExists(userName)){
            System.out.println("The user was not found");
            return;
        }
        User user = userStorage.getUser(userName);
        displayMessages(user.getSpam());
    }

    private void doSetFilter(){
        System.out.print("Enter user name: ");
        String userName = sc.nextLine();
        if (!userStorage.userExists(userName)){
            System.out.println("The user was not found");
            return;
        }
        User user = userStorage.getUser(userName);
        CompositeSpamFilter compositeFilter = new CompositeSpamFilter();

        while (true){
            System.out.print("Enter filter: ");
            String filterType = sc.nextLine().trim().toLowerCase();
            if (filterType.equals("done")){
                break;
            }
            SpamFilter filter = null;
            switch (filterType){
                case "simple":
                    filter = new SimpleSpamFilter();
                    break;
                case "keywords":
                    System.out.print("Enter keywords: ");
                    String keywords = sc.nextLine();
                    if (keywords.isEmpty() || !keywords.matches("[a-zA-Z0-9\\s]+")){
                        System.out.println("Error: Rewrite keywords");
                        break;
                    }
                    filter = new KeywordsSpamFilter(keywords);
                    break;
                case "sender":
                    System.out.print("Enter sender's names to block: ");
                    String senders = sc.nextLine();
                    filter = new SenderSpamFilter(senders);
                    break;
                case "repetition":
                    System.out.print("Enter maximum word repetition: ");
                    try {
                        int maxRepetitions = Integer.parseInt(sc.nextLine());
                        filter = new RepetitionSpamFilter(maxRepetitions);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: write integer");
                    }
                    break;
                default:
                    System.out.println("Unknown filter type");
            }
            if (filter != null){
                compositeFilter.addFilter(filter);
            }
        }
        user.setSpamFilter(compositeFilter);
        System.out.println("Filter is set");
    }

    public static void main(String[] args) {
        new Main().service();
    }
}