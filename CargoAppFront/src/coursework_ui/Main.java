package coursework_ui;

import coursework_ui.controllers.MenuPageController;
import coursework_ui.models.Transport;
import coursework_ui.utils.RequestManager;
import coursework_ui.utils.UtilsClass;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Основное приложение клиентской части
 */
public class Main extends Application {

    /**
     * Путь к MenuPage.fxml
     */
    private final String menuPage = "/views/MenuPage.fxml";

    private final String transportPage = "/views/TransportPage.fxml";

    private MenuPageController loc_menuController;

    /**
     * Позволяет открывать сайты в браузере по умолчанию
     */
    public static HostServices hostServices ;

    private Stage primaryStage;
    RequestManager req_manager = new RequestManager();
    private ObservableList<Transport> transportData = FXCollections.observableArrayList();

    public ObservableList<Transport> getTransportData() {
        return transportData;
    }

    /**
     * Данный метод запускается при старте приложения
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        /**
         * transportData - переменная, хранящая записи о всех транспортах из БД
         */
        transportData = req_manager.getTransports();

        hostServices = getHostServices();


        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Cargo App");
        this.primaryStage.setResizable(false);
        this.primaryStage.getIcons().add(new Image("/icon.png"));


        System.out.println(UtilsClass.isPlateFormat("В174РЖ74"));

        InitMainPage();
    }

    /*
    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource(mainPage));
        primaryStage.setTitle("Cargo App");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
     */

    public void InitMainPage(){
        try {
            FXMLLoader loader = new FXMLLoader(MenuPageController.class.getResource(menuPage));
            Parent root = loader.load();

            primaryStage.setScene(new Scene(root));

            loc_menuController = loader.getController();
            loc_menuController.listenButtons();

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    public void showTransportsInfo(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(transportPage));
            AnchorPane transportOverview = (AnchorPane) loader.load();



            TransportPageController PController = loader.getController();
            PController.setMainApp(this);

        } catch (IOException e){
            e.printStackTrace();
        }
    }

     */

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
