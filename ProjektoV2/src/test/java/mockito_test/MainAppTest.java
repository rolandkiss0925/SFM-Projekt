package mockito_test;

import hu.unideb.inf.controller.MainApp;
import hu.unideb.inf.model.FileFoodDAO;
import hu.unideb.inf.model.FoodDAO;
import hu.unideb.inf.model.JpaFoodDAO;
import hu.unideb.inf.model.Restaurant;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainAppTest {
    //@Mock
    private static List<Restaurant> ettermek = new ArrayList<>();

    @BeforeAll //@BeforeClass
    public static void setUpClass() {
    }

    @AfterAll //@AfterClass
    public static void tearDownClass() {
    }

    @BeforeEach //@Before
    public void setUp() {
        // MockitoAnnotations.openMocks(this);
    }

    @AfterEach //@After
    public void tearDown() {
    }

    // Itt jonnek a tesztelendo metodusok
    @Test
    public void getEttermekShouldReturnEttermek() {
        // todo
    }
}
