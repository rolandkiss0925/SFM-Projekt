package mockito_test;

import hu.unideb.inf.model.Restaurant;
import org.junit.jupiter.api.*;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;

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
        List<Restaurant> expected = Arrays.asList(new Restaurant(), new Restaurant(), new Restaurant());

    }

    @Test
    public void handleDataShouldFillEttermeksetWithDataFromCsv() {
        // todo
    }
}
