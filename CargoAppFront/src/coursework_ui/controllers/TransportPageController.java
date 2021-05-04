package coursework_ui.controllers;

import coursework_ui.models.Transport;
import coursework_ui.utils.RequestManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Контроллер по работе с вкладкой с транспортными средствами
 */
public class TransportPageController {

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

    /**
     * Инициализация контроллера при первом запуске
     */
    @FXML
    public void initialize() {
        try {
            this.currentStage = new Stage();
            this.transportTableView.setItems(req_manager.getTransports());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //idColumn.setCellValueFactory(new PropertyValueFactory<Transport, Long>("id"));

        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());

        brandColumn.setCellValueFactory(cellData -> cellData.getValue().brandProperty());
        capacityColumn.setCellValueFactory(cellData -> cellData.getValue().capacityProperty());
        carryingColumn.setCellValueFactory(cellData -> cellData.getValue().carryingProperty());
        plateColumn.setCellValueFactory(cellData -> cellData.getValue().licence_plateProperty());
    }

    /**
     * Метод обновляет страницу по нажатию кнопки
     */
    @FXML
    public void updatePage() {
        try {
            this.transportTableView.setItems(req_manager.getTransports());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //idColumn.setCellValueFactory(new PropertyValueFactory<Transport, Long>("id"));

        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());

        brandColumn.setCellValueFactory(cellData -> cellData.getValue().brandProperty());
        capacityColumn.setCellValueFactory(cellData -> cellData.getValue().capacityProperty());
        carryingColumn.setCellValueFactory(cellData -> cellData.getValue().carryingProperty());
        plateColumn.setCellValueFactory(cellData -> cellData.getValue().licence_plateProperty());
    }

    /**
     * Метод позволяет удалить запись о транспортом средстве
     */
    @FXML
    private void handleDeleteTransport(){
        int selectedIndex = transportTableView.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0){
            Boolean result = req_manager.deleteTransport(transportTableView.getSelectionModel().getSelectedItem());
            if (result){
                transportTableView.getItems().remove(selectedIndex);
                updatePage();
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

    /**
     * Метод позволяет создать запись о транспортом средстве
     */
    @FXML
    private void handleNewTransport(){
        Transport newTransport = new Transport("brand", "0.0", "0.0", "A111AA777");

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/TransportEditPage.fxml"));
            AnchorPane editPage = loader.load();

            Stage creatingStage = new Stage();
            creatingStage.setTitle("Creating New Transport");
            creatingStage.initOwner(MenuPageController.getPrimaryStage());
            creatingStage.initModality(Modality.APPLICATION_MODAL);

            Scene editScene = new Scene(editPage);
            creatingStage.setScene(editScene);

            TransportEditPageController controller = loader.getController();
            controller.initialize(creatingStage, new Transport());

            creatingStage.showAndWait();

            System.out.println(controller.isOkClicked());

            if (controller.isOkClicked()){
                Transport resultTransport = controller.getLocTransport();
                if (resultTransport != null) {
                    //transportTableView.getItems().add(resultTransport);
                    req_manager.createTransport(resultTransport);
                    updatePage();
                } else {
                    System.out.println("Haven't create new Transport!");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод позволяет редактировать запись о транспортом средстве
     */
    @FXML
    private void handleEditTransport(){
        Transport selectedTransport = transportTableView.getSelectionModel().getSelectedItem();

        if (selectedTransport != null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/views/TransportEditPage.fxml"));
                AnchorPane editPage = loader.load();

                Stage editStage = new Stage();
                editStage.setTitle("Transport Editing");
                editStage.initOwner(MenuPageController.getPrimaryStage());
                //editStage.initOwner(TransportPageController.getPrimaryStage());
                editStage.initModality(Modality.APPLICATION_MODAL);

                Scene editScene = new Scene(editPage);
                editStage.setScene(editScene);

                TransportEditPageController controller = loader.getController();
                controller.initialize(editStage, selectedTransport);

                editStage.showAndWait();

                System.out.println(controller.isOkClicked());

                if (controller.isOkClicked()){
                    Transport resultTransport = controller.getLocTransport();
                    if (resultTransport != null) {
                        req_manager.updateTransport(resultTransport);
                        updatePage();
                    } else {
                        System.out.println("Haven't updated Transport entity!");
                    }
                }

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
    }
        /*
        @FXML
    private void handleEditTransport(){
        Transport selectedTransport = transportTableView.getSelectionModel().getSelectedItem();

        if (selectedTransport != null) {
            try {
                FXMLLoader loader = new FXMLLoader(TransportEditPageController.class.getResource("/views/TransportEditPage.fxml"));
                Parent page = loader.load();

                currentStage.initOwner(MenuPageController.getPrimaryStage());
                currentStage.initModality(Modality.APPLICATION_MODAL);

                Scene editScene = new Scene(page);

                currentStage.setScene(editScene);

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
         */

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
