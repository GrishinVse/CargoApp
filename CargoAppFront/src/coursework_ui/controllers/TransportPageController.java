package coursework_ui.controllers;

import coursework_ui.Main;
import coursework_ui.models.Transport;
import coursework_ui.utils.RequestManager;
import javafx.beans.property.LongProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class TransportPageController {

    private Main mainApp;
    private Stage currentStage;

    public TransportPageController(){ }

    @FXML
    private TableView<Transport> transportTableView;

    public void setTransportTableView(TableView<Transport> transportTableView) {
        this.transportTableView = transportTableView;
    }

    @FXML
    private TableColumn<Transport, Long> idColumn;

    @FXML
    private TableColumn<Transport, String> brandColumn;

    @FXML
    private TableColumn<Transport, String> capacityColumn;

    @FXML
    private TableColumn<Transport, String> carryingColumn;

    @FXML
    private TableColumn<Transport, String> plateColumn;

    // Buttons

    @FXML
    private Button deleteButton;

    @FXML
    private Button editButton;

    @FXML
    private Button newButton;

    RequestManager req_manager = new RequestManager();
    private TransportEditPageController editPageController;

    /**
     * Инициализация контроллера при первом запуске
     */
    @FXML
    public void initialize() {
        try {
            this.mainApp = mainApp;
            this.transportTableView.setItems(req_manager.getTransports());
        } catch (IOException e) {
            e.printStackTrace();
        }

        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        brandColumn.setCellValueFactory(cellData -> cellData.getValue().brandProperty());
        capacityColumn.setCellValueFactory(cellData -> cellData.getValue().capacityProperty());
        carryingColumn.setCellValueFactory(cellData -> cellData.getValue().carryingProperty());
        plateColumn.setCellValueFactory(cellData -> cellData.getValue().licence_plateProperty());
    }

    @FXML
    private void handleDeleteTransport(){
        int selectedIndex = transportTableView.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0){
            Boolean result = req_manager.deleteTransport(transportTableView.getSelectionModel().getSelectedItem());
            if (result){
                transportTableView.getItems().remove(selectedIndex);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("ERROR!");
                alert.setHeaderText("Couldn't delete this transport!");
                alert.setContentText("Please try again!");

                alert.showAndWait();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR!");
            alert.setHeaderText("Can't delete this line!");
            alert.setContentText("Please select any recording!");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleEditTransport(){
        Transport selectedTransport = transportTableView.getSelectionModel().getSelectedItem();

        if (selectedTransport != null) {
            try {
                FXMLLoader loader = new FXMLLoader(TransportEditPageController.class.getResource("/views/TransportEditPage.fxml"));
                Parent page = loader.load();

                currentStage = mainApp.getPrimaryStage();
                currentStage.setScene(new Scene(page));

                editPageController = loader.getController();

                currentStage.show();
                //editPageController.initialize();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR!");
            alert.setHeaderText("Can't edit this line!");
            alert.setContentText("Please select any recording!");

            alert.showAndWait();
        }
        /*
        if (currentUser.getJob().getJobId() == 1){
            Employee tempEmployee = employeeTableView.getSelectionModel().getSelectedItem();
            Employee resultEmployee = mainApp.showEmployeesEditPage(stage, tempEmployee);
            if (resultEmployee != null) {
                requests.updateEmployee(resultEmployee);
                mainApp.showMainApp(stage);
            } else {
                mainApp.showMainApp(stage);
            }
        }

         */
    }

}
