package mockito_test;

import hu.unideb.inf.controller.FXMLStudentsSceneController;
import hu.unideb.inf.model.Restaurant;
import hu.unideb.inf.model.Users;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class FXMLStudentsSceneControllerTest {
    //@Mock


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
}
