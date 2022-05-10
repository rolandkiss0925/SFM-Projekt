package hu.unideb.inf.model;

import org.h2.engine.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class JpaUsersDAO implements UsersDAO{

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public void saveUser(Users u) {
        entityManager.getTransaction().begin();
        entityManager.persist(u);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteUser(Users u) {
        entityManager.getTransaction().begin();
        entityManager.remove(u);
        entityManager.getTransaction().commit();
    }


    @Override
    public List<Users> getUsers() {
        TypedQuery<Users> query = entityManager.createQuery(
                "SELECT u FROM Users u", Users.class);
        List<Users> users = query.getResultList();
        return users;
    }

    @Override
    public void close() throws Exception {

    }
}
