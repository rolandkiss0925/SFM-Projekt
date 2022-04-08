package hu.unideb.inf;

import hu.unideb.inf.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class FXMLStudentsSceneController {
    private Model model;

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
    void handleButtonPushed(){
        tp.getSelectionModel() .select(kosar);
    }

    public void handleChangeName(ActionEvent actionEvent) {
    }

    public void handleLoadButtonPushed(ActionEvent actionEvent) {
    }

    public void LognGEnyo(ActionEvent actionEvent) {
        tp.getSelectionModel() .select(etterem);
    }
}
