package com.coursework.cargo_app_ui.controllers;

import com.coursework.cargo_app_ui.models.OrderList;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditPageController {
    @FXML
    private CheckBox individualCheck;
    @FXML
    private CheckBox corporateCheck;

    // Choosing other entities

    @FXML
    private ChoiceBox<?> transportChoice;

    @FXML
    private ChoiceBox<?> clientChoice;

    // Text Areas

    @FXML
    private TextField orderTypeField;

    @FXML
    private TextArea descriptionField;

    @FXML
    private TextField startAddrField;

    @FXML
    private TextField otherAddrField;

    @FXML
    private TextField endAddrField;


    //private ObservableList<Client> clientsList;
    //private ObservableList<Corporate> corporatesList;
    //private ObservableList<Individual> individualsList;

    private Stage dialogStage;
    private OrderList orderList;
    private boolean okClicked = false;

    @FXML
    private void initialize(){}

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setOrder(OrderList order) {
        this.orderList = order;
        orderTypeField.setText(order.getOrderType());
        descriptionField.setText(order.getDescription());

        startAddrField.setText(order.getStartAddress());
        otherAddrField.setText(order.getOtherAddress());
        endAddrField.setText(order.getEndAddress());

        /*
        work with clientsList (???) or make 2 separated lists for individual and corporate clients !!!
        if (individualCheck.isSelected() == true &&  corporateCheck.isSelected() == false){
            clientChoice.setItems();
        }

         */
    }

    public boolean isOkClicked(){
        return okClicked;
    }


    @FXML
    private void handleOk() {

    }

    @FXML
    private void handleCancel() {

    }

}
