package hu.unideb.inf.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class JpaFoodDAO implements FoodDAO{

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public void saveFood(Food f) {
        entityManager.getTransaction().begin();
        entityManager.persist(f);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteFood(Food f) {
        entityManager.getTransaction().begin();
        entityManager.remove(f);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateFood(Food f) {
        /*entityManager.getTransaction().begin();
        entityManager.persist(a);
        entityManager.getTransaction().commit();*/
        saveFood(f);
    }

    @Override
    public List<Food> getFoods() {
        TypedQuery<Food> query = entityManager.createQuery(
                "SELECT f FROM Food f", Food.class);
        List<Food> foods = query.getResultList();
        return foods;
    }

    @Override
    public void saveRestaurant(Restaurant r) {
        entityManager.getTransaction().begin();
        entityManager.persist(r);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Restaurant> getRestaurants(){
        TypedQuery<Restaurant> query = entityManager.createQuery(
                "SELECT r FROM Restaurant r", Restaurant.class);
        List<Restaurant> restaurants = query.getResultList();
        return restaurants;
    }

    @Override
    public void close() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }
}
