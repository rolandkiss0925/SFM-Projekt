package hu.unideb.inf.controller;

import hu.unideb.inf.model.Food;
import hu.unideb.inf.model.Model;
import hu.unideb.inf.model.Users;
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

import java.io.*;
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

    @FXML
    private TextField loginNameTextBox;

    @FXML
    private TextField loginPwdTextBox;

    @FXML
    private TextField regEmailTextBox;

    @FXML
    private TextField regPwdTextBox;

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

        ImageView icon = new ImageView("file:../SFMProjekto/images/alertFood.jpg");

        icon.setFitHeight(200);
        icon.setFitWidth(200);
        alert.getDialogPane().setGraphic(icon);
        alert.showAndWait();
    }
    public void LognGEnyo(ActionEvent actionEvent) {
        Users felhasznalo = new Users(loginNameTextBox.getText(), loginPwdTextBox.getText());
        if (doesUserExists(felhasznalo)){
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
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Hiba");
            alert.setHeaderText("E-mail vagy jelszó helytelen!");
            alert.showAndWait();
        }


    }



    public void RegistforFood(ActionEvent actionEvent) {
        tp.getSelectionModel().select(registration);
    }

    private EventHandler<ActionEvent> init(String text) {
        etterem_nev.setText("\t\t\t"+text);
        return null;
    }

    public void GobackForLogin(ActionEvent actionEvent) {
        Users u = new Users(regEmailTextBox.getText(), regPwdTextBox.getText());
        registUserToCSV(u);
        //showAlertWithDefaultHeaderText();
        //tp.getSelectionModel().select(Login);

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
        ImageView icon = new ImageView("file:../SFMProjekto/images/FizetesAlert.jpg");

        icon.setFitHeight(100);
        icon.setFitWidth(100);
        alert.getDialogPane().setGraphic(icon);

        alert.showAndWait();

    }

    public void back2Login(ActionEvent actionEvent){
        tp.getSelectionModel().select(Login);
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

    public List<Users> readAllUsersFromCSV(){
        List<Users> result = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File("src/main/java/hu/unideb/inf/controller/user_data.csv"));
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                String[] token = line.split(";");
                Users u = new Users(token[0], token[1]);
                result.add(u);
            }
        }
        catch (FileNotFoundException e){
            System.out.println("User database not found! Exception: " + e);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Hiba");
            alert.setHeaderText("User database not found! Exception: " + e);
            alert.showAndWait();
            return null;
        }
        return result;
    }

    public boolean doesUserExists(Users u1){
        List<Users> usersList = readAllUsersFromCSV();
        return usersList.contains(u1);
    }

    public List<String> getAllUserNames(){
        List<String> res = new ArrayList<>();
        List<Users> usersList = readAllUsersFromCSV();
        for (Users u:usersList
             ) {
            res.add(u.getName());
        }

        return res;
    }

    public void registUserToCSV(Users u){
        if (!doesUserExists(u) && !getAllUserNames().contains(u.getName())){
            try {
                File f =new File("src/main/java/hu/unideb/inf/controller/user_data.csv");
                FileWriter fw = new FileWriter(f, true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.newLine();
                bw.write(u.getName() + ";" + u.getPassword());

                bw.close();
                showAlertWithDefaultHeaderText();
            }
            catch (IOException e){
                System.out.println("File not found! Exception: " + e);
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Hiba");
            alert.setHeaderText("Ez a felhasználónév már foglalt!");
            alert.showAndWait();
        }
    }
}

