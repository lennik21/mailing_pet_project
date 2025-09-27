package main;

import java.util.*;
public class UserStorage {
    private final Map<String, User> users = new HashMap<>();

    public boolean addUser(String userName){
        if (users.containsKey(userName)){
            return false;
        }
        users.put(userName, new User(userName));
        return true;
    }

    public User getUser(String userName){
        return users.get(userName);
    }

    public boolean userExists(String userName){
        return users.containsKey(userName);
    }

    public List<User> getAllUsers(){
        return new ArrayList<>(users.values());
    }
}
