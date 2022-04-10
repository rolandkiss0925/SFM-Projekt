package hu.unideb.inf;

import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import hu.unideb.inf.model.*;
import org.h2.tools.Server;

public class Application {

    public static void main(String[] args) throws SQLException {
        startDatabase();

        //try-with-resources
        try (AnimalDAO aDAO = new JpaAnimalDAO();) {
            handleData(aDAO);
        } catch (Exception e) {
            e.printStackTrace();
        }

   /*   Exercise 1
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        Customer customer = new Customer();
        customer.setFirstName("Dennys");
        customer.setLastName("Fredericci");

        Animal elephant = new Animal();
        elephant.setName("Nelly");
        elephant.setAge(10);
        elephant.setGender(Animal.GenderType.FEMALE);

        entityManager.getTransaction().begin();
        entityManager.persist(customer);
        entityManager.persist(elephant);
        entityManager.getTransaction().commit();

        System.out.println("Open your browser and navigate to http://localhost:8082/");
        System.out.println("JDBC URL: jdbc:h2:mem:my_database");
        System.out.println("User Name: sa");
        System.out.println("Password: ");
*/
    }

    public static void handleData(AnimalDAO aDAO){
        Animal a = new Animal();
        a.setName("Peppa");
        a.setAge(4);
        a.setGender(Animal.GenderType.FEMALE);
        // aDAO.saveAnimal(a);

        Animal a2 = new Animal();
        a2.setName("George");
        a2.setAge(2);
        a2.setGender(Animal.GenderType.MALE);
        // aDAO.saveAnimal(a2);

        Zoo zoo = new Zoo();
        zoo.setName("Debrecen Zoo");
        zoo.getAnimals().add(a);
        zoo.getAnimals().add(a2);
        aDAO.saveZoo(zoo);
    }

    private static void startDatabase() throws SQLException {
        new Server().runTool("-tcp", "-web", "-ifNotExists");
    }
}
