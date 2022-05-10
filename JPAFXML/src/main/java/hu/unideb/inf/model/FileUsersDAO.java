package hu.unideb.inf.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUsersDAO implements UsersDAO{
    private List<Users> usersList;

    public FileUsersDAO() {
        //deserialization
        try (FileInputStream fis = new FileInputStream("users.ser");
             ObjectInputStream ois = new ObjectInputStream(fis);){
            usersList = (List<Users>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            usersList = new ArrayList<>();
        }
    }

    /**
     * Serializes the list of animals
     */
    private void serialize(){
        try (FileOutputStream fos = new FileOutputStream("users.ser");
             ObjectOutputStream oos = new ObjectOutputStream(fos);){
            oos.writeObject(usersList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(Users u) {
        if (!usersList.contains(u)) usersList.add(u);
        serialize();
    }

    @Override
    public void deleteUser(Users u) {
        usersList.remove(u);
        serialize();
    }

    @Override
    public List<Users> getUsers() {
        return usersList;
    }

    @Override
    public void close() throws Exception {
        serialize();
    }
}
