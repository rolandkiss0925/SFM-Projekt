package hu.unideb.inf.model;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JpaFoodDAO implements FoodDAO{


    public static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
    private static final EntityManager entityManager = entityManagerFactory.createEntityManager();

    public static EntityManager getEntityManager() {
        return entityManager;
    }
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
                "SELECT f.name FROM Food f", Food.class);
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
        //Különben hamarabb bezárja az EntityManagert
        //entityManager.close();
        //entityManagerFactory.close();
    }
    //Az  éttreremhez tartozó ételeklekérdezése
    public static List<String> getfood(String etteremname){
       // JpaFoodDAO.getEntityManager().getTransaction().begin();
       try {
           @SuppressWarnings("unchecked")
           TypedQuery<String> query = (TypedQuery<String>) entityManager.createQuery(
                   "select f.Food_name from Food f, Restaurant where from_restaurant = Rid AND type = 'ETEL' AND Rname = '" + etteremname + "'");
           return query.getResultList();
       }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    public static Collection<String> getdrink(String etteremname2) {
        try {
            @SuppressWarnings("unchecked")
            TypedQuery<String> query = (TypedQuery<String>) entityManager.createQuery(
                    "select f.Food_name from Food f, Restaurant where from_restaurant = Rid AND type = 'ITAL' AND Rname = '" + etteremname2 + "'");
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
        public static Collection<String> getkoret(String etteremname3) {
            try {
                @SuppressWarnings("unchecked")
                TypedQuery<String> query = (TypedQuery<String>) entityManager.createQuery(
                        "select f.Food_name from Food f, Restaurant where from_restaurant = Rid AND type = 'KORET' AND Rname = '" + etteremname3 + "'");
                return query.getResultList();
            }
            catch (Exception e) {
                e.printStackTrace();
                throw e;
            }


    }

}
