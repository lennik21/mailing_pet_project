package main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UserStorageTest {
    private static User user1;
    private static User user2;

    @BeforeEach
    void setup(){
        user1 = new User("Roma");
        user2 = new User("Nikita");
    }

    @Test
    public void testAddUser(){
        UserStorage storage = new UserStorage();
        assertTrue(storage.addUser(user1.getUserName()));
        assertFalse(storage.addUser(user1.getUserName()));
    }
    @Test
    public void testGetUser(){
        UserStorage storage = new UserStorage();
        storage.addUser(user1.getUserName());
        assertNotNull(storage.getUser(user1.getUserName()));
        assertNull(storage.getUser(user2.getUserName()));
    }
    @Test
    void testUserExists() {
        UserStorage storage = new UserStorage();
        storage.addUser(user1.getUserName());
        assertTrue(storage.userExists(user1.getUserName()));
        assertFalse(storage.userExists(user2.getUserName()));
    }
    @Test
    void testGetAllUsers(){
        UserStorage storage = new UserStorage();
        storage.addUser(user1.getUserName());
        storage.addUser(user2.getUserName());
        List<User> users = storage.getAllUsers();
        assertEquals(user1.getUserName(), users.get(1).getUserName());
        assertEquals(user2.getUserName(), users.get(0).getUserName());
    }
}