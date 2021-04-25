package coursework_ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MenuPageController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private AnchorPane anchorView;

    @FXML
    private Button exitButton;

    @FXML
    private Button mainPageButton;

    @FXML
    private Button aboutButton;

    @FXML
    private Button transportButton;

    public static Stage primaryStage = new Stage();

    /**
     * Инициализация контроллера при первом запуске
     */
    @FXML
    public void initialize() { }

    /**
     * Функция работает с кнопками в главном меню
     */
    @FXML
    public void listenButtons() {
        aboutButton.setOnAction(actionEvent -> switchPage("AboutPage"));
        transportButton.setOnAction(actionEvent -> switchPage("TransportPage"));
        exitButton.setOnAction(actionEvent -> exit());
    }

    /**
     * Переключает страницы в основном меню приложения
     * @param text определят название страницы на которую переключается основное окно
     */
    private void switchPage(String text) {
        try {
            anchorView.getChildren().clear();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/" + text + ".fxml"));
            Parent root = loader.load();

            anchorView.getChildren().add(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Закрывает главное меню программы
     */
    @FXML
    private void exit() {
        borderPane.getScene().getWindow().hide();
    }
}
