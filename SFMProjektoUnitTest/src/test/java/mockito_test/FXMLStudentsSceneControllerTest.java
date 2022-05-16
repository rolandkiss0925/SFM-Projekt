package mockito_test;

import hu.unideb.inf.model.Restaurant;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class FXMLStudentsSceneControllerTest {
    //@Mock
    private final List<String> etteremekarray =  new ArrayList<>();
    private final List<String> kajagenyok = new ArrayList<String>();
    private final List<String> italgenyok = new ArrayList<String>();
    private final List<String> koretgenyok = new ArrayList<String>();

    @BeforeAll //@BeforeClass
    public static void setUpClass() {
    }

    @AfterAll //@AfterClass
    public static void tearDownClass() {
    }

    @BeforeEach //@Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @AfterEach //@After
    public void tearDown() {
    }

    // Itt jonnek a tesztelendo metodusok
    @Test
    public void initializeShouldInitializeEtteremekarrayWithEtteremnevek () {

    }

    // Talan az
    @Test
    public void LognGEnyoShouldDoIDontKnowWhat() { // ???
        // todo
    }

    @Test
    public void makemenufromrestaurnatShouldFillKajagenyokWithKajak() {
        // todo
    }

    @Test
    public void makekoretfromrestaurnatShouldFillKoretgenyokWithKoretek() {
        // todo
    }

    @Test
    public void RegistforFoodShouldReturnToRegistrationWindow() {
        // todo
    }

    @Test
    public void GobackForLoginShoudReturnToLoginPage() {
        // todo
    }

    @Test
    public void GotokosarShouldReturnToShoppingBasket() {
        // todo
    }

    @Test
    public void GobacktoRestaurantsShouldReturnToRestaurants() {
        // todo
    }

    @Test
    public void SetNameShouldReturnName() { // *
        // todo
    }

    @Test
    public void SetName2ShouldReturnName() { // *
        // todo
    }

    @Test
    public void plusitemShouldAddValueToKajagenyok() {
        // todo
    }

    @Test
    public void plusitem2ShouldAddValueToItalgenyok() {
        // todo
    }

    @Test
    public void plusitem3ShouldAddValueToKoretgenyok() {
        // todo
    }

    @Test
    public void minusitemitemShouldRemoveValueFromKajagenyok() {
        // todo
    }

    @Test
    public void minusitem2ShouldRemoveValueFromItalgenyok() {
        // todo
    }

    @Test
    public void minusitem3ShouldRemoveValueFromKoretgenyok() {
        // todo
    }
}
