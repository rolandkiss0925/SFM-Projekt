package hu.unideb.inf.controller;

import hu.unideb.inf.model.Food;
import hu.unideb.inf.model.Model;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView ;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.*;

public class FXMLStudentsSceneController implements Initializable{
    private Model model;
    private Food foods;
    private int x = 0;

    private EventHandler<ActionEvent> GoToEtterem;

    public void setModel(Model model) {
        this.model = model;
    }

    @FXML
    private TabPane tp;

    @FXML
    private GridPane probagrid;
    @FXML
    private GridPane etteremgrid;

    @FXML
    private Label etterem_nev;
    @FXML
    private Label countitem;
    @FXML
    private Tab kosar;

    @FXML
    private Tab etterem;

    @FXML
    private Tab registration;

    @FXML
    private Tab Login;
    @FXML
    private Tab etteremeink;

    @FXML
    private ChoiceBox<String> myChoiceBox;

    private final String[] etteremekarray = {"Arpád burger", "Valhalla","Házi ízek", "Ibolyka pesszó","sAJTOS HÁZ","Fácánkakas"};
    private final String[] kajakoma = {"első", "masoddik", "harmadik", "negyedik", "ötödik", "hatodik"};
    private final String[] seged = {"-1", "+1", "Darabszam"};
    private final Button[] seged2 =new Button[4];

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    }

    @FXML
    void handleButtonPushed(){
        tp.getSelectionModel() .select(kosar);
    }

    public void handleChangeName(ActionEvent actionEvent) {
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
        int x = etteremekarray.length;
        Button[] button = new Button[x];
        for (int i = 0; i < x; i++) {
            button[i] = new Button();
            button[i].setText(SetName2(etteremekarray[i]));
            button[i].setId(SetName(etteremekarray[i],i));
            button[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    String text = "zegz";
                    text = ((Button)actionEvent.getSource()).getText();
                    init(text);
                    tp.getSelectionModel().select(etterem);
                }
            });
            etteremgrid.add(button[i],0,i);
        }
        tp.getSelectionModel().select(etteremeink);
    }



    public void RegistforFood(ActionEvent actionEvent) {
        tp.getSelectionModel().select(registration);

    }

    private EventHandler<ActionEvent> init(String text) {
        etterem_nev.setText("\t\t\t"+text);
        return null;
    }

    public void GobackForLogin(ActionEvent actionEvent) {
        showAlertWithDefaultHeaderText();
        tp.getSelectionModel().select(Login);

    }

    public void Restaurant(MouseEvent mouseEvent) {
    }


    public void Gotokosar(ActionEvent actionEvent) {
        tp.getSelectionModel().select(kosar);
    }

    public void GobacktoRestaurants(ActionEvent actionEvent) {
        tp.getSelectionModel().select(etteremeink);
    }

    public void Payout(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Fizetés.exe");
        alert.setHeaderText("Köszönjük a rendelését");
        alert.setContentText("Te dagadt");
        ImageView icon = new ImageView("file:C:/Users/tozso/Desktop/StudentsRegister2/FizetesAlert.jpg");

        icon.setFitHeight(100);
        icon.setFitWidth(100);
        alert.getDialogPane().setGraphic(icon);

        alert.showAndWait();

    }

    //--------------------------SEGED FÜGGVÉNYEK----------------------------------------------------------------------------
    public void makegrid(ActionEvent actionEvent) {
        int count = 0;
        int j = 0;
        String asd;
        for (int k = 0; k < kajakoma.length; k++) {
            probagrid.add(new Label(kajakoma[k]),0,k);
            probagrid.add(new Button(SetName(seged[0],k)),1,k);
            probagrid.add(new Button(SetName(seged[1],k )), 2,k);
            probagrid.add(new Label(SetName(seged[2],k)),3,k);
            if (j!=seged.length-1) {j++;}
        }
        if (((Button)actionEvent.getSource()).getText().contains("-1"))
        {
            String text = "Szopo";
            text = ((Button)actionEvent.getSource()).getText();
            minusitem2(text,x);
        }
    }

    private String SetName(String button, int k) {
        return String.format("%s%d",button,k);
    }
    private String SetName2(String s) {
        return String.format("%s",s);
    }

    private void minusitem2(String text, int x) {
    }

    public void plusitem(ActionEvent actionEvent) {
        x++;
        countitem.setText("Darabszám: " + x);
    }

    public void minusitem(ActionEvent actionEvent) {
        if (x>0) x--;
        countitem.setText("Darabszám: " + x);

    }


}

