package coursework_ui.controllers;

import coursework_ui.models.Individual;
import coursework_ui.models.OrderList;
import coursework_ui.models.Transport;
import coursework_ui.utils.RequestManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class MainPageController {

    @FXML
    private TableView<Individual> individualTable;

    @FXML
    private TableColumn<Individual, String> indEmailColumn;

    @FXML
    private TableColumn<Individual, String> indPhoneColumn;

    @FXML
    private TableColumn<Individual, String> indFirstNameColumn;

    @FXML
    private TableColumn<Individual, String> indLastNameColumn;

    //////

    @FXML
    private TableView<Transport> transportTable;

    @FXML
    private TableColumn<Transport, Long> transIdColumn;

    @FXML
    private TableColumn<Transport, String> transBrandColumn;

    @FXML
    private TableColumn<Transport, String> transCapacityColumn;

    @FXML
    private TableColumn<Transport, String> transCarryingColumn;

    @FXML
    private TableColumn<Transport, String> transLicenceColumn;

    //////

    @FXML
    private TableView<OrderList> orderListTableView;

    @FXML
    private TableColumn<OrderList, Long> IdColumn;

    @FXML
    private TableColumn<OrderList, String> TypeColumn;

    @FXML
    private Label startLabel;

    @FXML
    private Label otherLabel;

    @FXML
    private Label endLabel;

    @FXML
    private Label typeLabel;

    @FXML
    private TextArea descriptionArea;

    RequestManager req_manager = new RequestManager();
    private Stage currentStage;

    public MainPageController(){}

    /**
     * Инициализация контроллера при первом запуске
     */
    @FXML
    public void initialize() {
        try {
            this.currentStage = new Stage();
            this.orderListTableView.setItems(req_manager.getOrderLists());
        } catch (IOException e) {
            e.printStackTrace();
        }

        IdColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        TypeColumn.setCellValueFactory(cellData -> cellData.getValue().orderTypeProperty());

        showOrderDetails(null);

        orderListTableView.getSelectionModel().selectedItemProperty().addListener(
                ((observableValue, oldValue, newValue) -> showOrderDetails(newValue))
        );
    }

    public void showOrderDetails(OrderList orderList){
        if (orderList != null){
            startLabel.setText(orderList.getStartAddress());

            if (orderList.getOtherAddress().length() == 0){
                otherLabel.setText(" - ");
            } else {
                otherLabel.setText(orderList.getOtherAddress());
            }

            endLabel.setText(orderList.getEndAddress());
            typeLabel.setText(orderList.getOrderType());
            descriptionArea.setText(orderList.getDescription());

            // Transport Table
            Transport curTransport = orderList.getTransportId();
            if (curTransport != null) {
                ObservableList<Transport> transports = FXCollections.observableArrayList();
                transports.add(curTransport);
                transportTable.setItems(transports);

                transIdColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());

                transBrandColumn.setCellValueFactory(cellData -> cellData.getValue().brandProperty());
                transCapacityColumn.setCellValueFactory(cellData -> cellData.getValue().capacityProperty());
                transCarryingColumn.setCellValueFactory(cellData -> cellData.getValue().carryingProperty());
                transLicenceColumn.setCellValueFactory(cellData -> cellData.getValue().licence_plateProperty());
            }

            Individual curIndividual = orderList.getIndividualId();
            if (curIndividual != null){
                ObservableList<Individual> individuals = FXCollections.observableArrayList();
                individuals.add(curIndividual);
                individualTable.setItems(individuals);

                indEmailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
                indPhoneColumn.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
                indFirstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
                indLastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
            }


        } else {
            startLabel.setText("No data");
            otherLabel.setText("No data");
            endLabel.setText("No data");
            typeLabel.setText("No data");
            descriptionArea.setText("No data");
        }
    }
}
