package hu.unideb.inf.controller;

import hu.unideb.inf.model.*;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView ;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import java.io.*;
import java.net.URL;
import java.util.*;

public class FXMLStudentsSceneController implements Initializable{
    private Model model;
    private Food foods;

    private EventHandler<ActionEvent> GoToEtterem;

    public void setModel(Model model) {
        this.model = model;
    }

    @PersistenceUnit
    private EntityManager emf;
    @FXML
    private TabPane tp;
    @FXML
    private GridPane etteremgrid;
    @FXML
    private Label etterem_nev;
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
    private ChoiceBox<String> menus1;
    @FXML
    private ChoiceBox<String> menus2;
    @FXML
    private ChoiceBox<String> menus3;
    @FXML
    private ListView<String> menulist;
    @FXML
    private ChoiceBox<String> myChoiceBox;

    @FXML
    private GridPane kosargrid;

    @FXML
    private Label totalPrice;

    @FXML
    private TextField loginNameTextBox;

    @FXML
    private TextField loginPwdTextBox;

    @FXML
    private TextField regEmailTextBox;

    @FXML
    private TextField regPwdTextBox;

    List<Node> nodes = new ArrayList<>();

    @FXML
    public void handleLoadButtonPushed(ActionEvent event) {

        kosargrid.getChildren().removeAll(nodes);

     /*   for (var e : kosargrid.getChildren()){
            kosargrid.getChildren().removeAll(e);
        } */
        System.out.println(etterem_nev.getText());
        int i = 0;
        final int[] total = {0};
        for (Restaurant r : MainApp.getEttermek()) {
            if (r.getName().equals(etterem_nev.getText())) {
                for (String s : menulist.getItems()) {
                    for (Food f : r.getFoods()) {
                        if (s.equals(f.getName())) {
                            Label name = new Label();
                            name.setText(f.getName());
                            name.setPrefSize(150, 75);

                            Label db = new Label(f.getDb() + "");

                            Label price = new Label();
                            int baseprice = f.getPrice();
                            price.setText(baseprice + " Ft");

                            Button min = new Button("-");
                            int finalI = i;
                            min.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    total[0] -= baseprice;
                                    totalPrice.setText(total[0] + " Ft");
                                    f.setDb(f.getDb() - 1);
                                    db.setText(f.getDb() + "");
                                    f.setPrice(baseprice * f.getDb());
                                    price.setText(f.getPrice() + " Ft");
                                    if (f.getDb() == 0)
                                        kosargrid.getChildren().removeIf(n -> GridPane.getRowIndex(n) == finalI);
                                }
                            });

                            Button plus = new Button("+");
                            plus.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    total[0] += baseprice;
                                    totalPrice.setText(total[0] + " Ft");
                                    f.setDb(f.getDb() + 1);
                                    f.setPrice(baseprice * f.getDb());
                                    price.setText(f.getPrice() + " Ft");
                                    db.setText(f.getDb() + "");
                                }
                            });

                            total[0] += f.getPrice();

                            totalPrice.setText(total[0] + " Ft");

                            kosargrid.addRow(i, name, min, db, plus, price);
                            i++;

