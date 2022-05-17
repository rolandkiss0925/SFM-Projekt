package hu.unideb.inf.controller;

import hu.unideb.inf.model.Model;
import hu.unideb.inf.model.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;

public class AdWindowController implements Initializable {
    private Model model;
    private static long createdMillis;

    @FXML
    private WebView webViewVideo;

    @FXML
    private Text counter;

    private static WebEngine engine;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        engine = webViewVideo.getEngine();
        loadPage();

        createdMillis = System.currentTimeMillis();

        /*for (int i = getAgeInSeconds(); i <= 10;) {
            counter.setText(getAgeInSeconds()+"");
        }*/
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public static void shutdown() {
        FXMLStudentsSceneController.adFinished(getAgeInSeconds());
        engine.load(null);
    }


    //Eljarasok es fuggvenyek

    private static int getAgeInSeconds() {
        long nowMillis = System.currentTimeMillis();
        return (int)((nowMillis - createdMillis) / 1000);
    }

    private void loadPage() {
        engine.load(getRandomURL());
    }

    private String getRandomURL(){
        List<String> urlList = getURL_List();

        Random r = new Random();
        int low = 0;
        int high = urlList.size()+1;
        int R = r.nextInt(high-low) + low;

        String result = urlList.get(R);

        return result;
    }

    private List<String> getURL_List(){
        List<String> result = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File("src/main/java/hu/unideb/inf/controller/adLinks.txt"));
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                result.add(line);
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Ad database not found! Exception: " + e);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Hiba");
            alert.setHeaderText("Ad database not found! Exception: " + e);
            alert.showAndWait();
            return null;
        }
        return result;
    }
}
