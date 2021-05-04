package coursework_ui.controllers;

import coursework_ui.models.Corporate;
import coursework_ui.models.Individual;
import coursework_ui.models.OrderList;
import coursework_ui.models.Transport;
import coursework_ui.utils.RequestManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Контроллер по работе с главной вкладкой
 */
public class MainPageController {

    @FXML
    private Label clientInfo;

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
    private TableView<Corporate> corporateTable;

    @FXML
    private TableColumn<Corporate, String> crpEmailColumn;

    @FXML
    private TableColumn<Corporate, String> crpPhoneColumn;

    @FXML
    private TableColumn<Corporate, String> crpNameColumn;

    @FXML
    private TableColumn<Corporate, String> crpAddressColumn;

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

    @FXML
    private Button newButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;


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

            individualTable.setVisible(false);
            corporateTable.setVisible(false);
            clientInfo.setVisible(false);
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

    /**
     * Метод обновляет страницу по нажатию кнопки
     */
    @FXML
    public void updatePage() {
        try {
            this.orderListTableView.setItems(req_manager.getOrderLists());

            individualTable.setVisible(false);
            corporateTable.setVisible(false);
            clientInfo.setVisible(false);
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

    /**
     * Отображает подробную информацию по заказу.
     * @param orderList экземпляр класса OrderList, по которому мы хотим получить развернутую информацию
     */
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

            /**
             * Вывод информации о транспортном средстве по этому заказу
             */
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

            /**
             * Вывод информации о клиенте по этому заказу, в зависимости от заполненных полей
             */
            try {
                Object curIndividual = "null";
                Object curCorporate = "null";

                System.out.println("**>" + orderList.getIndividualId().toString() + "<**");
                if (orderList.getIndividualId().toString().contains("null")) {
                    System.out.println("Individual ID is NULL");
                } else {
                    curIndividual = orderList.getIndividualId().get();
                }

                System.out.println("**>" + orderList.getCorporateId().toString() + "<**");
                if (orderList.getCorporateId().toString().contains("null")){
                    System.out.println("Corporate ID is NULL");
                } else {
                    curCorporate = orderList.getCorporateId().get();
                }

                //System.out.println(curIndividual.toString() + curIndividual.getClass() + " | " + curCorporate.toString() + curCorporate.getClass());

                System.out.println(curIndividual);
                System.out.println(curIndividual.getClass());

                System.out.println(curCorporate);
                System.out.println(curCorporate.getClass());

                if (curIndividual != "null" & curCorporate == "null"){
                    ObservableList<Individual> individuals = FXCollections.observableArrayList();
                    individuals.add((Individual) curIndividual);

                    individualTable.setVisible(true);
                    corporateTable.setVisible(false);
                    clientInfo.setVisible(false);

                    individualTable.setItems(individuals);

                    indEmailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
                    indPhoneColumn.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
                    indFirstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
                    indLastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
                }

                if (curIndividual == "null" & curCorporate != "null"){
                    ObservableList<Corporate> corporates = FXCollections.observableArrayList();
                    corporates.add((Corporate) curCorporate);

                    individualTable.setVisible(false);
                    corporateTable.setVisible(true);
                    clientInfo.setVisible(false);

                    corporateTable.setItems(corporates);

                    crpEmailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
                    crpPhoneColumn.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
                    crpNameColumn.setCellValueFactory(cellData -> cellData.getValue().companyNameProperty());
                    crpAddressColumn.setCellValueFactory(cellData -> cellData.getValue().legalAddressProperty());
                }

                if (curIndividual == "null" & curCorporate == "null"){
                    individualTable.setVisible(false);
                    corporateTable.setVisible(false);
                    clientInfo.setVisible(true);

                    clientInfo.setText("Client is not selected!");
                    clientInfo.setFont(new Font("Century Gothic",40));
                }
            } catch (NullPointerException e){
                e.printStackTrace();
            }

        } else {
            startLabel.setText("No data");
            otherLabel.setText("No data");
            endLabel.setText("No data");
            typeLabel.setText("No data");
            descriptionArea.setText("No data");
        }
    }

    /**
     * Метод позволяет удалить запись заказа
     */
    @FXML
    public void handleDeleteOrder() {
        int selectedIndex = orderListTableView.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0){
            Boolean result = req_manager.deleteOrderList(orderListTableView.getSelectionModel().getSelectedItem());
            if (result) {
                orderListTableView.getItems().remove(selectedIndex);
                updatePage();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("ERROR!");
                alert.setHeaderText("Couldn't delete this order!");
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
     * Метод позволяет создать запись заказа
     */
    @FXML
    public void handleNewOrder() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/OrderEditPage.fxml"));
            AnchorPane editPage = loader.load();

            Stage creatingStage = new Stage();
            creatingStage.setTitle("Creating New Order");
            creatingStage.initOwner(MenuPageController.getPrimaryStage());
            creatingStage.initModality(Modality.APPLICATION_MODAL);

            Scene editScene = new Scene(editPage);
            creatingStage.setScene(editScene);

            OrderEditPageController controller = loader.getController();
            controller.initialize(creatingStage, new OrderList());

            creatingStage.showAndWait();

            System.out.println(controller.isOkClicked());

            if (controller.isOkClicked()){
                OrderList resultOrder = controller.getLocOrderList();
                if (resultOrder != null) {
                    req_manager.createOrderList(resultOrder);
                    updatePage();
                } else {
                    System.out.println("Haven't create new Order!");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод позволяет обновить или изменить запись заказа
     */
    @FXML
    public void handleEditOrder() {
        OrderList selectedOrder = orderListTableView.getSelectionModel().getSelectedItem();

        if (selectedOrder != null) {
            try {
                //FXMLLoader loader = new FXMLLoader();
                //loader.setLocation(getClass().getResource("../OrderEditPage.fxml"));
                //loader.setLocation(getClass().getResource("/views/OrderEditPage.fxml"));
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/OrderEditPage.fxml"));
                Parent editPage = (AnchorPane) loader.load();

                Stage editStage = new Stage();
                editStage.setTitle("Order Editing");
                //editStage.initOwner(MainPageController.getPrimaryStage());
                editStage.initModality(Modality.APPLICATION_MODAL);

                Scene editScene = new Scene(editPage);
                editStage.setScene(editScene);

                OrderEditPageController controller = loader.getController();
                controller.initialize(editStage, selectedOrder);

                editStage.showAndWait();

                System.out.println(controller.isOkClicked());

                if (controller.isOkClicked()){
                    OrderList resultOrderList = controller.getLocOrderList();
                    if (resultOrderList != null) {
                        req_manager.updateOrderList(resultOrderList);
                        updatePage();
                    } else {
                        System.out.println("Haven't updated OrderList entity!");
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

    public static Stage primaryStage = new Stage();

    private static Stage getPrimaryStage() {
        return primaryStage;
    }

}
