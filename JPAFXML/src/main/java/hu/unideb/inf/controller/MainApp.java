package hu.unideb.inf.controller;

import hu.unideb.inf.model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.h2.tools.Server;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/FXMLStudentsScene.fxml"));
        Scene scene = new Scene(loader.load());
        ((FXMLStudentsSceneController)loader.getController()).setModel(new Model());
        stage.setTitle("Students Register");
        stage.setScene(scene);
        stage.show();

    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */

    static List<Food> kajak = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        startDatabase();

        try(FoodDAO fDAO = new JpaFoodDAO();) {
            handleData(fDAO);
            kajak = fDAO.getFoods();
        } catch (SQLException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        launch(args);
        stopDatabase();
    }

    public static List<Food> getKajak(){
        return kajak;
    }

    public static void handleData(FoodDAO fDAO){
        Model model = new Model();

        Restaurant meki = new Restaurant();
        meki.setName("McDonald's");
        meki.getFoods().add(model.getPizza());
        meki.getFoods().add(model.getSb());
        meki.getFoods().add(model.getKola());
        fDAO.saveRestaurant(meki);

    }

    private static Server s = new Server();
    
    private static void startDatabase() throws SQLException {
        s.runTool("-tcp", "-web", "-ifNotExists");
    }

    private static void stopDatabase()  {
        s.shutdown();
    }
    
}
