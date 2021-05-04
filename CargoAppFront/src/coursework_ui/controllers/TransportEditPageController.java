package coursework_ui.controllers;

import coursework_ui.models.Transport;
import coursework_ui.utils.AlertMessage;
import coursework_ui.utils.RequestManager;
import coursework_ui.utils.UtilsClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Контроллер по работе со страницей редактирования транспорта
 */
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

    private Boolean okClicked = false;

    RequestManager req_manager = new RequestManager();
    private Transport locTransport = new Transport();
    private Stage dialogStage;

    public void setLocTransport(Transport transport){
        this.locTransport = transport;
        brandField.setText(transport.getBrand());
        carryingField.setText(transport.getCarrying());
        capacityField.setText(transport.getCapacity());
        plateField.setText(transport.getLicence_plate());
    }

    public Transport getLocTransport() {
        return locTransport;
    }

    /**
     * Проверка введенных значений
     * @return true если введенные данный соответствуют формату
     */
    private boolean isInputValid() {

        String errorMessage = "";
        if (brandField.getText() == null || brandField.getText().length() == 0) {
            errorMessage += "Error: Incorrect Brand Field";
        }
        else if (carryingField.getText() == null || carryingField.getText().length() == 0 || !UtilsClass.isDigit(carryingField.getText())) {
            errorMessage += "Error: Carrying Field is not integer";
        }
        else if (capacityField.getText() == null || capacityField.getText().length() == 0 || !UtilsClass.isDigit(capacityField.getText())) {
            errorMessage += "Error: Capacity Field is not integer";
        }
        else if (plateField.getText() == null || plateField.getText().length() == 0) {
            errorMessage += "Error: plateField";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {

            AlertMessage.getAlert(errorMessage);

            return false;
        }
    }

    public TransportEditPageController(){}

    /**
     * Инициализация контроллера при первом запуске
     */
    @FXML
    public void initialize(Stage editStage, Transport selectedTransport) {
        this.dialogStage = editStage;
        setLocTransport(selectedTransport);
    }

    /**
     * Отрабатывет нажатия на кнопку OK
     */
    @FXML
    void handleOk(ActionEvent event) {
        if (isInputValid()){
            locTransport.setBrand(brandField.getText());
            locTransport.setCapacity(capacityField.getText());
            locTransport.setCarrying(carryingField.getText());
            locTransport.setLicence_plate(plateField.getText());

            okClicked = true;
            dialogStage.close();
        }
    }

    /*
    @FXML
    void handleEnter(ActionEvent actionEvent){
        okButton.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                handleOk(actionEvent);
            }
        });
    }

     */

    /**
     * Отрабатывет нажатия на кнопку Cancel
     */
    @FXML
    void handleCancel(ActionEvent event) {
        dialogStage.close();
    }

    public boolean isOkClicked(){
        return okClicked;
    }
}