                            nodes.add(name);
                            nodes.add(min);
                            nodes.add(db);
                            nodes.add(plus);
                            nodes.add(price);
                        }
                    }
                }
            }
        }
    }

    private final List<String> etteremekarray =  new ArrayList<>();
    private final List<String> kajagenyok = new ArrayList<String>();
    private final List<String> italgenyok = new ArrayList<String>();
    private final List<String> koretgenyok = new ArrayList<String>();

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

    public void LognGEnyo(ActionEvent actionEvent) {
        Users felhasznalo = new Users(loginNameTextBox.getText(), loginPwdTextBox.getText());
        if (doesUserExists(felhasznalo)){
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
                        makemenufromrestaurnat(text);
                        makedrinkfromrestaurnat(text);
                        makekoretfromrestaurnat(text);
                        tp.getSelectionModel().select(etterem);
                    }
                });
                etteremgrid.add(button[i],0,i);
                button[i].setStyle("-fx-background-color: darkblue");
                button[i].setStyle("-fx-text-fill: darkblue");
                etteremgrid.setHgap(11);
                etteremgrid.setVgap(11);
            }
            tp.getSelectionModel().select(etteremeink);
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Hiba");
            alert.setHeaderText("E-mail cím vagy jelszó helytelen!");
            alert.showAndWait();
        }



    }
    private void makedrinkfromrestaurnat(String text) {
        italgenyok.addAll(JpaFoodDAO.getdrink(text));
        for (String s:italgenyok) {
            menus2.getItems().add(String.valueOf(s));
        }
    }

    //ChoiceBoxba teszi az étteremhez tartozó ételeket/italokat
    private void makemenufromrestaurnat(String text) {
        kajagenyok.addAll(JpaFoodDAO.getfood(text));
        for (String s:kajagenyok) {
            menus1.getItems().add(String.valueOf(s));
        }
    }

    private void makekoretfromrestaurnat(String text) {
        koretgenyok.addAll(JpaFoodDAO.getkoret(text));
        for (String s:koretgenyok) {
            menus3.getItems().add(String.valueOf(s));
        }
    }


    //Regisztráló ablakhoz dob
    public void RegistforFood(ActionEvent actionEvent) {tp.getSelectionModel().select(registration); }

    //Beállítja a felsőlécet a kiválasztott étterem nevére
    private EventHandler<ActionEvent> init(String text) {
        etterem_nev.setText(text);
        return null;
    }
    //Vissza lép regisztráció után a Login oldalra
    public void GobackForLogin(ActionEvent actionEvent) {
        Users u = new Users(regEmailTextBox.getText(), regPwdTextBox.getText());
        registUserToCSV(u);

        //showAlertWithDefaultHeaderText();
        //tp.getSelectionModel().select(Login);
    }

    public void Restaurant(MouseEvent mouseEvent) {
    }

    //Kosar oldalára lép
    public void Gotokosar(ActionEvent actionEvent) {
        tp.getSelectionModel().select(kosar);
        handleLoadButtonPushed(actionEvent);
    }

    //Vissza lép az éttermekre
     public void GobacktoRestaurants(ActionEvent actionEvent) {

        menus1.getItems().removeAll(kajagenyok);
        kajagenyok.clear();
        menus2.getItems().removeAll(italgenyok);
        italgenyok.clear();
        menus3.getItems().removeAll(koretgenyok);
        koretgenyok.clear();


        tp.getSelectionModel().select(etteremeink);
    }

    public void back2Login(ActionEvent actionEvent){
        tp.getSelectionModel().select(Login);
    }

    //--------------------------SEGED FÜGGVÉNYEK----------------------------------------------------------------------------
    private String SetName(String button, int k) {return String.format("%s%d",button,k);}
    private String SetName2(String s) {return String.format("%s",s);}

    public void plusitem(ActionEvent actionEvent) {menulist.getItems().add(menus1.getValue());}
    public void plusitem2(ActionEvent actionEvent) {menulist.getItems().add(menus2.getValue()); }
    public void plusitem3(ActionEvent actionEvent) {menulist.getItems().add(menus3.getValue()); }

    public void minusitem(ActionEvent actionEvent) {menulist.getItems().remove(menus1.getValue());}
    public void minusitem2(ActionEvent actionEvent) {menulist.getItems().remove(menus2.getValue());}
    public void minusitem3(ActionEvent actionEvent) {menulist.getItems().remove(menus3.getValue());}
//-------------------ALERT---------------------------

    //regisztráció után felugró ablak
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

    //Rendelés leadása utáni felugró ablak
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

    public void makegrid(ActionEvent actionEvent) {
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
