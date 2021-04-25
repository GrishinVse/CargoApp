package coursework_ui.controllers;

import coursework_ui.models.Transport;
import coursework_ui.utils.RequestManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TransportEditPageController {

    @FXML
    private Button cancelButton;

    @FXML
    private Button okButton;

    @FXML
    private TextField brandField;

    @FXML
    private TextField carryingField;

    @FXML
    private TextField capacityField;

    @FXML
    private TextField plateField;

    RequestManager req_manager = new RequestManager();
    Transport loc_transport = new Transport();
    private Stage dialogStage;

    public void setTransport(Transport transport){
        this.loc_transport = transport;
        brandField.setText(transport.getBrand());
        carryingField.setText(transport.getCarrying());
        capacityField.setText(transport.getCapacity());
        plateField.setText(transport.getLicence_plate());
    }

    private boolean isInputValid() {
        String errorMessage = "";
        if (brandField.getText() == null || brandField.getText().length() == 0) {
            errorMessage += "Error: brandField";
        }
        else if (carryingField.getText() == null || carryingField.getText().length() == 0) {
            errorMessage += "Error: carryingField";
        }
        else if (capacityField.getText() == null || capacityField.getText().length() == 0) {
            errorMessage += "Error: capacityField";
        }
        else if (plateField.getText() == null || plateField.getText().length() == 0) {
            errorMessage += "Error: plateField";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR!");
            alert.setHeaderText("Fields are incorrect");
            alert.setContentText(errorMessage);

            alert.showAndWait();
            return false;
        }
    }

    public TransportEditPageController(){}

    @FXML
    public void initialize() {

    }

    @FXML
    void handleOk(ActionEvent event) {
        if (isInputValid()){
            loc_transport.setBrand(brandField.getText());
            loc_transport.setCapacity(capacityField.getText());
            loc_transport.setCarrying(carryingField.getText());
            loc_transport.setLicence_plate(plateField.getText());

            //  close();
        }
    }

    @FXML
    void handleCancel(ActionEvent event) {

    }
}
