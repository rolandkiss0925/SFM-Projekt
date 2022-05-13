package hu.unideb.inf.controller;

import hu.unideb.inf.model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.h2.tools.Server;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;
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
    static List<Restaurant> ettermek = new ArrayList<>();

    static List<Users> felhList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        startDatabase();

        try(FoodDAO fDAO = new JpaFoodDAO();) {
            handleData(fDAO);
            kajak = fDAO.getFoods();
            ettermek = fDAO.getRestaurants();
        } catch (SQLException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }

        try(UsersDAO uDAO = new JpaUsersDAO();) {
            handleUserData(uDAO);
            felhList = uDAO.getUsers();
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

    public static List<Restaurant> getEttermek() {
        return ettermek;
    }

    public static List<Users> getFelhList(){return felhList; }


    public static void handleData(FoodDAO fDAO){

        String fname = "src\\main\\java\\hu\\unideb\\inf\\model\\database1.csv";
        List<String> sorok = FileUtils.readLines(fname);

        HashSet<String> ettermekset = new HashSet<>();

        List<Food> foodslist = new ArrayList<>();


        for (String sor : sorok) {
            var tomb = sor.split(";");
            ettermekset.add(tomb[0]);
        }

    //    Model model = new Model();

        int i = 0;
        for (String etterem : ettermekset){
            Restaurant r = new Restaurant();
            r.setName(etterem);

            for (String sor : sorok){
                String[] tomb = sor.split(";");
                if (etterem.equals(tomb[0])){

                    Food etel = new Food();
                    etel.setName(tomb[1]);
                    etel.setPrice(Integer.parseInt(tomb[3]));
                    etel.setDb(1);
                    r.getFoods().add(etel);

                    Food ital = new Food();
                    ital.setName(tomb[2]);
                    ital.setPrice(Integer.parseInt(tomb[4]));
                    ital.setDb(1);
                    r.getFoods().add(ital);

                    i++;

                }
            }
            fDAO.saveRestaurant(r);
        }




    /*    Restaurant meki = new Restaurant();
        meki.setName("McDonald's");
        meki.getFoods().add(model.getPizza());
        meki.getFoods().add(model.getSb());
  //      meki.getFoods().add(model.getKola());
        fDAO.saveRestaurant(meki);

        Restaurant kfc = new Restaurant();
        kfc.setName("KFC");
        kfc.getFoods().add(model.getKola());
        fDAO.saveRestaurant(kfc);

        Restaurant rednekk = new Restaurant();
        Restaurant zero = new Restaurant();
        Restaurant subway = new Restaurant();
        rednekk.setName("Rednekk BBQ");
        rednekk.getFoods().add(model.getJoker());
        zero.setName("Zero Bistro");
        subway.setName("Subway Sandwich");
        fDAO.saveRestaurant(rednekk);
        fDAO.saveRestaurant(zero);
        fDAO.saveRestaurant(subway); */
    }

    public static void handleUserData(UsersDAO uDAO){
        String fname2 = "src\\main\\java\\hu\\unideb\\inf\\model\\users.csv";
        List<String> sorok2 = FileUtils.readLines(fname2);
        //HashSet<String> ettermekset = new HashSet<>();
        //List<Users> usersList = new ArrayList<>();



        for (String sor : sorok2) {
            var tomb2 = sor.split(";");
            //usersList.add(new Users(tomb2[0], tomb2[1]));
            Users us= new Users(tomb2[0],tomb2[1]);
            //us.setName(tomb2[0]);
            //us.setPassword(tomb2[1]);
            uDAO.saveUser(us);
        }
    }

    private static Server s = new Server();
    
    private static void startDatabase() throws SQLException {
        s.runTool("-tcp", "-web", "-ifNotExists");
    }

    private static void stopDatabase()  {
        s.shutdown();
    }
    
}
