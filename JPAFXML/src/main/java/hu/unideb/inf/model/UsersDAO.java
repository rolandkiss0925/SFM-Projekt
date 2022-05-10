package hu.unideb.inf.model;



import java.util.List;

public interface UsersDAO extends AutoCloseable{
    public void saveUser(Users u);
    public void deleteUser(Users u);
    public List<Users> getUsers();
}
