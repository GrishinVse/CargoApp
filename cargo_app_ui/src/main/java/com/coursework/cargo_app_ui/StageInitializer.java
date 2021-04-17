package com.coursework.cargo_app_ui;

import com.coursework.cargo_app_ui.UIApplication.StageReadyEvent;
import com.coursework.cargo_app_ui.controllers.EditPageController;
import com.coursework.cargo_app_ui.controllers.OrdersPageController;
import com.coursework.cargo_app_ui.models.OrderList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {
    @Value("classpath:/mainPage.fxml")
    private Resource mainPageResource;

    @Value("classpath:/ordersPage.fxml")
    private Resource ordersPageResource;

    @Value("classpath:/editOrderPage.fxml")
    private Resource editPageResource;

    private String appTitle;

    private Stage primaryStage;
    private BorderPane root;
    //private AnchorPane root;

    private ObservableList<OrderList> ordersData = FXCollections.observableArrayList();

    public ObservableList<OrderList> getOrdersData() {
        return ordersData;
    }

    public StageInitializer(@Value("${spring.application.ui.title}") String appTitle) {
        this.appTitle = appTitle;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent stageReadyEvent) {
        this.primaryStage = stageReadyEvent.getStage();
        this.primaryStage.setTitle(appTitle);

        initMainPage();
        showOrderDetails();
    }

    // Methods to show fxml pages

    private void initMainPage() {
        try {
            FXMLLoader loader = new FXMLLoader(mainPageResource.getURL());
            root = (BorderPane) loader.load();

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showOrderDetails(){
        try {
            FXMLLoader loader = new FXMLLoader(ordersPageResource.getURL());
            AnchorPane orderDetails = (AnchorPane) loader.load();

            root.setCenter(orderDetails);

            OrdersPageController ordersPageController = loader.getController();
            ordersPageController.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public boolean showOrderEditDialog(OrderList orderList) {
        try {
            FXMLLoader loader = new FXMLLoader(editPageResource.getURL());
            //loader.setLocation(Main.class.getResource("views/personEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("EDIT");
            dialogStage.initOwner(primaryStage);
            dialogStage.initModality(Modality.WINDOW_MODAL);

            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            EditPageController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setOrder(orderList);
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }



    /*
    @Override
    public void onApplicationEvent(StageReadyEvent stageReadyEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(mainPageResource.getURL());
            loader.setControllerFactory(aClass -> applicationContext.getBean(aClass));
            Parent parent = loader.load();

            Stage stage = stageReadyEvent.getStage();
            stage.setScene(new Scene(parent, 800, 600));
            stage.setTitle(appTitle);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

     */
}
