package hu.unideb.inf.controller;

import hu.unideb.inf.model.*;
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
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
    private GridPane kosargrid;

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
    private Label foodLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label creditsLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label foodLabel1;

    @FXML
    private Label priceLabel1;

    @FXML
    private Label totalPrice;

    @FXML
    private Label quantity1;

    @FXML
    private Label quantity2;

    @FXML
    private Label quantity3;

    @FXML
    private List<Label> q = new ArrayList<>();

    @FXML
    private ChoiceBox<String> myChoiceBox;

    @FXML
    private TextField loginUserTextBox;

    @FXML
    private TextField loginPwdTextBox;

    @FXML
    void handleLoadButtonPushed(ActionEvent event) {

        for(Label l : q){
            l = new Label();
        }

        StringBuilder sb = new StringBuilder("q");
        for (int i = 0; i < q.size(); i++){
            q.get(i).setId(sb.append(i).toString());
            q.get(i).setText(MainApp.getKajak().get(i).getDb() + "");
        }


        nameLabel.setText(MainApp.getKajak().get(0).getName());
        creditsLabel.setText(MainApp.getKajak().get(0).getPrice() + " ft");

        foodLabel.setText(MainApp.getKajak().get(1).getName());
        priceLabel.setText(MainApp.getKajak().get(1).getPrice() + " ft");

        foodLabel1.setText(MainApp.getKajak().get(2).getName());
        priceLabel1.setText(MainApp.getKajak().get(2).getPrice() + " ft");

        totalPrice.setText(MainApp.getKajak().get(0).getPrice() +MainApp.getKajak().get(1).getPrice() + MainApp.getKajak().get(2).getPrice() + " ft");

    //    quantity1.setText(MainApp.getKajak().get(0).getDb() + "");
    //    quantity2.setText(MainApp.getKajak().get(1).getDb() + "");
    //    quantity3.setText(MainApp.getKajak().get(2).getDb() + "");

        if (MainApp.getKajak().get(0).getDb() == 0)
            kosargrid.getChildren().removeAll(nameLabel, creditsLabel, quantity1);

    }

    private final int baseprice = MainApp.getKajak().get(0).getPrice();
    private final int baseprice1 = MainApp.getKajak().get(1).getPrice();
    private final int baseprice2 = MainApp.getKajak().get(2).getPrice();

    @FXML
    void handleButtonAdd(ActionEvent event){
        adder(MainApp.getKajak().get(0), baseprice);
        handleLoadButtonPushed(event);
    }

    @FXML
    void handleButtonRemove(ActionEvent event){
        remover(MainApp.getKajak().get(0), baseprice);
        handleLoadButtonPushed(event);
    }

    @FXML
    void handleButtonAdd1(ActionEvent event){
        adder(MainApp.getKajak().get(1), baseprice1);
        handleLoadButtonPushed(event);
    }

    @FXML
    void handleButtonRemove1(ActionEvent event){
        remover(MainApp.getKajak().get(1), baseprice1);
        handleLoadButtonPushed(event);
    }

    @FXML
    void handleButtonAdd2(ActionEvent event){
        adder(MainApp.getKajak().get(2), baseprice2);
        handleLoadButtonPushed(event);
    }

    @FXML
    void handleButtonRemove2(ActionEvent event){
        remover(MainApp.getKajak().get(2), baseprice2);
        handleLoadButtonPushed(event);
    }

    void remover(Food food, int price){
        if (food.getDb() != 0) {
            int db = food.getDb() - 1;
            food.setDb(db);
            food.setPrice(price * db);
        }
    }

    void adder(Food food, int price){
        int db = food.getDb() + 1;
        food.setDb(db);
        food.setPrice(price * db);
    }

 //   private final String[] etteremekarray = {"Arpád burger", "Valhalla","Házi ízek", "Ibolyka pesszó","sAJTOS HÁZ","Fácánkakas"};
    private List<String> etteremekarray = new ArrayList<>();
    private final String[] kajakoma = {"első", "masoddik", "harmadik", "negyedik", "ötödik", "hatodik"};
    private final String[] seged = {"-1", "+1", "Darabszam"};
    private final Button[] seged2 =new Button[4];

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        for (Restaurant r : MainApp.getEttermek()){
            etteremekarray.add(r.getName());
        }
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
        //Felhasznalo letezes ellenorzese users.csv-bol
        Users felhasznalo = new Users(loginUserTextBox.getText(), loginPwdTextBox.getText());

        if (MainApp.getFelhList().contains(felhasznalo)){
            int x = etteremekarray.size();
            Button[] button = new Button[x];
            for (int i = 0; i < x; i++) {
                button[i] = new Button();
                button[i].setText(SetName2(etteremekarray.get(i)));
                button[i].setId(SetName(etteremekarray.get(i),i));
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
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Hiba");
            alert.setHeaderText("Bejelentkezes sikertelen!");
            alert.setContentText("Felhasznalo nem letezik");

            alert.showAndWait();
        }
    }



    public void RegistforFood(ActionEvent actionEvent) {
        //Opens registration tab
        tp.getSelectionModel().select(registration);

    }

    private EventHandler<ActionEvent> init(String text) {
        etterem_nev.setText("\t\t\t"+text);
        return null;
    }

    public void GobackForLogin(ActionEvent actionEvent) {
        //##--Felhasznalo felvetele--####


        //###############################


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
