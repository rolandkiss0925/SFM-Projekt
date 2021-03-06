package hu.unideb.inf.controller;

import hu.unideb.inf.model.Food;
import hu.unideb.inf.model.JpaFoodDAO;
import hu.unideb.inf.model.Model;
import hu.unideb.inf.model.Restaurant;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView ;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
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

    @PersistenceUnit
    private EntityManager emf;

    @FXML
    private TabPane tp;

    @FXML
    private GridPane kosargrid;

    @FXML
    private GridPane menugrid;

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
    private Label totalPrice;

    @FXML
    private Tab Login;

    @FXML
    private Tab etteremeink;

    @FXML
    private ChoiceBox<String> menus1;

    @FXML
    private ListView<String> menulist;

    @FXML
    private ChoiceBox<String> myChoiceBox;


    @FXML
    public void handleLoadButtonPushed(ActionEvent event) {

        int i = 0;
        final int[] total = {0};
        for (Food f : MainApp.getKajak()){
            if (f.getName().contains("burger") && !Objects.equals(f.getName(), "null")) {
                Label name = new Label();
                name.setText(f.getName());
                name.setPrefSize(75,75);

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
                        f.setDb(f.getDb()-1);
                        db.setText(f.getDb() + "");
                        f.setPrice(baseprice * f.getDb());
                        price.setText(f.getPrice() + " Ft");
                        if (f.getDb() == 0) {
                            kosargrid.getChildren().removeIf(n -> GridPane.getRowIndex(n) == finalI);
                        }
                    }
                });

                Button plus = new Button("+");
                plus.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        total[0] += baseprice;
                        totalPrice.setText(total[0] + " Ft");
                        f.setDb(f.getDb()+1);
                        f.setPrice(baseprice * f.getDb());
                        price.setText(f.getPrice() + " Ft");
                        db.setText(f.getDb() + "");
                    }
                });

                total[0] += f.getPrice();

                totalPrice.setText(total[0] + " Ft");

                kosargrid.addRow(i, name, min, db, plus, price);
                i++;

            }
        }
    }


    private final List<String> etteremekarray =  new ArrayList<>();
    //private final String[] etteremekarray = {"Arp??d burger", "Valhalla","H??zi ??zek", "Ibolyka pessz??","sAJTOS H??Z","F??c??nkakas"};
    private final List<String> kajagenyok = new ArrayList<>();
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

    public void LognGEnyo(ActionEvent actionEvent) {
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
                    // makemenufromrestaurnat(text);
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
    }

    private void makemenufromrestaurnat(String text) {
        //EntityManager em = emf.getEntityManagerFactory().createEntityManager();
        kajagenyok.addAll(MainApp.getFood(text));
        menus1.getItems().addAll(kajagenyok);
        //  List<Food> kajak =


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



    //--------------------------SEGED F??GGV??NYEK----------------------------------------------------------------------------
  /*  public void makegrid(ActionEvent actionEvent) {
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
*/
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
        menulist.getItems().add(menus1.getValue());

    }

    public void minusitem(ActionEvent actionEvent) {
        menulist.getItems().remove(menus1.getValue());

    }
//-------------------ALERT---------------------------

    private void showAlertWithDefaultHeaderText() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Regisztr??ci??");
        alert.setHeaderText("Nemsok??ra ehetsz, te dagadt");
        alert.setContentText("A regisztr??l??s sikeres volt");

        ImageView icon = new ImageView("file:../SFMProjekto/images/alertFood.jpg");

        icon.setFitHeight(200);
        icon.setFitWidth(200);
        alert.getDialogPane().setGraphic(icon);
        alert.showAndWait();
    }


    public void Payout(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Fizet??s.exe");
        alert.setHeaderText("K??sz??nj??k a rendel??s??t");
        alert.setContentText("Te dagadt");
        ImageView icon = new ImageView("file:../SFMProjekto/images/FizetesAlert.jpg");

        icon.setFitHeight(100);
        icon.setFitWidth(100);
        alert.getDialogPane().setGraphic(icon);

        alert.showAndWait();

    }

    public void makegrid(ActionEvent actionEvent) {
    }
}
