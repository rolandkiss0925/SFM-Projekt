package mockito_test;

import hu.unideb.inf.controller.FXMLStudentsSceneController;
import hu.unideb.inf.model.Restaurant;
import hu.unideb.inf.model.Users;
import org.junit.jupiter.api.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FXMLStudentsSceneControllerTest {
    @BeforeAll //@BeforeClass
    public static void setUpClass() {
    }

    @AfterAll //@AfterClass
    public static void tearDownClass() {
    }

    @BeforeEach //@Before
    public void setUp() {
    }

    @AfterEach //@After
    public void tearDown() {
    }

    // Itt jonnek a tesztelendo metodusok
    @Test
    public void userDataBaseShoulExist() {
        boolean expected = true;

        File file = new File("src/main/java/hu/unideb/inf/controller/user_data.csv");
        boolean isItExist = file.exists();

        Assertions.assertEquals(expected, isItExist);
    }

    @Test
    public void readAllUsersShouldContainAnyUsers() {
        List<Users> temp = FXMLStudentsSceneController.readAllUsersFromCSV();
        Assertions.assertNotEquals(temp, null);

    }

    @Test
    public void nullUserShouldNotExist() {
        Assertions.assertEquals(FXMLStudentsSceneController.doesUserExists(new Users(null, null)), false);
    }

    @Test
    public void SetNameShouldReturnCorrectFormat() {
        String expected = String.format("%s%d","Alma",2);
        Assertions.assertEquals(expected, FXMLStudentsSceneController.SetName("Alma", 2));
    }

    @Test
    public void SetName2ShouldReturnCorrectFormat() {
        String expected = String.format("%s", "Alma");
        Assertions.assertEquals(expected, FXMLStudentsSceneController.SetName2("Alma"));
    }
}
