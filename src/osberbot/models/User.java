package osberbot.models;

import java.util.List;

/**
 * Created by Tititesouris on 27/09/2015.
 */
public class User {

    private int id;

    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static List<User> findAll() {
        return null;
    }

    public static List<User> findById() {
        return null;
    }

    public static List<User> findByName() {
        return null;
    }

    boolean insertUser(User user) {
        return false;
    }

    boolean updateUser(User user) {
        return false;
    }

    boolean deleteUser(User user) {
        return false;
    }

}
