package hu.unideb.inf.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class JpaAnimalDAO implements AnimalDAO{

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public void saveAnimal(Animal a) {
        entityManager.getTransaction().begin();
        entityManager.persist(a);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteAnimal(Animal a) {
        entityManager.getTransaction().begin();
        entityManager.remove(a);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateAnimal(Animal a) {
        /*entityManager.getTransaction().begin();
        entityManager.persist(a);
        entityManager.getTransaction().commit();*/
        saveAnimal(a);
    }

    @Override
    public List<Animal> getAnimals() {
        TypedQuery<Animal> query = entityManager.createQuery(
                "SELECT a FROM Animal a", Animal.class);
        List<Animal> animals = query.getResultList();
        return animals;
    }

    @Override
    public void saveZoo(Zoo zoo) {
        entityManager.getTransaction().begin();
        entityManager.persist(zoo);
        entityManager.getTransaction().commit();
    }

    @Override
    public void close() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }
}
