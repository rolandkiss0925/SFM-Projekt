package hu.unideb.inf;

import hu.unideb.inf.model.Food;
import hu.unideb.inf.model.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView ;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.*;

public class FXMLStudentsSceneController implements Initializable{
    private Model model;
    private Food foods;

    public void setModel(Model model) {
        this.model = model;
    }

    @FXML
    private TabPane tp;


    @FXML
    private Tab kosar;

    @FXML
    private Tab etterem;

    @FXML
    private Tab registration;

    @FXML
    private Tab Login;

    @FXML
    private ChoiceBox<String> myChoiceBox;

    private String[] kajakoma;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        foods = new Food();
        ArrayList<String[]> foodd = foods.getList();
        String list = foodd.toString();

        for (Object obj : foodd) {
            if (obj instanceof String[]) {
                String[] strArray = (String[]) obj;
                myChoiceBox.getItems().add(Arrays.toString(strArray));
                // System.out.println(obj);
            }


            // myChoiceBox.setOnAction(this::getFood);
        }
    }

        @FXML
    void handleButtonPushed(){
        tp.getSelectionModel() .select(kosar);
    }

    public void handleChangeName(ActionEvent actionEvent) {
    }

    public void handleLoadButtonPushed(ActionEvent actionEvent) {
    }

    private void showAlertWithDefaultHeaderText() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Regisztráció");
        alert.setHeaderText("Nemsokára ehetsz, te dagadt");
        alert.setContentText("A regisztrálás sikeres volt");

        ImageView icon = new ImageView("file:C:/Users/tozso/Desktop/StudentsRegister2/alertFood.jpg");

        icon.setFitHeight(200);
        icon.setFitWidth(200);
        alert.getDialogPane().setGraphic(icon);
        alert.showAndWait();
    }


    public void LognGEnyo(ActionEvent actionEvent) {
        tp.getSelectionModel().select(etterem);
    }

    public void RegistforFood(ActionEvent actionEvent) {
        tp.getSelectionModel().select(registration);

    }

    public void GobackForLogin(ActionEvent actionEvent) {
        showAlertWithDefaultHeaderText();
        tp.getSelectionModel().select(Login);

    }

    public void ChoiceFood() {
       //ForKaja.getItems().addAll("Hamburger", "pizza", "gyros");

    }


    public void myChoiceBox(MouseEvent mouseEvent) {
    }
}

