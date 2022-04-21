/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.controller;


import hu.unideb.inf.model.Food;
import hu.unideb.inf.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class FXMLStudentsSceneController {
    private Model model;

    public void setModel(Model model){
        this.model = model;
    }

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
    void handleLoadButtonPushed(ActionEvent event) {
        nameLabel.setText(MainApp.getKajak().get(0).getName());
        creditsLabel.setText(MainApp.getKajak().get(0).getPrice() + " ft");

        foodLabel.setText(MainApp.getKajak().get(1).getName());
        priceLabel.setText(MainApp.getKajak().get(1).getPrice() + " ft");

        foodLabel1.setText(MainApp.getKajak().get(2).getName());
        priceLabel1.setText(MainApp.getKajak().get(2).getPrice() + " ft");

        totalPrice.setText(MainApp.getKajak().get(0).getPrice() +MainApp.getKajak().get(1).getPrice() + MainApp.getKajak().get(2).getPrice() + " ft");

        quantity1.setText(MainApp.getKajak().get(0).getDb() + "");
        quantity2.setText(MainApp.getKajak().get(1).getDb() + "");
        quantity3.setText(MainApp.getKajak().get(2).getDb() + "");

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

    @FXML
    void handleOrder(ActionEvent event) {
        // TODO
    }

}
