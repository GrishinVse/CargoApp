package com.coursework.cargo_app_ui.controllers;

import com.coursework.cargo_app_ui.StageInitializer;
import com.coursework.cargo_app_ui.models.OrderList;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class OrdersPageController {

    private final String urlApi = "http://localhost:8080/cargo_app/order_lists";

    private final String noData = "No Data";

    @FXML
    private TableView<OrderList> orderListTableView;

    @FXML
    private TableColumn<OrderList, Long> ClientColumn;

    @FXML
    private TableColumn<OrderList, String> DescriptionColumn;

    private StageInitializer mainApp;

    public void setOrderListTableView(TableView<OrderList> orderListTableView) {
        this.orderListTableView = orderListTableView;
    }

    // Labels block
    @FXML
    private Label EmpIDLabel;
    @FXML
    private Label StartAddressLabel;
    @FXML
    private Label OtherAddressLabel;
    @FXML
    private Label EndAddressLabel;
    @FXML
    private Label DescriptionLabel;

    // Buttons block
    @FXML
    private Button newButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    public OrdersPageController(){  }

    @FXML
    public void initialize(){
        ClientColumn.setCellValueFactory(cellData -> cellData.getValue().clientIdProperty());
        //ClientColumn.setCellValueFactory(cellData -> cellData.getValue().orderTypeProperty());
        DescriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());

        showOrderInfo(null);

        orderListTableView.getSelectionModel().selectedItemProperty().addListener(
                (((observableValue, oldValue, newValue) -> showOrderInfo(newValue)))
        );
    }

    public void showOrderInfo(OrderList orderList){
        if (orderList != null){
            EmpIDLabel.setText(String.valueOf(orderList.getId()));
            StartAddressLabel.setText(orderList.getStartAddress());
            OtherAddressLabel.setText(orderList.getOtherAddress());
            EndAddressLabel.setText(orderList.getEndAddress());
            DescriptionLabel.setText(orderList.getDescription());
        } else {
            EmpIDLabel.setText(noData);
            StartAddressLabel.setText(noData);
            OtherAddressLabel.setText(noData);
            EndAddressLabel.setText(noData);
            DescriptionLabel.setText(noData);
        }
    }

    public void setMainApp(StageInitializer mainApp) {
        this.mainApp = mainApp;
        orderListTableView.setItems(mainApp.getOrdersData());
    }

    // Deleting info about order

    @FXML
    private void handleDeleteOrder(){
        int selectedIndex = orderListTableView.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0){
            try {
                deleteOrder(urlApi, orderListTableView.getSelectionModel().getSelectedItem().getId());
            } catch (IOException e) {
                e.printStackTrace();
            }

            orderListTableView.getItems().remove(selectedIndex);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Error!");
            alert.setHeaderText("Nothing to delete!");
            alert.setContentText("Please select a order to delete!");

            alert.showAndWait();
        }
    }

    private void deleteOrder(String requestUrl, Long loc_id) throws IOException{
        URL url = new URL(requestUrl + "/" + loc_id.toString());
        System.out.println("URL to order with ID = " + loc_id.toString() + "\n| " + url + " |");

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("DELETE");
        urlConnection.connect();
        System.out.println("RESPONSE CODE = " + urlConnection.getResponseCode());
    }

    // Adding new info about order

    @FXML
    private void handleNewOrder() throws IOException{
        OrderList tempOrder = new OrderList();
        boolean isOkClicked = mainApp.showOrderEditDialog(tempOrder);
        if (isOkClicked){
            mainApp.getOrdersData().add(tempOrder);
            createOrder(urlApi, tempOrder);
        }
    }

    private void createOrder(String request, OrderList orderList) throws IOException {
        URL url = new URL(request);
        System.out.println("URL -> " + url);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.setRequestProperty("Content-Type", "application/json; utf-8");
        urlConnection.setRequestProperty("Accept", "application/json");
        urlConnection.setDoOutput(true);

        System.out.println("New Order created:" + orderList.toString());

        //JSONArray jsonObject = new JSONArray(tempPerson.toString());

        JSONObject jsonObject = new JSONObject(orderList.toString());
        OutputStream outputStream = urlConnection.getOutputStream();
        outputStream.write(jsonObject.toString().getBytes("UTF-8"));
        outputStream.close();
        System.out.println("RESPONSE CODE = " + urlConnection.getResponseCode());
    }





}
